package com.sph.owner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.model.Address;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRequestDTo {
	private String ownerName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String gstNumber;

    private Address address;
    private OwnerBankAccountDto ownerAccount;
}
