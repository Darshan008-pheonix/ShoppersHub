package com.sph.owner.dto;
import com.sph.owner.entity.OwnerStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerSearchDto {


    @Size(max = 36, message = "Owner ID must not exceed 36 characters")
    private String ownerId;

    @Size(min = 3, max = 100, message = "Owner name must be between 3 and 100 characters")
    private String ownerName;

    @Size(min = 2, max = 150, message = "Company name must be between 2 and 150 characters")
    private String companyName;

    @Email(message = "Invalid email format")
    @Size(max = 150)
    private String email;

    @Pattern(
        regexp = "^[6-9]{1}[0-9]{9}$",
        message = "Phone number must be exactly 10 digits"
    )
    private String phoneNumber;

    @Pattern(
        regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$",
        message = "Invalid GST number format"
    )
    private String gstNumber;

    private OwnerStatus status;

}
