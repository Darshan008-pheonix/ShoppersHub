package com.sph.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {
	
	
	  private final String uri="mongodb://localhost:27017";

	  @Bean
	    public MongoClient mongoClient(){
	    return MongoClients.create(uri);
	    }

	  
	  public MongoTemplate getConnection(String dbName) {
		  return new MongoTemplate(mongoClient(), dbName);
	  }


}
