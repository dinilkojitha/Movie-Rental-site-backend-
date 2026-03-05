package sliit.oop_server_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Movies;

@Repository
public interface CategoryRepository extends MongoRepository<Movies, String>{
}
