package Haegertime.Donald.services;

import Haegertime.Donald.Model.Customer;
import Haegertime.Donald.Model.Employee;
import Haegertime.Donald.exceptions.DuplicateException;
import Haegertime.Donald.exceptions.ElementNotFoundException;
import Haegertime.Donald.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAllCustomers(){

        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        return optionalCustomer.orElse(null);
    }

    public Customer addCustomer(Customer newCustomer) throws DuplicateException {

        if (newCustomer.getId() != null) {
            if (customerRepository.existsById(newCustomer.getId())) {
                throw new DuplicateException(getErrorDuplicateMessage(newCustomer.getId()));
            }
        }

        return customerRepository.save(newCustomer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) throws ElementNotFoundException {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()){

            if(customer.get().getId() == updatedCustomer.getId()){

                return customerRepository.save(updatedCustomer);
            }else {
                return null;
            }

        }else{

            throw new ElementNotFoundException(getErrorNotFoundMessage(updatedCustomer.getId()));
        }

    }

    private String getErrorNotFoundMessage(Long id){

        return "This Customer with the id "+ id +" has been not found.";
    }

    private String getErrorDuplicateMessage(Long id){

        return "This Customer with the id "+ id +" alraidy exist.";
    }


}
