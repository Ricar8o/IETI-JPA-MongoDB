package eci.ieti.mongolab.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import eci.ieti.mongolab.data.model.Todo;

public interface TodoRepository extends CrudRepository<Todo,Long>{
    Page<Todo> findByResponsible(String responsible, Pageable pageable);
}