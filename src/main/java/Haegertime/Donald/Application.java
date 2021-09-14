package Haegertime.Donald;

import Haegertime.Donald.Model.Customer;
import Haegertime.Donald.Model.Project;
import Haegertime.Donald.repository.CustomerRepository;
import Haegertime.Donald.repository.EmployeeRepository;
import Haegertime.Donald.repository.ProjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		//save the first object in Database
		/*var customerRepo = applicationContext.getBean(CustomerRepository.class);
		var projectRepo = applicationContext.getBean(ProjectRepository.class);
		var employeeRepo = applicationContext.getBean(EmployeeRepository.class);

		var customer = new Customer(2L,"nom", new HashSet<>());
		customerRepo.save(customer);
		customer = customerRepo.findById(1L).get();
		var project = new Project("name", "description", customer, new HashSet<>());


		projectRepo.save(project);
		customerRepo.delete(customer);*/


	}

}
