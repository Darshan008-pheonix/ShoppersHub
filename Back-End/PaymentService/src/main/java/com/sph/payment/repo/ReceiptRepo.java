package com.sph.payment.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sph.payment.entity.Payment;
import com.sph.payment.entity.Receipt;

public interface ReceiptRepo extends MongoRepository<Receipt, String>{

}
