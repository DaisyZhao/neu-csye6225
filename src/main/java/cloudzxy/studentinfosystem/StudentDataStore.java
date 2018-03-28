package cloudzxy.studentinfosystem;

import java.util.*;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class StudentDataStore {	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(AccessCredentials.credentials))
			.withRegion(Regions.US_WEST_2).build();
		
	public List<Student> getAll() {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Student> studentList = mapper.scan(Student.class, scanExpression);

		return studentList;
	}

	public Student get(String sId) {
        DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		return mapper.load(Student.class, sId);
	}

	public void create(Student s) {
        DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		mapper.save(s);;
	}
	
	public void update(Student s) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		Student studentToUpdate = mapper.load(Student.class, s.getStudentId());
		studentToUpdate.setName(s.getName());
		studentToUpdate.setEmail(s.getEmail());
		studentToUpdate.setImage(s.getImage());
		studentToUpdate.setProgramName(s.getProgramName());
		studentToUpdate.setEnrolledCourseNames(s.getEnrolledCourseNames());
	
		mapper.save(studentToUpdate);
	}
	
	public void delete(String sId) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Student studentToDelete = mapper.load(Student.class, sId);
		mapper.delete(studentToDelete);
	}
}