package cloudzxy.studentinfosystem;

import java.util.*;

public class ProgramDataStore {
	private static Map<String, Program> programRepo = new HashMap<>();

	public static List<Program> getAll() {
		List<Program> programList = new ArrayList<>();
		programList.addAll(programRepo.values());

		return programList;
	}

	public static Program get(String pName) {
		return programRepo.get(pName);
	}

	public static void create(Program p) {
		programRepo.put(p.getProgramName(), p);
	}

	public static void update(Program p) {
		programRepo.put(p.getProgramName(), p);
	}

	public static Program delete(String pName) {
		Program p = programRepo.get(pName);
		if (p != null) {
			programRepo.remove(pName);
		}

		return p;
	}
}
