package sliit.oop_server_app.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sliit.oop_server_app.entity.Actor;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.entity.Movie;
import sliit.oop_server_app.entity.User;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReviewListRequest {
    private String body;
    private Integer likes;
    private Integer dislikes;
    private Integer usersid;
    private Integer moviesid;
    private LocalDate date;


}
