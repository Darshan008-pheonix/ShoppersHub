package com.sph.util.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
	
	String doorno;
	String addressLine1;
	String addressLine2;
	String city;
	String state;
	String landmark;
	int pincode;
	

}
