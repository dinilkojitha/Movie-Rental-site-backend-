package sliit.oop_server_app.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sliit.oop_server_app.entity.Actor;
import sliit.oop_server_app.entity.Category;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class MovieResponse {

    private Integer id;
    private String name;
    private String language;
    private String country;
    private String shortDescription ;
    private String description ;
    private String image;
    private String link;
    private String trailerLink;
    private List<Category> categoryId;
    private double price;
    private double imdb;
    private double tomato;
    private Integer viewcount;
    private double Hours;
    private Integer year;
    private Integer ratings;
    private List<Actor> actors;


}
