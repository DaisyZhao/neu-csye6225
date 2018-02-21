package cloudzxy.studentinfosystem;

import java.util.*;

public class StudentDataStore {
	private static Map<String, Student> studentRepo = new HashMap<>();

	public static List<Student> getAll() {
		List<Student> studentList = new ArrayList<>();
		studentList.addAll(studentRepo.values());

		return studentList;
	}

	public static Student get(String id) {
		return studentRepo.get(id);
	}

	public static void create(Student s) {
		studentRepo.put(s.getStudentId(), s);
	}

	public static void update(Student s) {
		studentRepo.put(s.getStudentId(), s);
	}

	public static Student delete(String id) {
		Student s = studentRepo.get(id);
		if (s != null) {
			studentRepo.remove(id);
		}

		return s;
	}
}
