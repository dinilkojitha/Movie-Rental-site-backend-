package sliit.oop_server_app.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sliit.oop_server_app.entity.Movie;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByNameContainingIgnoreCase(@NotNull String name);
    List<Movie> findByYearEquals(@NotNull Integer year);
    List<Movie> findByImdbGreaterThanEqual(@NotNull Double imdb);
    List<Movie> findByViewcountGreaterThanEqual(@NotNull Integer viewcount);

    @Query("SELECT MAX(m.viewcount) FROM Movie m")
    Integer findMaxViewcount();

    List<Movie> findByOrderByViewcountDesc();
    List<Movie> findByOrderByViewcountAsc();

}
