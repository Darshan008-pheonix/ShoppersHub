package com.sph.book.service;

import com.sph.util.dto.PaymentAndRecpietDto;

public interface ReceiptService {
	String sendReceipt(PaymentAndRecpietDto event);
	

}
