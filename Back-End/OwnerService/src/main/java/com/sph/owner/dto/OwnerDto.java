package com.sph.owner.dto;

import com.sph.owner.entity.OwnerStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sph.util.model.Address;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {

	
	private String ownerId;

    @NotBlank(message = "Owner name is required")
    @Size(min = 3, max = 100, message = "Owner name must be between 3 and 100 characters")
    private String ownerName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number is required")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be 10 digits")
    private Long phoneNumber;

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 150)
    private String companyName;

    @NotBlank(message = "GST number is required")
    @Pattern(
        regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$",
        message = "Invalid GST number format"
    )
    private String gstNumber;

    @NotNull(message = "Address is required")
    @Valid
    private Address address;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Owner status is required")
    private OwnerStatus status;

    private Boolean blocked;

    @NotNull(message = "Bank account details are required")
    @Valid
    private OwnerBankAccountDto ownerAccount;

}
