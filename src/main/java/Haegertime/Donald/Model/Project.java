package Haegertime.Donald.Model;

import java.util.Set;

public class Project {

    private Long id;
    private String projectName;
    private String description;
    private Customer customer;
    private Set<Employee> assignedEmployees;

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

    public boolean removeEmployeeFromProject(Employee employee) {
        return this.assignedEmployees.remove(employee);
    }


}
