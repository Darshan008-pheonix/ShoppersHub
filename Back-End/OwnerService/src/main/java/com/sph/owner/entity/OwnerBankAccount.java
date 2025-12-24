package com.sph.owner.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerBankAccount {

	private Long accountNumber;
	private String bankName;
	private String branchName;
	private String ifscCode;
	private String branch;
	private String uiId;
	private String status;
	//Owner owid;
}
