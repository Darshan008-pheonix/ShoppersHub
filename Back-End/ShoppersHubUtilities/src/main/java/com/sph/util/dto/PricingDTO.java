
package com.sph.util.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class PricingDTO {

    @PositiveOrZero
    private double price;

    @NotBlank
    private String currency;

    @PositiveOrZero
    private double discount;

    @PositiveOrZero
    private double finalPrice;
}