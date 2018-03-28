package cloudzxy.studentinfosystem;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Professor")
public class Professor {
	private String professorName;
	private Set<String> instructionCourses;

	@DynamoDBHashKey(attributeName="professorName")
	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	@DynamoDBAttribute(attributeName="instructionCourses")
	public Set<String> getInstructionCourses() {
		return instructionCourses;
	}

	public void setInstructionCourses(Set<String> instructionCourses) {
		this.instructionCourses = instructionCourses;
	}
}
