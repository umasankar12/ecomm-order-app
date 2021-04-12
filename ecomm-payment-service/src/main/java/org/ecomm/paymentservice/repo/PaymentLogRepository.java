package org.ecomm.paymentservice.repo;

import org.ecomm.paymentservice.model.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentLogRepository extends JpaRepository<PaymentLog, Integer> {
}
