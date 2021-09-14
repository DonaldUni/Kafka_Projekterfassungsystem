package Haegertime.Donald.services;

import Haegertime.Donald.Model.Customer;
import Haegertime.Donald.Model.Employee;
import Haegertime.Donald.Model.Project;
import Haegertime.Donald.exceptions.ElementNotFoundException;
import Haegertime.Donald.repository.CustomerRepository;
import Haegertime.Donald.repository.EmployeeRepository;
import Haegertime.Donald.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Project> findAllProjects(){

        return projectRepository.findAll();
    }

    public Project findProjectById(Long id){

        Optional<Project> optionalProject = projectRepository.findById(id);

        return optionalProject.orElse(null);
    }

    public Project addNewProjectToCustomer(Long customerId, Project newProject) throws ElementNotFoundException {

        Project savedProject = null;

        if (customerId != null) {
            if (customerRepository.existsById(customerId)) {
                Customer customer = customerRepository.findById(customerId).get();
                newProject.setCustomer(customer);
                savedProject = projectRepository.save(newProject);
            }else {

                throw new ElementNotFoundException(getErrorProjectNotFoundMessage(customerId));
            }
        }

        return savedProject;
    }

    public Project updateProject(Project updatedProject){

        return projectRepository.save(updatedProject);
    }

    public void removeProjectFromCustomer(Long customerId, Long projectId) throws ElementNotFoundException {

        if (customerId != null && projectId != null) {
            if (customerRepository.existsById(customerId)){
                if (projectRepository.existsById(projectId)){

                    Customer customer = customerRepository.findById(customerId).get();
                    Project project = projectRepository.findById(projectId).get();

                    //Remove Project From Customer
                    Set<Project> projectsOfCustomer = customer.getProjects();

                    projectsOfCustomer.removeIf(p -> p.getId() == project.getId());

                    //update projects in customer and save in database
                    customer.setProjects(projectsOfCustomer);
                    customerRepository.save(customer);

                }else {

                    throw new ElementNotFoundException(getErrorProjectNotFoundMessage(projectId));
                }
            }else {

                throw new ElementNotFoundException(getErrorCustomerNotFoundMessage(projectId));
            }
        }
    }

    public void assignEmployeeToProject(Long projectId, Long employeeId) throws ElementNotFoundException {

        if (projectId != null && employeeId != null) {
            if (projectRepository.existsById(projectId)) {
                if (employeeRepository.existsById(employeeId)) {

                    Project project = projectRepository.findById(projectId).get();
                    Employee employee = employeeRepository.findById(employeeId).get();

                    //Assign Employee To Project
                    Set<Project> projectsOfEmployee = employee.getAssignedProjects();
                    projectsOfEmployee.add(project);
                    employee.setAssignedProjects(projectsOfEmployee);
                    employeeRepository.save(employee);

                } else {

                    throw new ElementNotFoundException(getErrorProjectNotFoundMessage(projectId));
                }
            } else {

                throw new ElementNotFoundException(getErrorCustomerNotFoundMessage(projectId));
            }
        }
    }

    public void removeEmployeeFromProject(Long projectId, Long employeeId) throws ElementNotFoundException {

            if (projectId != null && employeeId != null) {
                if (projectRepository.existsById(projectId)) {
                    if (employeeRepository.existsById(employeeId)) {

                        Project project = projectRepository.findById(projectId).get();
                        Employee employee = employeeRepository.findById(employeeId).get();

                        //Remove Employee From Project
                        Set<Project> projectsOfEmployee = employee.getAssignedProjects();
                        System.out.println(projectsOfEmployee);
                        projectsOfEmployee.removeIf(p -> p.getId() == project.getId());

                        //update projects in employee and save in database
                        employee.setAssignedProjects(projectsOfEmployee);
                        employeeRepository.save(employee);

                    } else {

                        throw new ElementNotFoundException(getErrorProjectNotFoundMessage(projectId));
                    }
                } else {

                    throw new ElementNotFoundException(getErrorCustomerNotFoundMessage(projectId));
                }
            }
    }








    private String getErrorProjectNotFoundMessage(Long id){

        return "This Project with the id "+ id +" has been not found.";
    }

    private String getErrorCustomerNotFoundMessage(Long id){

        return "This Customer with the id "+ id +" has been not found.";
    }



    private String getErrorDuplicateMessage(Long id){

        return "This Project with the id "+ id +" alraidy exist.";
    }
}
