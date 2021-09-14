package Haegertime.Donald.services;

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

    public Employee findEmployeeById(Long id) throws ElementNotFoundException {

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

        return employeeRepository.save(updatedEmployee);
    }



    public String getErrorNotFoundMessage(Long id){

        return "This Timerecord with the id "+ id +" has been not found.";
    }

    public String getErrorDuplicateMessage(Long id){

        return "This Timerecord with the id "+ id +" alraidy exist.";
    }


}
