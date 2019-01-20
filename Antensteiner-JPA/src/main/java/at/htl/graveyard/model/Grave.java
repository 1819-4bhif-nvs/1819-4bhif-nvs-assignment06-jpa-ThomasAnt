package at.htl.graveyard.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Grave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    private Graveyard graveyard;

    private Long price;

    //region Constructor
    public Grave() {
    }

    public Grave(Long price) {
        this.price = price;
    }
    //endregion


    @Override
    public String toString() {
        return this.id.toString();
    }

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

    public Graveyard getGraveyard() {
        return graveyard;
    }

    public void setGraveyard(Graveyard graveyard) {
        this.graveyard = graveyard;
    }

    //endregion

}
