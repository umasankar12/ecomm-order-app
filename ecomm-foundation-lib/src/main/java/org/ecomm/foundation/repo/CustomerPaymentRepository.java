package org.ecomm.foundation.repo;

import org.ecomm.foundation.model.Customer;
import org.ecomm.foundation.model.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Integer> {
}
