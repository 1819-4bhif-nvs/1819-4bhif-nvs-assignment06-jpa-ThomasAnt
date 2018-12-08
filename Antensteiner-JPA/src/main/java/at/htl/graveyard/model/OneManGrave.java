package at.htl.graveyard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class OneManGrave extends Grave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Long age;

    //region Constructor
    public OneManGrave() {
    }

    public OneManGrave(Long price, String location, String firstName, String lastName, Long age) {
        super(price,location);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    //endregion

    //region Getter Setter
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
    //endregion
}
