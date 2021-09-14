package Haegertime.Donald.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @ManyToMany
    @JoinTable( name = "Assigments",joinColumns = {@JoinColumn(name = "Employee_ID")}, inverseJoinColumns = {@JoinColumn(name = "Project_Id")})
    private Set<Project> assignedProjects = new HashSet<>();

    public Employee() { }

    public Employee(String firstname, String lastname, Set<Project> assignedProjects) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.assignedProjects = assignedProjects;
    }

    public Employee(Long id, String firstname, String lastname, Set<Project> assignedProjects) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.assignedProjects = assignedProjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(Set<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }


}
