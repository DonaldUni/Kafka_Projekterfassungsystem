package Haegertime.Donald;

import Haegertime.Donald.Model.Customer;
import Haegertime.Donald.Model.Employee;
import Haegertime.Donald.Model.Project;
import Haegertime.Donald.exceptions.ElementNotFoundException;
import Haegertime.Donald.repository.CustomerRepository;
import Haegertime.Donald.repository.EmployeeRepository;
import Haegertime.Donald.repository.ProjectRepository;
import Haegertime.Donald.services.ProjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		//save the first object in Database
		var customerRepo = applicationContext.getBean(CustomerRepository.class);
		var projectRepo = applicationContext.getBean(ProjectRepository.class);
		var projectSer = applicationContext.getBean(ProjectService.class);

		var employeeRepo = applicationContext.getBean(EmployeeRepository.class);

		var customer = new Customer(2L,"nom", new HashSet<>());
		customerRepo.save(customer);
		customer = customerRepo.findById(1L).get();
		var project = new Project("name1", "description1", customer, new HashSet<>());
		var project1 = new Project("name2", "description2", customer, new HashSet<>());
		var project2 = new Project("name3", "description3", customer, new HashSet<>());
		var employee = new Employee("fname", "lname", new HashSet<>());

		Set<Project> projectSet = new HashSet<>();
		projectSet.add(project);
		projectSet.add(project1);
		projectSet.add(project2);
		customer.setProjects(projectSet);
		customer = customerRepo.save(customer);

		Set<Project> projectsOfCustomer = customer.getProjects();
		//projectRepository.delete(project);

		//System.out.println(projectsOfEmployee);
		projectsOfCustomer.removeIf(p -> p.getId() == 2L);

		//update projects in employee and save in database
		customer.setProjects(projectsOfCustomer);
		customerRepo.save(customer);


		/*project = projectRepo.getById(2L);
		projectRepo.delete(project);*/

		/*projectRepo.save(project);
		projectRepo.save(project1);
		projectRepo.save(project2);*/
		employeeRepo.save(employee);

		//projectRepo.deleteById(2L);



		/*try {
			projectSer.removeProjectFromCustomer(1L, 2L);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}*/




		//Remove Project From Customer
		//project  = projectRepo.findById(2L).get();
		/*Project project10 = new Project();
		project1.setCustomer(customer);
		Example<Project> projectExample = Example.of(project10, ExampleMatcher.matchingAll());
		List<Project> projects = projectRepo.findAll(projectExample);
		projects.remove(project);
		Set<Project> projectSet = new HashSet<>();

		for (Project p: projects
			 ) {
			projectSet.add(p);

		}
		customer.setProjects(projectSet);
		customerRepo.save(customer);

		projectRepo.deleteById(2l);*/


		/*Set<Project> projectsOfCustomer = new HashSet<>();
		projectsOfCustomer.add(project);
		customer.setProjects(projectsOfCustomer);
		System.out.println(customer);
		//projectsOfCustomer.remove(project);
		customer.setProjects(projectsOfCustomer);

		/*try {
			projectSer.removeProjectFromCustomer(1L, 2L);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}*/


	}

}
