package com.sph.util.dto;

import jakarta.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class InventoryDTO {

    @PositiveOrZero
    private int totalStock;
}