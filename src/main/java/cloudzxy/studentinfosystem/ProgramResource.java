package cloudzxy.studentinfosystem;

import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("programs")
public class ProgramResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Program> getPrograms() {
		return ProgramDataStore.getAll();
	}

	@GET
	@Path("{programName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Program getProgram(@PathParam("programName") String name) {
		return ProgramDataStore.get(name);
	}

	@POST
	@Path("program")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Program createProgram(Program p) {
		ProgramDataStore.create(p);

		return p;
	}

	@PUT
	@Path("program")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Program updateProgram(Program p) {
		ProgramDataStore.update(p);

		return p;
	}

	@DELETE
	@Path("{programName}")
	public Program removeProgram(@PathParam("programName") String name) {
		return ProgramDataStore.delete(name);
	}
}
