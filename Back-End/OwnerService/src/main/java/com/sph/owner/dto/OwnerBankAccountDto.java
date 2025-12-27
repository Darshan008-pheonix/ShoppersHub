package com.sph.owner.dto;

import com.sph.owner.entity.OwnerStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerBankAccountDto {

    @NotBlank(message = "Bank name is mandatory")
    @Size(max = 100, message = "Bank name must be less than 100 characters")
    private String bankName;

    @NotBlank(message = "Branch name is mandatory")
    @Size(max = 100, message = "Branch name must be less than 100 characters")
    private String branchName;

    @NotBlank(message = "IFSC code is mandatory")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "IFSC code must be valid")
    private String ifscCode;

    @NotBlank(message = "Branch is mandatory")
    @Size(max = 100, message = "Branch must be less than 100 characters")
    private String branch;

    @NotBlank(message = "UI ID is mandatory")
    private String uiId;

    @NotNull(message = "Status is required")
    private OwnerStatus status;
}
