package at.htl.graveyard.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Grave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;
    private String location;

    //region Constructor
    public Grave() {
    }

    public Grave(Long price, String location) {
        this.price = price;
        this.location = location;
    }
    //endregion

    //region Getter Setter
    public Long getId() {
        return id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    //endregion

}
