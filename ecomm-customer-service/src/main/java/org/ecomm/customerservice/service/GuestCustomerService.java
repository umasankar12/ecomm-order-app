package org.ecomm.customerservice.service;

import org.ecomm.customerservice.dto.GuestCustomerPayload;
import org.ecomm.foundation.model.GuestCustomer;
import org.ecomm.foundation.repo.GuestCustomerRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class GuestCustomerService {
    GuestCustomerRepository guestCustomerRepository;

    @Inject
    public GuestCustomerService(GuestCustomerRepository guestCustomerRepository) {
        this.guestCustomerRepository = guestCustomerRepository;
    }

    public GuestCustomerRepository getGuestCustomerRepository() {
        return guestCustomerRepository;
    }

    public GuestCustomer saveGuestCustomer(GuestCustomerPayload guestCustomerPayload){
        return guestCustomerRepository.save(new GuestCustomer().setFirstName(guestCustomerPayload.getFirstName())
                .setLastName(guestCustomerPayload.getLastName())
                .setEmail(guestCustomerPayload.getEmail())
                .setPhoneNum(guestCustomerPayload.getPhoneNum())
                .setGender(guestCustomerPayload.getGender())
        );
    }
}
