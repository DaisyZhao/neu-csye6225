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

@Path("courses")
public class CourseResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Course> getCourses() {
		return CourseDataStore.getAll();
	}

	@GET
	@Path("{courseName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Course getCourse(@PathParam("courseName") String name) {
		return CourseDataStore.get(name);
	}

	@POST
	@Path("course")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Course createCourse(Course c) {
		CourseDataStore.create(c);

		return c;
	}

	@PUT
	@Path("course")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Course updateCourse(Course c) {
		CourseDataStore.update(c);

		return c;
	}

	@DELETE
	@Path("{courseName}")
	public Course removeCourse(@PathParam("courseName") String name) {
		return CourseDataStore.delete(name);
	}
}
