package com.sph.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserBankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountHolderName;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String accountNumber; // encrypt this

    @Column(nullable = false)
    private String ifsc;

    @Column(nullable = false)
    private String country;

    private boolean verified;

    private boolean defaultAccount;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_account_id", nullable = false)
//    private UserAccount userAccount;
}

