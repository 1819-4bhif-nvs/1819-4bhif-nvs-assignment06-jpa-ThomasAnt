package at.htl.graveyard.rest;

import at.htl.graveyard.model.FamilyGrave;
import at.htl.graveyard.model.OneManGrave;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("onemangrave")
public class OneManGraveEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneManGraves (){
        TypedQuery<OneManGrave> oneManGraveTypedQuery = em.createQuery("select omg from OneManGrave omg",OneManGrave.class);
        return Response.ok().entity(oneManGraveTypedQuery.getResultList()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getOneManGrave(@PathParam("id")long id){
        OneManGrave omg = em.find(OneManGrave.class,id);
        return Response.ok().entity(omg).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces
    public Long postFamilyGrave(OneManGrave omg){
        em.persist(omg);
        return omg.getId();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deleteFamilyGrave (@PathParam("id")long id){
        OneManGrave omg = em.find(OneManGrave.class,id);
        if(omg != null)
            em.remove(omg);
    }
}
