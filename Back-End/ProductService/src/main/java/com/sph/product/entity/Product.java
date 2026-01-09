package com.sph.product.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document

    @CompoundIndex(name = "seller_category_idx", 
                   def = "{'sellerId': 1, 'categoryId': 1}")
    @CompoundIndex(name = "status_expiry_idx",
                   def = "{'status': 1, 'expiryDate': 1}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	 @Id
	 private String pid; //sphp001

	    private String ownerId;

	    private String name;

	    private String categoryId;
	    private String brand;
	    
	    private LocalDate manufactureDate;
	    private LocalDate expiryDate;

	    private String description;
	    
	    
	    private List<Variant> variants;

	    private Pricing pricing;
	    private Inventory inventory;

	    private Map<String, Object> attributes;

	    private List<String> images;

	    private Rating rating;

	    private ProductStatus status;

	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
}
