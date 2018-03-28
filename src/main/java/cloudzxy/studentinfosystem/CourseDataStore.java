package cloudzxy.studentinfosystem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

public class CourseDataStore {
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(AccessCredentials.credentials))
			.withRegion(Regions.US_WEST_2).build();
	
	static AmazonSNS sns = AmazonSNSClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(AccessCredentials.credentials))
			.withRegion(Regions.US_WEST_2).build();
		
	public List<Course> getAll() {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Course> courseList = mapper.scan(Course.class, scanExpression);

		return courseList;
	}

	public Course get(String cName) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		return mapper.load(Course.class, cName);
	}

	public String createTopic(String courseName) {
		String topicName = courseName.replaceAll(" ", "_");
		CreateTopicRequest createTopicRequest = new CreateTopicRequest(topicName);
		CreateTopicResult createTopicResult = sns.createTopic(createTopicRequest);
		
		return createTopicResult.getTopicArn();
	}
	
	public void create(Course c) {
		c.setTopicArn(createTopic(c.getCourseName()));
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.save(c);
		
		subscribeToTopic(c, c.getEnrolledStudentIds());
	}		
	
	public void update(Course c) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Course courseToUpdate = mapper.load(Course.class, c.getCourseName());		
		Set<String> originStudentIds = courseToUpdate.getEnrolledStudentIds();
		
		courseToUpdate.setBoard(c.getBoard());
		courseToUpdate.setRoster(c.getRoster());
		if (c.getTopicArn() != null) {
			courseToUpdate.setTopicArn(c.getTopicArn());
		}		
		courseToUpdate.setProfessorName(c.getProfessorName());
		courseToUpdate.setLectureNames(c.getLectureNames());
		courseToUpdate.setEnrolledStudentIds(c.getEnrolledStudentIds());
		mapper.save(courseToUpdate);
			
		subscribeToTopic(courseToUpdate, getNewEnrolledStudents(originStudentIds, c.getEnrolledStudentIds()));
	}

	public void delete(String cName) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Course courseToDelete = mapper.load(Course.class, cName);
		
		deleteTopic(courseToDelete.getTopicArn());
		
		mapper.delete(courseToDelete);
	}
	
	public void deleteTopic(String topicArn) {
		DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(topicArn);
		sns.deleteTopic(deleteTopicRequest);
	}
		
	public Set<String> getNewEnrolledStudents(Set<String> originStudentIds, Set<String> newStudentIds) {
		Set<String> newEnrolledStudents = new HashSet<>();
		
		for (String stuId: newStudentIds) {
			if (!originStudentIds.contains(stuId)) {
				newEnrolledStudents.add(stuId);
			}
		}		
		
		return newEnrolledStudents;
	}
	
	public void subscribeToTopic(Course course, Set<String> newEnrolledStudents) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		for (String stuId: newEnrolledStudents) {
			Student student = mapper.load(Student.class, stuId);
			SubscribeRequest subRequest = new SubscribeRequest(course.getTopicArn(), "email", student.getEmail());
			sns.subscribe(subRequest);
		}		
	}
}
