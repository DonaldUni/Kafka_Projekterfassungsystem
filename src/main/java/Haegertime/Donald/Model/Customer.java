package Haegertime.Donald.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany( mappedBy = "", cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<>();


    public Customer() { }

    public Customer(String name, Set<Project> projects) {
        this.name = name;
        this.projects = projects;
    }

    public Customer(Long id, String name, Set<Project> projects) {
        this.id = id;
        this.name = name;
        this.projects = projects;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addProjectToCustomer(Project newProject) {
        this.projects.add(newProject);
    }

    public void removeProjectFromCustomer(Project existingProject) {
        this.projects.remove(existingProject);
    }


}
