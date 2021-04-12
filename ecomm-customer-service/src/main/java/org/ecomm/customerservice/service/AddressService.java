package org.ecomm.customerservice.service;

import org.ecomm.customerservice.dto.AddressPayload;
import org.ecomm.customerservice.dto.CustomerPayload;
import org.ecomm.foundation.model.Address;
import org.ecomm.foundation.model.Customer;
import org.ecomm.foundation.repo.AddressRepository;
import org.ecomm.foundation.repo.CustomerRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

@Named
public class AddressService {
    AddressRepository addressRepository;

    @Inject
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public Address saveAddress(AddressPayload addressPayload, Set<Customer> customers){

        return addressRepository.save(new Address()
                .setLine1(addressPayload.getLine1())
                .setLine2(addressPayload.getLine2())
                .setCity(addressPayload.getCity())
                .setState(addressPayload.getState())
                .setCountry(addressPayload.getCountry())
                .setNickname(addressPayload.getNickname())
                .setZipcode(addressPayload.getZipcode())
                .setType(addressPayload.getType())
                .setCustomers(customers));
    }

}
