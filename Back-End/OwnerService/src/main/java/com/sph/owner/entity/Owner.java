package com.sph.owner.entity;



import com.sph.util.model.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "owner",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "gst_number")
    }
    )
public class Owner {
	@Id
    @Column(name = "owner_id", nullable = false, length = 36)
    private String ownerId;

    @Column(name = "owner_name", nullable = false, length = 100)
    @NotBlank
    private String ownerName;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    @NotBlank
    @Email
    private String email;

    @Column(name = "phone_number", nullable = false)
    private long phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @NotNull
    private OwnerStatus status;

    @Column(name = "company_name", nullable = false, length = 150)
    @NotBlank
    private String companyName;

    @Column(name = "is_blocked", nullable = false)
    private Boolean blocked;

    @Column(name = "gst_number", nullable = false, unique = true, length = 15)
    @NotBlank
    @Pattern(
        regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$"
    )
    private String gstNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_account_id", nullable =false)
    private OwnerBankAccount ownerBankAccount;

    @Embedded
    private Address address;

}