package cloudzxy.studentinfosystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LectureDataStore {
	private static Map<String, Lecture> lectureRepo = new HashMap<>();

	public static List<Lecture> getAll() {
		List<Lecture> lectureList = new ArrayList<>();
		lectureList.addAll(lectureRepo.values());

		return lectureList;
	}

	public static Lecture get(String lName) {
		return lectureRepo.get(lName);
	}

	public static void create(Lecture l) {
		lectureRepo.put(l.getLectureName(), l);
	}

	public static void update(Lecture l) {
		lectureRepo.put(l.getLectureName(), l);
	}

	public static Lecture delete(String lName) {
		Lecture l = lectureRepo.get(lName);
		if (l != null) {
			lectureRepo.remove(lName);
		}

		return l;
	}
}
