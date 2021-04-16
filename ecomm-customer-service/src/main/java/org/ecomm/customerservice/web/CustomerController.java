package org.ecomm.customerservice.web;

import org.ecomm.customerservice.dto.AddressPayload;
import org.ecomm.customerservice.dto.CustomerPayload;
import org.ecomm.customerservice.dto.GuestCustomerPayload;
import org.ecomm.customerservice.service.AddressService;
import org.ecomm.customerservice.service.CustomerPaymentService;
import org.ecomm.customerservice.service.CustomerService;
import org.ecomm.customerservice.service.GuestCustomerService;
import org.ecomm.foundation.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.sql.Date;
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

    CustomerPaymentService customerPaymentService;

    @Value("${app.paymentService.getItem.url}")
    String urlForOPaymentService;

    @Inject
    public CustomerController(CustomerService customerService,
                              AddressService addressService,
                              GuestCustomerService guestCustomerService,
                              CustomerPaymentService customerPaymentService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.guestCustomerService = guestCustomerService;
        this.customerPaymentService = customerPaymentService;
    }

    @PostMapping("/addCustomer")
    public @ResponseBody Customer saveCustomer(@RequestBody CustomerPayload customer) {
        RestTemplate template = new RestTemplate();
        Payment payment = template.getForObject(urlForOPaymentService+"/"+customer.getPaymentId(), Payment.class);
        CustomerPayment customerPayment  = new CustomerPayment();
        Customer savedCustomer = customerService.saveCustomer(customer);
        customerPaymentService.saveCustomerPaymentDetails(savedCustomer,payment);
        return savedCustomer;
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
