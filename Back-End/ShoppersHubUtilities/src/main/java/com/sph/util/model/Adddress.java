package com.sph.util.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adddress {
	
	String doorno;
	String addressLine1;
	String addressLine2;
	String city;
	String state;
	String landmark;
	int pincode;
	

}
