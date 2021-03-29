package eci.ieti.mongolab;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;


@Configuration
public class AppConfiguration {

    @Bean
    public MongoDatabaseFactory mongoDbFactory() throws Exception {
        String uri = "mongodb+srv://ricardo:LqpLBxHkDyikucBi@cluster-ieti.htfx4.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

        MongoClient mongoClient = MongoClients.create(uri);

        return new SimpleMongoClientDatabaseFactory( mongoClient, "test");

    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

        return mongoTemplate;

    }

}