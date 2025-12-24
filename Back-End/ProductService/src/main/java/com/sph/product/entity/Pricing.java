package com.sph.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pricing {
	
	    private double price;
	    private String currency;
	    private double discount;
	    private double finalPrice;

}
