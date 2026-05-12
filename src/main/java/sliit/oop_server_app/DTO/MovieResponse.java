package sliit.oop_server_app.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sliit.oop_server_app.entity.Actor;
import sliit.oop_server_app.entity.Category;

import java.util.List;

@Getter
@Setter
@NotNull
public class MovieResponse {

    private Integer id;
    private Integer viewcount;
    private Integer ratings;
    private Integer year;

    private String shortDescription;
    private String description;
    private String trailerLink;
    private String language;
    private String country;
    private String image;
    private String name;
    private String link;

    private Double tomato;
    private double price;
    private double hours;
    private Double imdb;

    private List<Category> categoryId;
    private List<Actor> actors;


}
