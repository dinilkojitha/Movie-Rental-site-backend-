package sliit.oop_server_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ActorsHasMovieId implements Serializable {
    private static final long serialVersionUID = 637983249089019118L;
    @NotNull
    @Column(name = "actors_id", nullable = false)
    private Integer actorsId;

    @NotNull
    @Column(name = "movies_id", nullable = false)
    private Integer moviesId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ActorsHasMovieId entity = (ActorsHasMovieId) o;
        return Objects.equals(this.actorsId, entity.actorsId) &&
                Objects.equals(this.moviesId, entity.moviesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorsId, moviesId);
    }

}