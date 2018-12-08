package at.htl.graveyard.business;

import at.htl.graveyard.model.FamilyGrave;
import at.htl.graveyard.model.Graveyardkeeper;
import at.htl.graveyard.model.Graveyard;
import at.htl.graveyard.model.OneManGrave;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class InitBean {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init(){
        FamilyGrave grave = new FamilyGrave(300L,"Linz","Mustermann",4L);
        FamilyGrave grave2 = new FamilyGrave(3000L,"Linz","Mustermann22",40L);
        OneManGrave grave1 = new OneManGrave(400L,"Linz","Max","Mustermann",30L);
        Graveyard graveyard = new Graveyard(100L,500L,"Linz");
        Graveyardkeeper graveyardkeeper = new Graveyardkeeper("Max","Mustermann",1000L);
        Graveyardkeeper graveyardkeeper2 = new Graveyardkeeper("Max2","Mustermann2",2000L);

        em.persist(grave);
        em.persist(grave1);
        em.persist(grave2);
        em.persist(graveyardkeeper);
        em.persist(graveyardkeeper2);
        em.persist(graveyard);
    }
}
