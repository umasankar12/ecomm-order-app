package org.ecomm.paymentservice.web;

import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.model.Payment;
import org.ecomm.foundation.repo.PaymentRepository;
import org.ecomm.paymentservice.facade.PaymentFacade;
import org.ecomm.paymentservice.facade.PaymentLogger;
import org.ecomm.paymentservice.model.PaymentRequest;
import org.ecomm.paymentservice.model.PaymentResponse;
import org.slf4j.Logger;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController()
public class PaymentController {

    PaymentRepository paymentRepo;

    PaymentLogger paymentLogger;

    private PaymentFacade paymentFacade;

    @AppLogger
    Logger logger;

    @Inject
    public PaymentController(PaymentRepository paymentRepo, PaymentLogger paymentLogger, PaymentFacade paymentFacade) {
        this.paymentRepo = paymentRepo;
        this.paymentLogger = paymentLogger;
        this.paymentFacade = paymentFacade;
    }

    @GetMapping("/home")
    public Map<String, Object> welcome() {
        var hm = new HashMap<String, Object>();
        hm.put("CurrentTime", LocalDateTime.now());
        hm.put("hello", "world");
        return hm;
    }

    @GetMapping("/get")
    public Iterable<Payment> getPayments() {
        return paymentRepo.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<Payment> getPayment(@PathVariable("id") Integer id) {
        return paymentRepo.findById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Payment addPaymentMethod(@RequestBody Payment payment) {
        logger.info("Adding new payment type {} from web ", payment.getType());
        PaymentRequest action = new PaymentRequest(
          "web: " + UUID.randomUUID(),
          "ADD_PAYMENT",
          String.format("ADD NEW PAYMENT METHOD: %s", payment.getType()),
          "TRUE",
          "Awaiting",
          new Timestamp(System.currentTimeMillis()),
          0.0,
          payment
        );
        //paymentRepo.validatePayment();
        Payment savedPayment = paymentRepo.save(payment);
        paymentLogger.logPaymentAction(action);
        return savedPayment;
    }


     @PostMapping(value = "/process", consumes = MediaType.APPLICATION_JSON_VALUE)
     public PaymentResponse processPayment(
       @RequestBody PaymentRequest paymentRequest) {

         Pair<Boolean, String> result = paymentFacade.processPayment(paymentRequest);
         paymentRequest.setStatus(result.getFirst().toString())
           .setReason(result.getSecond());
         paymentLogger.logPaymentAction(paymentRequest);
         return result.getFirst() ?
           PaymentResponse.createSuccessResponse(paymentRequest.getPayment(), result.getSecond()) :
           PaymentResponse.createFailureResponse(paymentRequest.getPayment(), -1, result.getSecond());
     }

/**
     @PostMapping(value = "/validate-payment", consumes = MediaType.APPLICATION_JSON_VALUE)
     public String validatePayment(@RequestBody PaymentRequest paymentRequest) {
     Optional<Payment> payment = paymentRepo.findById(paymentRequest.getPayment().getId());
     String validationResult = payment.map(paymentRepo::validatePayment).orElse("Invalid Payment");
     paymentLogger.logPaymentAction(
     new PaymentAction("Web",
     "validate-payment",
     "validation of new payment type",
     validationResult,
     validationResult,
     new Timestamp(System.currentTimeMillis()),
     0.0,
     paymentRequest.getPayment()
     )
     );

     return validationResult;
     }

     */
}
