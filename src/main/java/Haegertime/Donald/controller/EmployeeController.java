package Haegertime.Donald.controller;

import Haegertime.Donald.Model.Employee;
import Haegertime.Donald.exceptions.DuplicateException;
import Haegertime.Donald.exceptions.ElementNotFoundException;
import Haegertime.Donald.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/employees")
    private List<Employee> getAllEmployees(){

        return employeeService.findAllEmployees();
    }

    @GetMapping(path = "/employees/{id}")
    private Employee getEmployeeById(@PathVariable(value = "id")Long id){

        return employeeService.findEmployeeById(id);
    }


    @PostMapping(path = "/employees")
    private Employee postNewEmployee(@RequestBody Employee employee) throws DuplicateException {

        if (employee == null){

            return null;
        }else {

            return employeeService.addEmployee(employee);
        }
    }

    @PatchMapping(path ="/employees")
    private Employee patchEmployee(@RequestParam(name = "id") Long id, @RequestBody Employee updatedEmployee) throws ElementNotFoundException {

        if (updatedEmployee == null){

            return null;
        }else {

            return employeeService.updateEmployee(updatedEmployee);
        }
    }

}
