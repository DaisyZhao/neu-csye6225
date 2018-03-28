package cloudzxy.studentinfosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

public class LectureDataStore {	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(AccessCredentials.credentials))
			.withRegion(Regions.US_WEST_2).build();
	static DynamoDB dynamoDB = new DynamoDB(client);
	
	static String tableName = "Lecture";
	
	public LectureDataStore() {}
		
	public List<Lecture> getAll() {
		List<Lecture> lectureList = new ArrayList<>();
		try {
			ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
			ScanResult scanResult = client.scan(scanRequest);
			for (Map<String, AttributeValue> item: scanResult.getItems()) {
				Lecture lecture = new Lecture();			
				lecture.setLectureName(item.get("lectureName").getS());
				lecture.setNotes(item.get("notes").getS());
				
				List<String> materials = new ArrayList<>();
				for (Object m: item.get("materials").getL()) {
					materials.add(m.toString());
				}
				lecture.setMaterials(materials);
				
				lectureList.add(lecture);
			}
		} catch (Exception e) {
            System.err.println("Scan all items failed.");
            System.err.println(e.getMessage());
        }

		return lectureList;
	}

	public Lecture get(String lName) {
		Table lectureTable = dynamoDB.getTable(tableName);	
		Lecture lecture = new Lecture();
		
		try {
		    Item item = lectureTable.getItem("lectureName", lName);
				
		    lecture.setLectureName(item.getString("lectureName"));
		    lecture.setNotes(item.getString("notes"));
		
		    List<String> materials = new ArrayList<>();
		    for (Object m: item.getList("materials")) {
			    materials.add(m.toString());
		    }
		    lecture.setMaterials(materials);
		} catch (Exception e) {
            System.err.println("GetItem failed.");
            System.err.println(e.getMessage());
        }
		
		return lecture;
	}

	public void create(Lecture l) {
		Table table = dynamoDB.getTable(tableName);
		try {
		    Item item = new Item()
				.withPrimaryKey("lectureName", l.getLectureName())
				.withString("notes", l.getNotes())
				.withList("materials", l.getMaterials());
		    table.putItem(item);	
		} catch(Exception e) {
		    System.err.println("Create items failed.");
            System.err.println(e.getMessage());
		}
	}
	
	public void update(Lecture l) {
		Table table = dynamoDB.getTable(tableName);
				
		try {
			UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("lectureName", l.getLectureName())
					.withUpdateExpression("set notes = :n, materials = :m")
					.withValueMap(new ValueMap().withString(":n", l.getNotes()).withList(":m", l.getMaterials()))
					.withReturnValues(ReturnValue.UPDATED_NEW);
			
			table.updateItem(updateItemSpec);
		} catch (Exception e) {
			System.err.println("Unable to update item: " + l.getLectureName());
			System.err.println(e.getMessage());
		}
	}
	
	public void delete(String lName) {
		Table table = dynamoDB.getTable(tableName);
		
		try {
			DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("lectureName", lName);
			table.deleteItem(deleteItemSpec);		
		} 
		catch (Exception e) {
			System.err.println("Error deleting item in " + lName);
		    System.err.println(e.getMessage());
		}
	}
}
