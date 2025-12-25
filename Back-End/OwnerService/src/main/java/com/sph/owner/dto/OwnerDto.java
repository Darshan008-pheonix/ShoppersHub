package com.sph.owner.dto;

import com.sph.owner.entity.OwnerStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sph.util.model.Address;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
	private String ownerName;
    private String email;
    private long phoneNumber;
    private String companyName;
    private String gstNumber;
    private Address address;
    private OwnerStatus status;
    private boolean isBlocked;
    private OwnerBankAccountDto ownerAccount;
}
