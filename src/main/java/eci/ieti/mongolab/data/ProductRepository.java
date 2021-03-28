package eci.ieti.mongolab.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import eci.ieti.mongolab.data.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

	Page<Product> findByDescriptionContaining(String description, Pageable pageable);
}
