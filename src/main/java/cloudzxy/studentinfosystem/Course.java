package cloudzxy.studentinfosystem;

import java.util.*;

public class Course {
	private String courseName;
	private List<String> lectureNames;
	private String board;
	private String roster;
	private List<String> enrolledStudentIds;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<String> getLectureNames() {
		return lectureNames;
	}

	public void setLectureNames(List<String> lectureNames) {
		this.lectureNames = lectureNames;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getRoster() {
		return roster;
	}

	public void setRoster(String roster) {
		this.roster = roster;
	}

	public List<String> getEnrolledStudentIds() {
		return enrolledStudentIds;
	}

	public void setEnrolledStudentIds(List<String> enrolledStudentIds) {
		this.enrolledStudentIds = enrolledStudentIds;
	}
}
