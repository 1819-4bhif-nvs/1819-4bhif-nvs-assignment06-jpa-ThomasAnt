package at.htl.graveyard.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Graveyard implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numberOfGraves;
    private Long area;
    private String location;

    //region Constuctor
    public Graveyard() {
    }

    public Graveyard(Long numberOfGraves, Long area, String location) {
        this.numberOfGraves = numberOfGraves;
        this.area = area;
        this.location = location;
    }
    //endregion

    //region Getter Setter
    public Long getNumberOfGraves() {
        return numberOfGraves;
    }

    public void setNumberOfGraves(Long grabanzahl) {
        this.numberOfGraves = grabanzahl;
    }

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
    //endregion
}
