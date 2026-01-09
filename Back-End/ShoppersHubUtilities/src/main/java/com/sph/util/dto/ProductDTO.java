package com.sph.util.dto;



import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Data;

@Data
public class ProductDTO {
	
	private String pid;

    @NotBlank
    private String ownerId;

    @NotBlank
    private String name;

    @NotBlank
    private String categoryId;

    @NotBlank
    private String brand;

    @NotNull
    private LocalDate manufactureDate;

    private LocalDate expiryDate;

    private String description;

    @NotEmpty
    @Valid
    private List<VariantDTO> variants;

    @NotNull
    @Valid
    private PricingDTO pricing;

    @NotNull
    @Valid
    private InventoryDTO inventory;

    private Map<String, Object> attributes;

    private List<String> images;
}
