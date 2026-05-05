package sliit.oop_server_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByMovies_Id(Integer id);

    void deleteByMovies_id(Integer id);
}
