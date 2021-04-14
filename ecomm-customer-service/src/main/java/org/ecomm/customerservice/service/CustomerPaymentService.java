package org.ecomm.customerservice.service;

import org.ecomm.foundation.model.Customer;
import org.ecomm.foundation.model.CustomerPayment;
import org.ecomm.foundation.model.Payment;
import org.ecomm.foundation.repo.CustomerPaymentRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class CustomerPaymentService {
    CustomerPaymentRepository customerPaymentRepository;

    public CustomerPaymentService(CustomerPaymentRepository customerPaymentRepository) {
        this.customerPaymentRepository = customerPaymentRepository;
    }

    public void saveCustomerPaymentDetails(Customer customer, Payment payment){
        customerPaymentRepository.save(new CustomerPayment()
                .setPayment(payment)
                .setCustomerByCustomerId(customer)
                .setStartDate(new Date(System.currentTimeMillis()))
                );
    }
}
