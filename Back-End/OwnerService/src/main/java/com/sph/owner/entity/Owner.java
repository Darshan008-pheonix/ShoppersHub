package com.sph.owner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.model.Address;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
	private String ownerId;
	private String ownerName;
	private String email;
	private long phoneNumber;
	private OwnerStatus status;
	private String companyName;
	private boolean isBlocked;
	private String gstNumber;
	private OwnerBankAccount ownerBankAccount;
	//private Address address;
}