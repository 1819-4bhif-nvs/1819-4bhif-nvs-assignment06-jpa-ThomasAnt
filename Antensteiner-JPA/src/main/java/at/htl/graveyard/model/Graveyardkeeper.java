package at.htl.graveyard.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Graveyardkeeper.findAll", query = "select gyk from Graveyardkeeper gyk")
@Entity
public class Graveyardkeeper implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "graveyardkeepers",fetch = FetchType.LAZY)
    @JsonbTransient
    private List<Graveyard> graveyards = new ArrayList<>();

    private String firstname;
    private String lastname;
    private Long salary;
    private LocalDate worksSince;

    public Graveyardkeeper() {
    }

    public Graveyardkeeper(String firstname, String lastname, Long salary,LocalDate worksSince) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.salary = salary;
        this.worksSince = worksSince;
    }

    public List<Graveyard> getGraveyards() {
        return graveyards;
    }

    public void setGraveyards(List<Graveyard> graveyards) {
        this.graveyards = graveyards;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public LocalDate getWorksSince() {
        return worksSince;
    }

    public void setWorksSince(LocalDate worksSince) {
        this.worksSince = worksSince;
    }
}
