package sliit.oop_server_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Movies;
import sliit.oop_server_app.entity.Users;

import java.util.List;

@Repository
public interface MoviesRepository extends MongoRepository<Movies, String>{
    List<Movies> getMoviesByCategoryid(String categoryid);
}
