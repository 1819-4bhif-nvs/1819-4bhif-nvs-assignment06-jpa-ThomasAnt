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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Startup
@Singleton
public class InitBean {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init(){
        Graveyard graveyard = new Graveyard(1000L,"Linz");
        Graveyard graveyard2 = new Graveyard(500L,"Wels");

        em.persist(graveyard);
        em.persist(graveyard2);

        FamilyGrave grave1 = new FamilyGrave(1000L,"Bammberger",4L);
        FamilyGrave grave2 = new FamilyGrave(3000L,"Meier",7L);
        OneManGrave grave3 = new OneManGrave(400L,"Max","Mustermann",77L);
        OneManGrave grave4 = new OneManGrave(150L,"Lara","Mittermeier",55L);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Graveyardkeeper graveyardkeeper = new Graveyardkeeper("Maria","Schmitt",1200L, LocalDate.parse("01.01.2000",dtf));
        Graveyardkeeper graveyardkeeper2 = new Graveyardkeeper("Peter","Lindbichler",1400L,LocalDate.parse("12.12.2012",dtf));
        Graveyardkeeper graveyardkeeper3 = new Graveyardkeeper("Hubert","Hotzenbauer",1100L,LocalDate.parse("22.11.2010",dtf));

        graveyard.addGrave(grave1);
        graveyard.addGrave(grave3);
        graveyard2.addGrave(grave2);
        graveyard2.addGrave(grave4);

        graveyard.addGraveyardkeeper(graveyardkeeper);
        graveyard.addGraveyardkeeper(graveyardkeeper3);
        graveyard2.addGraveyardkeeper(graveyardkeeper2);
        graveyard2.addGraveyardkeeper(graveyardkeeper);

        em.persist(grave1);
        em.persist(grave2);
        em.persist(grave3);
        em.persist(grave4);
        em.persist(graveyardkeeper);
        em.persist(graveyardkeeper2);
        em.persist(graveyardkeeper3);
    }
}
