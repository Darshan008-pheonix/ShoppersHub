package com.sph.owner.dto;
import com.sph.owner.entity.OwnerStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerSearchDto {

    private String ownerId;
    private String ownerName;
    private String companyName;
    private String email;
    private String phoneNumber;
    private String gstNumber;
    private OwnerStatus status;
}
