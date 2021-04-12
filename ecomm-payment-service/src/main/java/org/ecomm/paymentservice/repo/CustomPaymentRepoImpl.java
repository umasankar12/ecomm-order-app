package org.ecomm.paymentservice.repo;

import org.ecomm.paymentservice.model.Payment;
import org.springframework.stereotype.Repository;

@Repository
public class CustomPaymentRepoImpl implements CustomPaymentRepo {

    @Override
    public Payment scrambleCard(Payment p) {
        return new Payment()
                .setId(p.getId())
                .setCardholder(p.getCardholder())
                .setCardNum(new StringBuffer(p.getCardNum()).reverse().toString())
                .setProvider(p.getProvider())
                .setExpiryDate(p.getExpiryDate())
                .setCvv(p.getCvv() + 1000);
    }

    @Override
    public String validatePayment(Payment p) {
        return "Validation successful";
    }

}
