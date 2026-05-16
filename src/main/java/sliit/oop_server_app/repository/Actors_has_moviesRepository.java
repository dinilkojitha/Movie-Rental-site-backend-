package sliit.oop_server_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sliit.oop_server_app.entity.ActorsHasMovie;

import java.util.List;

@Repository
public interface Actors_has_moviesRepository extends JpaRepository<ActorsHasMovie, Integer> {

    void deleteAllByMoviesId(Integer id);

    List<ActorsHasMovie> findByMovies_Id(Integer moviesId);
}
