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

@Path("announcements")
public class AnnouncementResource {

	AnnouncementDataStore announcementRepo = new AnnouncementDataStore();
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Announcement> getAnnouncements() {
		return announcementRepo.getAll();
	}

	@GET
	@Path("{announcementId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Announcement getAnnouncement(@PathParam("announcementId") String id) {
		return announcementRepo.get(id);
	}

	@POST
	@Path("announcement")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Announcement createAnnouncement(Announcement anm) {
		announcementRepo.create(anm);

		return anm;
	}

	@PUT
	@Path("announcement")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Announcement updateAnnouncement(Announcement anm) {
		if (announcementRepo.get(anm.getAnnouncementId()) == null) {
			announcementRepo.create(anm);
		} else {
		    announcementRepo.update(anm);
		}

		return announcementRepo.get(anm.getAnnouncementId());
	}

	@DELETE
	@Path("{announcementId}")
	public Announcement removeAnnouncement(@PathParam("announcementId") String id) {
		Announcement anm = announcementRepo.get(id);
		if (anm != null) {
			announcementRepo.delete(id);
		}
		
		return anm;
	}
}
