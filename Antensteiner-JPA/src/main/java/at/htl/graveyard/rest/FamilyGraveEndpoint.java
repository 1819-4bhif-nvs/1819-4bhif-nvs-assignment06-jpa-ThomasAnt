package at.htl.graveyard.rest;

import at.htl.graveyard.model.FamilyGrave;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("familygrave")
public class  FamilyGraveEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamilyGraves(){
        TypedQuery<FamilyGrave> familyGraveTypedQuery = em.createNamedQuery("FamilyGrave.findAll",FamilyGrave.class);
        return Response.ok().entity(familyGraveTypedQuery.getResultList()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getFamilyGraves(@PathParam("id")long id){
        FamilyGrave fm = em.find(FamilyGrave.class,id);
        return Response.ok().entity(fm).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postFamilyGrave(FamilyGrave fg){
        em.persist(fg);
        return Response.ok().entity(fg).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deleteFamilyGrave (@PathParam("id")long id){
        FamilyGrave fm = em.find(FamilyGrave.class,id);
        if( fm != null) {
            em.remove(fm);
        }
    }
}
