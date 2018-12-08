package at.htl.graveyard.rest;

import at.htl.graveyard.model.Grave;
import at.htl.graveyard.model.Graveyardkeeper;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/graveyardkeeper")
@Stateless
public class GraveyardkeeperEndpoint {
    @PersistenceContext
    EntityManager em;
        
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGravekeepers (){
        TypedQuery<Graveyardkeeper> query = em.createQuery("select g from Graveyardkeeper g",Graveyardkeeper.class);
        return Response.ok().entity(query.getResultList()).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getGravekeeper(@PathParam("id")long id){
        Graveyardkeeper graveyardkeeper = em.find(Graveyardkeeper.class,id);
        return Response.ok().entity(graveyardkeeper).build();
    }
}
