package org.ecomm.paymentservice.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecomm.foundation.model.PaymentHistory;
import org.ecomm.foundation.repo.PaymentHistoryRepository;
import org.ecomm.paymentservice.model.PaymentRequest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class PaymentLoggerImpl implements PaymentLogger {

    PaymentHistoryRepository repository;

    @Inject
    public PaymentLoggerImpl(PaymentHistoryRepository paymentHistoryRepository) {
        this.repository = paymentHistoryRepository;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public PaymentHistory logPaymentAction(PaymentRequest action) {
        try {
            return repository.save(new PaymentHistory()
              .setAction(action.getAction())
              .setActionDetail(String.format("ADD NEW PAYMENT METHOD: %s", action.getAction()))
              .setEntryTime(action.getEntryTime())
              .setRequestId(action.getSource())
              .setStatus(true)
              .setEntryData(new ObjectMapper().writeValueAsString(action.getPayment()))
            );

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
