package org.ecomm.customerservice.web;

import org.ecomm.customerservice.dto.AddressPayload;
import org.ecomm.customerservice.dto.CustomerPayload;
import org.ecomm.customerservice.dto.GuestCustomerPayload;
import org.ecomm.customerservice.service.AddressService;
import org.ecomm.customerservice.service.CustomerService;
import org.ecomm.customerservice.service.GuestCustomerService;
import org.ecomm.foundation.model.Address;
import org.ecomm.foundation.model.Customer;
import org.ecomm.foundation.model.GuestCustomer;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    CustomerService customerService;

    AddressService addressService;

    GuestCustomerService guestCustomerService;

    @Inject
    public CustomerController(CustomerService customerService, AddressService addressService, GuestCustomerService guestCustomerService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.guestCustomerService = guestCustomerService;
    }

    @PostMapping("/addCustomer")
    public Customer saveCustomer(@RequestBody CustomerPayload customer) {
        return customerService.saveCustomer(customer);
    }

    @PostMapping("/addAddress")
    public Address saveAddress(@RequestBody AddressPayload addressPayload) {
        Set<Customer> customers = new HashSet<>();
        Customer  customer = customerService.getCustomerRepository().findById(addressPayload.getCustomerId()).get();
        customers.add(customer);
        return addressService.saveAddress(addressPayload, customers);
    }

    @GetMapping("/getCustomers")
    public List<Customer> getCustomers() {
        return customerService.getCustomerRepository().findAll();
    }

    @GetMapping("/get/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) {
        List<Address> addresses = addressService.getAddressRepository().findAll();
        return customerService.getCustomerRepository().findById(id).get();
    }

    @GetMapping("/getAllAddress")
    public List<Address> getAllAddress() {
        return addressService.getAddressRepository().findAll();
    }

    @GetMapping("/getAddress/{id}")
    public Address getAddressById(@PathVariable("id") int id) {
        return addressService.getAddressRepository().findById(id).orElse(new Address());
    }

    @PostMapping("/addGuestCustomer")
    public GuestCustomer saveGuestCustomer(@RequestBody GuestCustomerPayload guestCustomerPayload){
        return guestCustomerService.saveGuestCustomer(guestCustomerPayload);
    }
}
