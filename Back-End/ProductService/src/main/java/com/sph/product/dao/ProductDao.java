package com.sph.product.dao;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.sph.product.config.MongoConfig;
import com.sph.product.entity.Product;

@Component
public class ProductDao {
	
	
	
	

    private final MongoTemplate mongoTemplate;

    public ProductDao(@Qualifier("productMongoTemplate") MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
	
	public Product insertProduct(Product p) {
		Product obj = mongoTemplate.insert(p);
		return obj;
	}
	
	
	

}
