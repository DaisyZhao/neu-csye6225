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

@Path("students")
public class StudentResource {
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Student> getStudents() {
		return StudentDataStore.getAll();
	}

	@GET
	@Path("{studentId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Student getStudent(@PathParam("studentId") String id) {
		return StudentDataStore.get(id);
	}

	@POST
	@Path("student")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Student createStudent(Student s) {
		StudentDataStore.create(s);

		return s;
	}

	@PUT
	@Path("student")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Student updateStudent(Student s) {
		StudentDataStore.update(s);

		return s;
	}

	@DELETE
	@Path("{studentId}")
	public Student removeStudent(@PathParam("studentId") String id) {
		return StudentDataStore.delete(id);
	}
}
