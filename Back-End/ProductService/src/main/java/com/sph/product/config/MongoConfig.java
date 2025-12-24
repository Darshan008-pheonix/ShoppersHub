package com.sph.product.config;




import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {


    private String uri="mongodb://localhost:27017";

  @Bean
    public MongoClient mongoClient(){
    return MongoClients.create(uri);
    }

    public MongoTemplate mongoTemplate(String database){
      return new MongoTemplate(mongoClient(),database+"db");
    }

}