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

@Path("professors")
public class ProfessorResource {
ProfessorDataStore professorRepo = new ProfessorDataStore();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Professor> getProfessors() {
		return professorRepo.getAll();
	}

	@GET
	@Path("{professorName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Professor getProfessor(@PathParam("professorName") String name) {
		return professorRepo.get(name);
	}

	@POST
	@Path("professor")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Professor createProfessor(Professor p) {
		professorRepo.create(p);

		return p;
	}

	@PUT
	@Path("professor")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Professor updateProfessor(Professor p) {
		if (professorRepo.get(p.getProfessorName()) == null) {
			professorRepo.create(p);
		} else {
			professorRepo.update(p);
		}		

		return professorRepo.get(p.getProfessorName());
	}

	@DELETE
	@Path("{professorName}")
	public Professor removeProfessor(@PathParam("professorName") String name) {
		Professor professor = professorRepo.get(name);
		if (professorRepo.get(name) != null) {
			professorRepo.delete(name);
		}
		
		return professor;
	}
}
