package com.sph.util.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponseDto {
    private double itemPrice;
    private double tax;
    private double deliveryCharge;
    private double totalAmount;
    private String eta;
    private String ownerId;
}