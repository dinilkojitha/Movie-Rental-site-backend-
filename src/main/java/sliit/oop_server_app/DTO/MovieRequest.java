package sliit.oop_server_app.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import sliit.oop_server_app.entity.Actor;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.entity.Movie;

import java.util.List;


@Getter
@Setter
public class MovieRequest {

    private String name;
    private String country;
    private int duration;
    private double price;
    private Double imdb;
    private Double tomato;
    private String language;
    private int viewcount;
    private String shortDescription;
    private String description ;
    private String image;
    private String link;
    private String trailerlink;
    private double hours;
    private Integer ratings;
    private List<Integer> actorsId;
    private Integer year;
    private List<Integer> categoryId;
}


