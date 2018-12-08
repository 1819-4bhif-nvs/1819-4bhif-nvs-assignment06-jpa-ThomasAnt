package at.htl.graveyard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

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

    public FamilyGrave(Long price, String location, String familyName, Long numberBurried) {
        super(price,location);
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

