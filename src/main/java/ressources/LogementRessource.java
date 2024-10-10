package ressources;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("/logements")
public class LogementRessource {

    public static LogementBusiness logementBusiness=new LogementBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logement> getLogements(){
        return logementBusiness.getLogements();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement){
        if(logementBusiness.addLogement(logement)){
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{ref}")
    public Response deleteLogement(@PathParam("ref") int ref) {
        if (logementBusiness.deleteLogement(ref)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{ref}")
    public Response updateLogement(@PathParam("ref") int ref, Logement logement){
        if (logementBusiness.updateLogement(ref, logement)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }
}
