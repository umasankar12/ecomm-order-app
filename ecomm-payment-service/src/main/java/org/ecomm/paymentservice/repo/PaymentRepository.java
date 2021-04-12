package org.ecomm.paymentservice.repo;

import org.ecomm.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("paymentRepo")
public interface PaymentRepository extends JpaRepository<Payment, Integer>, CustomPaymentRepo {
}
