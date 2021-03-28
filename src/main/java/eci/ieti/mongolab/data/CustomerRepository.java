package eci.ieti.mongolab.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import eci.ieti.mongolab.data.model.Customer;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

}