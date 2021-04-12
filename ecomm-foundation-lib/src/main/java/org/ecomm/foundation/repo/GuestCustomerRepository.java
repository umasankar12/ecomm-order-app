package org.ecomm.foundation.repo;

import org.ecomm.foundation.model.Customer;
import org.ecomm.foundation.model.GuestCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestCustomerRepository extends JpaRepository<GuestCustomer, Integer> {
}
