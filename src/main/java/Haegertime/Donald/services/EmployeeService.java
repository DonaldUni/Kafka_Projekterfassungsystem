package Haegertime.Donald.services;

import Haegertime.Donald.Model.Customer;
import Haegertime.Donald.Model.Employee;
import Haegertime.Donald.exceptions.DuplicateException;
import Haegertime.Donald.exceptions.ElementNotFoundException;
import Haegertime.Donald.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees(){

        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id)  {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        return optionalEmployee.orElse(null);
    }
    public Employee addEmployee(Employee employee) throws DuplicateException {

        if (employee.getId() != null) {
            if (employeeRepository.existsById(employee.getId())) {
                throw new DuplicateException(getErrorDuplicateMessage(employee.getId()));
            }
        }

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee updatedEmployee) throws ElementNotFoundException {

        Optional<Employee> employee = employeeRepository.findById(updatedEmployee.getId());

        if (employee.isPresent()){

            updatedEmployee.setId(employee.get().getId());
            return employeeRepository.save(updatedEmployee);
        }else{

            throw new ElementNotFoundException(getErrorNotFoundMessage(updatedEmployee.getId()));
        }

    }

    private String getErrorNotFoundMessage(Long id){

        return "This Employee with the id "+ id +" has been not found.";
    }

    private String getErrorDuplicateMessage(Long id){

        return "This Employee with the id "+ id +" alraidy exist.";
    }


}
