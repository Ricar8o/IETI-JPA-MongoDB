package eci.ieti.mongolab.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import eci.ieti.mongolab.data.model.Todo;

public interface TodoRepository extends CrudRepository<Todo,Long>{
    Page<Todo> findByResponsible(String responsible, Pageable pageable);
    
    @Query("{ 'dueDate' : { $lt: ?0 } }")
    List<Todo> findBeforeDueDate(Date date);

    @Query("{ 'responsible' : { $eq: ?0 } , 'priority': { $gte: ?1 } }")
    List<Todo> findByResponsibleAndPrioritygte(String responsible, int priority);
    
    @Query("{ '$expr': { '$gt': [ { '$strLenCP': '$description' }, ?0 ] } }")
    List<Todo> findByDescriptionLengthgt(int length);
}