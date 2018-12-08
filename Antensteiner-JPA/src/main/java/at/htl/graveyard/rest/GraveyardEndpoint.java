package at.htl.graveyard.rest;

import at.htl.graveyard.model.Graveyard;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("graveyard")
public class GraveyardEndpoint {
    @PersistenceContext
    EntityManager em;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGraveyards (){
        TypedQuery<Graveyard> query = em.createQuery("select gy from Graveyard gy",Graveyard.class);
        return Response.ok().entity(query.getResultList()).build();
    }
}
