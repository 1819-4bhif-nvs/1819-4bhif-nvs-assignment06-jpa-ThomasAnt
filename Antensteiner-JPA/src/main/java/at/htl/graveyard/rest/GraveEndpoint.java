package at.htl.graveyard.rest;

import at.htl.graveyard.model.FamilyGrave;
import at.htl.graveyard.model.Grave;
import at.htl.graveyard.model.Graveyardkeeper;

import javax.ejb.Stateless;
import javax.faces.push.Push;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/grave")
@Stateless
public class GraveEndpoint  {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGraves()
    {
        TypedQuery<Grave> graves = em.createQuery("select g from Grave g", Grave.class);
        return Response.ok().entity(graves.getResultList()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrave(@PathParam("id")long id)
    {
        Grave g = em.find(Grave.class,id);
        return Response.ok().entity(g).build();
    }

}
