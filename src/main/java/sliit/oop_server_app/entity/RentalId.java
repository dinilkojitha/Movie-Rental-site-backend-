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
public class RentalId implements Serializable {
    private static final long serialVersionUID = 678512988485384801L;
    @NotNull
    @Column(name = "users_id", nullable = false)
    private Integer usersId;

    @NotNull
    @Column(name = "movies_id", nullable = false)
    private Integer moviesId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RentalId entity = (RentalId) o;
        return Objects.equals(this.usersId, entity.usersId) &&
                Objects.equals(this.moviesId, entity.moviesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, moviesId);
    }

}