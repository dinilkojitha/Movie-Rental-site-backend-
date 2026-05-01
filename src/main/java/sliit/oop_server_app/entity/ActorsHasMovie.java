package sliit.oop_server_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "actors_has_movies")
public class ActorsHasMovie {
    @EmbeddedId
    private ActorsHasMovieId id;

    @MapsId("actorsId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actors_id", nullable = false)
    private Actor actors;

    @MapsId("moviesId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movies_id", nullable = false)
    private Movie movies;

}