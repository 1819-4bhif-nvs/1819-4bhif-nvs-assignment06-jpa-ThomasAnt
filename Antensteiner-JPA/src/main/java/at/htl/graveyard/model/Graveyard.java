package at.htl.graveyard.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedQueries(
        {
                @NamedQuery(name = "Graveyard.findAll", query = "select g from Graveyard g"),
                //@NamedQuery(name= "Graveyard.findByLocation", query = "select gy from graveyard gy where gy.location like :location")
        }
)@Entity
public class Graveyard implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "graveyard", orphanRemoval = true)
    private List<Grave> graves = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employment",
            joinColumns = @JoinColumn(name = "graveyard_id"),
            inverseJoinColumns = @JoinColumn(name = "graveyardkeeper_id"))
    private List<Graveyardkeeper>  graveyardkeepers = new ArrayList<>();

    private Long area;
    private String location;

    //region Constuctor
    public Graveyard() {
    }

    public Graveyard(Long area, String location) {
        this.area = area;
        this.location = location;
    }
    //endregion

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addGraveyardkeeper (Graveyardkeeper graveyardkeeper){
        graveyardkeepers.add(graveyardkeeper);
        graveyardkeeper.getGraveyards().add(this);
    }

    public void removeGraveyardkeeper(Graveyardkeeper graveyardkeeper){
        graveyardkeepers.remove(graveyardkeeper);
        graveyardkeeper.getGraveyards().remove(graveyardkeeper);
    }

    public List<Grave> getGraves() {
        return graves;
    }

    public List<Graveyardkeeper> getGraveyardkeepers() {
        return graveyardkeepers;
    }

    public void addGrave(Grave g) {
        graves.add(g);
        g.setGraveyard(this);
    }

    public void removeGrave(Grave g){
        graves.remove(g);
        g.setGraveyard(null);
    }
    //endregion
}
