package sliit.oop_server_app.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sliit.oop_server_app.entity.ActorsHasMovie;

@Repository
public interface Actors_has_moviesRepository extends JpaRepository<ActorsHasMovie, Integer> {

    void deleteAllByMoviesId(Integer id);
}
