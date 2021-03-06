package cloudzxy.studentinfosystem;

import java.util.*;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class ProgramDataStore {
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(AccessCredentials.credentials))
			.withRegion(Regions.US_WEST_2).build();

	public List<Program> getAll() {		
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Program> programList = mapper.scan(Program.class, scanExpression);

		return programList;
	}

	public Program get(String pName) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		return mapper.load(Program.class, pName);
	}

	public void create(Program p) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.save(p);
	}

	public void update(Program p) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Program programToUpdate = mapper.load(Program.class, p.getProgramName());
		programToUpdate.setCourseNames(p.getCourseNames());		
		mapper.save(programToUpdate);
	}

	public void delete(String pName) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Program programToDelete = mapper.load(Program.class, pName);
		mapper.delete(programToDelete);
	}
}
