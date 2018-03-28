package cloudzxy.studentinfosystem;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("lectures")
public class LectureResource {
    LectureDataStore lectureRepo = new LectureDataStore();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Lecture> getLectures() {
		return lectureRepo.getAll();
	}

	@GET
	@Path("{lectureName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Lecture getLecture(@PathParam("lectureName") String name) {
		return lectureRepo.get(name);
	}

	@POST
	@Path("lecture")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Lecture createLecture(Lecture l) {
		lectureRepo.create(l);

		return l;
	}

	@PUT
	@Path("lecture")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Lecture updateLecture(Lecture l) {
		if (lectureRepo.get(l.getLectureName()) == null) {
			lectureRepo.create(l);
		} else {
			lectureRepo.update(l);
		}		

		return lectureRepo.get(l.getLectureName());
	}

	@DELETE
	@Path("{lectureName}")
	public Lecture removeLecture(@PathParam("lectureName") String name) {
		Lecture lecture = lectureRepo.get(name);
		if (lecture != null) {
			lectureRepo.delete(name);
		}
		
		return lecture;
	}
}
