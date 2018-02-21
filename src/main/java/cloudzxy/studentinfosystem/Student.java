package cloudzxy.studentinfosystem;

import java.util.*;

public class Student {
	private String studentId;
	private String name;
	private String image;
	private List<String> enrolledCourseNames;
	private String programName;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<String> getEnrolledCourseNames() {
		return enrolledCourseNames;
	}

	public void setEnrolledCourseNames(List<String> enrolledCourseNames) {
		this.enrolledCourseNames = enrolledCourseNames;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
}
