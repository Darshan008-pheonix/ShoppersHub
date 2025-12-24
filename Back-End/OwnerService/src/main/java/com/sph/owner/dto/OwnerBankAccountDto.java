package com.sph.owner.dto;

import com.sph.owner.entity.Owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerBankAccountDto {
	private String bankName;
	private String branchName;
	private String ifscCode;
	private String branch;
	private String uiId;
	private String status;
	private Owner owid;
}
