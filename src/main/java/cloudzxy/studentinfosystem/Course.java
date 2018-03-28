package cloudzxy.studentinfosystem;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Course")
public class Course {
	private String courseName;
	private String professorName;
	private List<String> lectureNames;
	private String board;
	private String roster;
	private String topicArn;
	private Set<String> enrolledStudentIds;

	@DynamoDBHashKey(attributeName="courseName")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@DynamoDBAttribute(attributeName="professorName")
	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	@DynamoDBAttribute(attributeName="lectureNames")
	public List<String> getLectureNames() {
		return lectureNames;
	}

	public void setLectureNames(List<String> lectureNames) {
		this.lectureNames = lectureNames;
	}

	@DynamoDBAttribute(attributeName="board")
	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	@DynamoDBAttribute(attributeName="roster")
	public String getRoster() {
		return roster;
	}

	public void setRoster(String roster) {
		this.roster = roster;
	}

	@DynamoDBAttribute(attributeName="topicArn")
	public String getTopicArn() {
		return topicArn;
	}

	public void setTopicArn(String topicArn) {
		this.topicArn = topicArn;
	}

	@DynamoDBAttribute(attributeName="enrolledStudentIds")
	public Set<String> getEnrolledStudentIds() {
		return enrolledStudentIds;
	}

	public void setEnrolledStudentIds(Set<String> enrolledStudentIds) {
		this.enrolledStudentIds = enrolledStudentIds;
	}
}
