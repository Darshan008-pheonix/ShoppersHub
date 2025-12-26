package com.sph.product.config;




import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {


    private String uri="mongodb://localhost:27017";

  @Bean
    public MongoClient mongoClient(){
    return MongoClients.create(uri);
    }

  @Primary
  @Bean(name = "productMongoTemplate")
  public MongoTemplate productMongoTemplate(MongoClient mongoClient) {
      return new MongoTemplate(mongoClient, "productDb");
  }

  @Bean(name = "counterMongoTemplate")
  public MongoTemplate counterMongoTemplate(MongoClient mongoClient) {
      return new MongoTemplate(mongoClient, "counterDb");
  }
}