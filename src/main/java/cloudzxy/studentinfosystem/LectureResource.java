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

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Lecture> getLectures() {
		return LectureDataStore.getAll();
	}

	@GET
	@Path("{lectureName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Lecture getLecture(@PathParam("lectureName") String name) {
		return LectureDataStore.get(name);
	}

	@POST
	@Path("lecture")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Lecture createLecture(Lecture l) {
		LectureDataStore.create(l);

		return l;
	}

	@PUT
	@Path("lecture")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Lecture updateLecture(Lecture l) {
		LectureDataStore.update(l);

		return l;
	}

	@DELETE
	@Path("{lectureName}")
	public Lecture removeLecture(@PathParam("lectureName") String name) {
		return LectureDataStore.delete(name);
	}
}
