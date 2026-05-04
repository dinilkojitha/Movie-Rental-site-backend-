package sliit.oop_server_app.config.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sliit.oop_server_app.entity.Actor;
import sliit.oop_server_app.entity.Movie;

@Getter
@Setter
@Entity
@Table(name = "actors_has_movies")
public class ActorsHasMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actors_id", nullable = false)
    private Actor actors;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movies_id", nullable = false)
    private Movie movies;

}