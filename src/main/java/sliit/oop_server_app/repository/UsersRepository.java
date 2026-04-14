package sliit.oop_server_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends MongoRepository<Users, String>{
    boolean existsByGmail(String gmail);
    Optional<Users> findByGmail(String gmail);
}
