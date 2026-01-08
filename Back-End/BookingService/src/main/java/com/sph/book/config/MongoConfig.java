package com.sph.book.config;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {


    private String uri="mongodb://localhost:27018";

  @Bean
    public MongoClient mongoClient(){
    return MongoClients.create(uri);
    }

  @Primary
  @Bean(name = "bookingsMongoTemplate")
  public MongoTemplate bookingsMongoTemplate(MongoClient mongoClient) {
      return new MongoTemplate(mongoClient, "bookingsDb");
  }

  @Bean(name = "counterMongoTemplate")
  public MongoTemplate counterMongoTemplate(MongoClient mongoClient) {
      return new MongoTemplate(mongoClient, "counterDb");
  }
}