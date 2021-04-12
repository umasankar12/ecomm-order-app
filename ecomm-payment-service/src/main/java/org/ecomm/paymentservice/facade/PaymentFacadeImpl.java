package org.ecomm.paymentservice.facade;

import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.model.Payment;
import org.ecomm.foundation.repo.PaymentRepository;
import org.ecomm.paymentservice.model.PaymentRequest;
import org.slf4j.Logger;
import org.springframework.data.util.Pair;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class PaymentFacadeImpl implements PaymentFacade {

    @AppLogger
    Logger logger;

    PaymentRepository paymentRepository;

    @Inject
    public PaymentFacadeImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Pair<Boolean, String> processPayment(PaymentRequest request) {
        double amount = request.getAmount();
        int paymentId = request.getPayment().getId();
        logger.info("Payment requested for {} with amount {}", paymentId, amount);
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        //Dummy Logic
        if (amount < 0 || amount > 1000)
            return Pair.of(false, "invalid amount");
        return payment.map(p -> {
            if (p.getExpiryDate() == null || p.getExpiryDate().getTime() < System.currentTimeMillis())
                return Pair.of(false, "Card expired");

            logger.info("Dummy payment successful for {} - type: {}, provider: {}",
              p.getCardholder(),
              p.getType(),
              p.getProvider());

            return Pair.of(true, "payment successful");
        })
          .orElse(Pair.of(false, "Payment Not found"));
    }
}
