package com.sph.product.dao;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.sph.product.config.MongoConfig;
import com.sph.product.entity.Product;
import com.sph.util.service.CommonUtils;

@Component
public class ProductDao {
	
	
	
	

    private final MongoTemplate mongoTemplate;

    public ProductDao(@Qualifier("productMongoTemplate") MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
	@CachePut(value=CommonUtils.Product_Cache, key="#p.getPid()")
	public Product insertProduct(Product p) {
		return mongoTemplate.insert(p);
	}

	@Cacheable(value=CommonUtils.Product_Cache, key="#pid")
	public Product getProduct(String pid) {
		Query query=new Query();
		query.addCriteria(Criteria.where("pid").is(pid));
		return mongoTemplate.findOne(query, Product.class);
	}
	


	public long reservePro(String pid, int qnt) {
	
		 Query query = new Query();
		    query.addCriteria(
		        Criteria.where("pid").is(pid)
		                .and("inventory.totalStock").gte(qnt)
		    );

		    Update update = new Update()
		            .inc("inventory.totalStock", -qnt)
		            .inc("inventory.reservedStock", qnt);

		    UpdateResult result = mongoTemplate.updateFirst(
		            query,
		            update,
		            Product.class
		    );
		    return result.getModifiedCount();
	}


	public long releasePro(String pid, int qnt) {
		 Query query = new Query();
		    query.addCriteria(
		        Criteria.where("pid").is(pid)
		    );

		    Update update = new Update()
		            .inc("inventory.totalStock", qnt)
		            .inc("inventory.reservedStock",-qnt);

		    UpdateResult result = mongoTemplate.updateFirst(
		            query,
		            update,
		            Product.class
		    );
		    return result.getModifiedCount();
	}
	
	@CacheEvict(value=CommonUtils.Product_Cache, key="#pid")
	public void deletedProduct(String pid) {
		Query query=new Query();
		query.addCriteria(Criteria.where("pid").is(pid));
		 mongoTemplate.remove(query, Product.class);
	}
	
	
	

}
