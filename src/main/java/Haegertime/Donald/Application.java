package Haegertime.Donald;

import Haegertime.Donald.Model.Customer;
import Haegertime.Donald.Model.Employee;
import Haegertime.Donald.Model.Project;
import Haegertime.Donald.exceptions.DuplicateException;
import Haegertime.Donald.exceptions.ElementNotFoundException;
import Haegertime.Donald.repository.CustomerRepository;
import Haegertime.Donald.repository.EmployeeRepository;
import Haegertime.Donald.repository.ProjectRepository;
import Haegertime.Donald.services.CustomerService;
import Haegertime.Donald.services.EmployeeService;
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
		saveFirstDataInDatabase(applicationContext);
	}

	private static void saveFirstDataInDatabase(ConfigurableApplicationContext applicationContext){

		var customerService = applicationContext.getBean(CustomerService.class);
		var projectService = applicationContext.getBean(ProjectService.class);
		var employeeService = applicationContext.getBean(EmployeeService.class);

		var customer = new Customer(2L,"nom-customer1", new HashSet<>());
		var customer1 = new Customer(2L,"nom-customer2", new HashSet<>());
		var customer2 = new Customer(3L,"nom-customer3", new HashSet<>());

		var project = new Project("project-name1", "description1", customer, new HashSet<>());
		var project1 = new Project("project-name2", "description2", customer, new HashSet<>());
		var project2 = new Project("project-name3", "description3", customer1, new HashSet<>());

		var employee = new Employee("fname", "lname", new HashSet<>());


		try {
			customerService.addCustomer(customer);
			customerService.addCustomer(customer1);
			customerService.addCustomer(customer2);

			customer = customerService.findCustomerById(1L);
			customer1 = customerService.findCustomerById(2L);

			Set<Project> projectSet = new HashSet<>();
			Set<Project> projectSet1 = new HashSet<>();
			projectSet.add(project);
			projectSet.add(project1);
			projectSet1.add(project2);
			customer.setProjects(projectSet);
			customer1.setProjects(projectSet);

			customerService.updateCustomer(customer);

			employeeService.addEmployee(employee);

		} catch (DuplicateException | ElementNotFoundException e) {
			e.printStackTrace();
		}

	}

}
