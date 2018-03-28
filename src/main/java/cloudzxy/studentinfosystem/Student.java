package cloudzxy.studentinfosystem;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Student")
public class Student {
	private String studentId;
	private String name;
	private String image;
	private String email;
	private Set<String> enrolledCourseNames;
	private String programName;

	@DynamoDBHashKey(attributeName="studentId")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@DynamoDBAttribute(attributeName="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute(attributeName="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DynamoDBAttribute(attributeName="image")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@DynamoDBAttribute(attributeName="enrolledCourseNames")
	public Set<String> getEnrolledCourseNames() {
		return enrolledCourseNames;
	}

	public void setEnrolledCourseNames(Set<String> enrolledCourseNames) {
		this.enrolledCourseNames = enrolledCourseNames;
	}

	@DynamoDBAttribute(attributeName="programName")
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
}
