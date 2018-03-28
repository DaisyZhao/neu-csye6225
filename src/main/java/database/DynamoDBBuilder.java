package database;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

public class DynamoDBBuilder {
	static AmazonDynamoDB dynamoDB;

	private static void init() throws Exception {
		dynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(new ProfileCredentialsProvider())
				.withRegion(Regions.US_WEST_2).build();
	}

	public static void main(String[] args) throws Exception {
		init();
		createTable("Lecture", "lectureName");
		init();
		createTable("Student", "studentId");
		init();
		createTable("Course", "courseName");
		init();
		createTable("Program", "programName");
		init();
		createTable("Professor", "professorName");
		init();
		createTable("Announcement", "announcementId");
	}

	public static void createTable(String tableName, String keyName) {
		try {
			CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
					.withKeySchema(new KeySchemaElement().withAttributeName(keyName).withKeyType(KeyType.HASH))
					.withAttributeDefinitions(new AttributeDefinition()
							.withAttributeName(keyName).withAttributeType(ScalarAttributeType.S))
					.withProvisionedThroughput(new ProvisionedThroughput()
							.withReadCapacityUnits(5L).withWriteCapacityUnits(5L));
			TableUtils.createTableIfNotExists(dynamoDB, createTableRequest);
			TableUtils.waitUntilActive(dynamoDB, tableName);
			System.out.println("Create table request succeed for " + tableName);

		} catch (Exception e) {
			System.err.println("Create table request failed for " + tableName);
			System.err.println(e.getMessage());
		}
	}

}
