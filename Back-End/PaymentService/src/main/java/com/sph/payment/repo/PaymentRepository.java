package com.sph.payment.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sph.payment.entity.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    Optional<Payment> findByTransactionId(String transactionId);
}
