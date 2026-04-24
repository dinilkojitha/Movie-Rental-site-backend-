package sliit.oop_server_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Movie;
import sliit.oop_server_app.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String>{

}
