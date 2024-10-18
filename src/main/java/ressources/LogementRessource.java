package ressources;

import entities.Logement;
import filter.Secured;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("/logements")
public class LogementRessource {

    public static LogementBusiness logementBusiness=new LogementBusiness();


    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)  //produces dans la reponse HTTP
    public Response getLogements(@QueryParam("delegation") String delegation, @QueryParam("reference") Integer reference) {


        if (reference != null) {
            Logement logement = logementBusiness.getLogementsByReference(reference);
            if (logement != null) {
                return Response.status(Response.Status.OK).entity(logement).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }


        if (delegation != null) {
            List<Logement> logements = logementBusiness.getLogementsByDeleguation(delegation);
            return Response.status(Response.Status.OK).entity(logements).build();
        }


        List<Logement> logements = logementBusiness.getLogements();
        return Response.status(Response.Status.OK).entity(logements).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON) //consumes pour l'envoie de requete http
    public Response addLogement(Logement logement){ //la reponse par defaut 204 car elle est void
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



//    @GET
//    @Path("{delegation}")
//    public List<Logement> getLogementByDelegation(@QueryParam("delegation") String delegation){
//        return logementBusiness.getLogementsByDeleguation(delegation);
//    }
//
//
//    @GET
//    @Path("/{reference}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Logement getLogementsByReference(@QueryParam("reference") int reference){
//        return logementBusiness.getLogementsByReference(reference);
//    }

}
