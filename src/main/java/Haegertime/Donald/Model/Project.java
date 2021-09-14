package Haegertime.Donald.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String projectName;

    @Column(unique = true, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @ManyToMany(mappedBy = "assignedProjects", fetch = FetchType.EAGER)
    private Set<Employee> assignedEmployees = new HashSet<>();

    public Project() { }

    public Project(String projectName, String description, Customer customer, Set<Employee> assignedEmployees) {
        this.projectName = projectName;
        this.description = description;
        this.customer = customer;
        this.assignedEmployees = assignedEmployees;
    }

    public Project(Long id, String projectName, String description, Customer customer, Set<Employee> assignedEmployees) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.customer = customer;
        this.assignedEmployees = assignedEmployees;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(Set<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public void removeEmployeeFromProject(Employee employee) {
        this.assignedEmployees.remove(employee);
    }


}
