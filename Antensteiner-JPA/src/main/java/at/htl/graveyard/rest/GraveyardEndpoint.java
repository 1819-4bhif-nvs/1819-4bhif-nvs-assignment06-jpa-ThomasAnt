package at.htl.graveyard.rest;

import at.htl.graveyard.model.Graveyard;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("graveyard")
public class GraveyardEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGraveyards (){
        TypedQuery<Graveyard> query = em.createNamedQuery("Graveyard.findAll",Graveyard.class);
        return Response.ok().entity(query.getResultList()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id")long id){
        Graveyard graveyard = em.find(Graveyard.class,id);
        return Response.ok().entity(graveyard).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response post(Graveyard graveyard){
        if(graveyard == null)
            return Response.noContent().build();
        em.persist(graveyard);
        return Response.ok().entity(graveyard).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response put (Graveyard graveyard){
        em.merge(graveyard);
        return Response.ok().entity(graveyard).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete (@PathParam("id")long id){
        Graveyard graveyard = em.find(Graveyard.class,id);
        if(graveyard != null)
            em.remove(graveyard);
    }
}
