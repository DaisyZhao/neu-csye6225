package cloudzxy.studentinfosystem;

import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class AnnouncementDataStore {	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(AccessCredentials.credentials))
			.withRegion(Regions.US_WEST_2).build();
	
	public List<Announcement> getAll() {		
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Announcement> announcementList = mapper.scan(Announcement.class, scanExpression);

		return announcementList;
	}

	public Announcement get(String announcementId) {
        DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		return mapper.load(Announcement.class, announcementId);
	}

	public void create(Announcement a) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.save(a);
	}

	public void update(Announcement a) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Announcement announcementToUpdate = mapper.load(Announcement.class, a.getAnnouncementId());
		announcementToUpdate.setAnnouncementContent(a.getAnnouncementContent());
		announcementToUpdate.setCourseName(a.getCourseName());
		
		mapper.save(announcementToUpdate);
	}

	public void delete(String announcementId) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Announcement announcementToDelete = mapper.load(Announcement.class, announcementId);
		mapper.delete(announcementToDelete);
	}
}
