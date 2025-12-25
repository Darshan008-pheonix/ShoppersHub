package com.sph.owner.entity;

import com.sph.util.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
	private Address address;
}