package eci.ieti.mongolab.data;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import eci.ieti.mongolab.data.model.User;

public interface UserRepository extends MongoRepository<User,String> {

    User findByEmail(String name);

    List<User> findByName(String name);
}
