package cloudzxy.studentinfosystem;

import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class ProfessorDataStore {
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(AccessCredentials.credentials))
			.withRegion(Regions.US_WEST_2).build();	
	
//	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withCredentials(new ProfileCredentialsProvider())
//			.withRegion(Regions.US_WEST_2).build();

	public List<Professor> getAll() {		
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Professor> professorList = mapper.scan(Professor.class, scanExpression);

		return professorList;
	}

	public Professor get(String pName) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		return mapper.load(Professor.class, pName);
	}

	public void create(Professor p) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.save(p);
	}

	public void update(Professor p) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Professor professorToUpdate = mapper.load(Professor.class, p.getProfessorName());
		professorToUpdate.setInstructionCourses(p.getInstructionCourses());	
		mapper.save(professorToUpdate);
	}

	public void delete(String pName) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Professor professorToDelete = mapper.load(Professor.class, pName);
		mapper.delete(professorToDelete);
	}
}
