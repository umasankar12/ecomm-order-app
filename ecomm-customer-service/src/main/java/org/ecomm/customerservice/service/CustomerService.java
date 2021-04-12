package org.ecomm.customerservice.service;

import org.ecomm.customerservice.dto.CustomerPayload;
import org.ecomm.foundation.model.Customer;
import org.ecomm.foundation.repo.CustomerRepository;
import org.ecomm.foundation.repo.PaymentRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Named
public class CustomerService {

    CustomerRepository customerRepository;

    @Inject
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(CustomerPayload customer){

        return customerRepository.save(new Customer()
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setDob(customer.getDob())
                .setEmail(customer.getEmail())
                .setPhone(customer.getPhone()));
    }

    public Customer updateCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Set<Customer> getCustomers(Integer customerId){
        Set<Customer> customers = new HashSet<>();
        customers.add(customerRepository.findById(customerId).get());
        return customers;
    }

    public Customer updateCustomerAddress(Customer customer){
        return new Customer();
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
