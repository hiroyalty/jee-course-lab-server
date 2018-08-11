package nl.hva.jeecourse.rest;

import nl.hva.jeecourse.model.State;
import nl.hva.jeecourse.repository.Repository;
import nl.hva.jeecourse.repository.impl.RepositoryImpl;

import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("states")
public class StateResource {

    @Inject
    private Repository repo;// =  RepositoryImpl.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStates() {
        List<State> states = repo.getAllStates();

        return Response.status(Response.Status.OK).entity(states).build();
    }

}
