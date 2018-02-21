package cloudzxy.studentinfosystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDataStore {
	private static Map<String, Course> courseRepo = new HashMap<>();

	public static List<Course> getAll() {
		List<Course> courseList = new ArrayList<>();
		courseList.addAll(courseRepo.values());

		return courseList;
	}

	public static Course get(String cName) {
		return courseRepo.get(cName);
	}

	public static void create(Course c) {
		courseRepo.put(c.getCourseName(), c);
	}

	public static void update(Course c) {
		courseRepo.put(c.getCourseName(), c);
	}

	public static Course delete(String cName) {
		Course c = courseRepo.get(cName);
		if (c != null) {
			courseRepo.remove(cName);
		}

		return c;
	}
}
