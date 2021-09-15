package Haegertime.Donald.controller;

import Haegertime.Donald.Model.Customer;
import Haegertime.Donald.exceptions.DuplicateException;
import Haegertime.Donald.exceptions.ElementNotFoundException;
import Haegertime.Donald.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/customers")
    private List<Customer> getAllCustomers(){

        return customerService.findAllCustomers();
    }

    @GetMapping(path = "/customers/{id}")
    private Customer getCustomerById(@PathVariable(value = "id")Long id){

        return customerService.findCustomerById(id);
    }


    @PostMapping(path = "/customers")
    private Customer postNewCustomer( @RequestBody Customer customer) throws DuplicateException {

        if (customer == null){

            return null;
        }else {

            return customerService.addCustomer(customer);
        }
    }

    @PatchMapping(path ="/customers")
    private Customer patchCustomer(@RequestParam(name = "id") Long id, @RequestBody Customer updatedCustomer) throws ElementNotFoundException {

        if (updatedCustomer == null){

            return null;
        }else {

            return customerService.updateCustomer(id, updatedCustomer);
        }
    }

}
