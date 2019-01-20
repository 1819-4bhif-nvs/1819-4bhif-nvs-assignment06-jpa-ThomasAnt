package at.htl.graveyard.model;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "FamilyGrave.findAll", query = "select fg from FamilyGrave fg")
@Entity
public class FamilyGrave extends Grave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String familyName;
    private Long numberBurried;

    //region Constructor
    public FamilyGrave() {
    }

    public FamilyGrave(Long price, String familyName, Long numberBurried) {
        super(price);
        this.familyName = familyName;
        this.numberBurried = numberBurried;
    }
    //endregion



    //region Getter Setter
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Long getNumberBurried() {
        return numberBurried;
    }

    public void setNumberBurried(Long numberBurried) {
        this.numberBurried = numberBurried;
    }
    //endregion
}

