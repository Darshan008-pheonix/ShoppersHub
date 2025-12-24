package com.sph.product.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Variant {

	private String variantId;
    private String sku;

    private double price;
    private int stock;

    private Map<String, Object> attributes;

    private VariantStatus status;
}
