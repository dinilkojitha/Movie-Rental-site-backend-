package sliit.oop_server_app.DTO;

import lombok.Getter;
import lombok.Setter;
import sliit.oop_server_app.entity.Actor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MovieResponse {

    private int id;
    private String name;
    private String language;
    private String country;
    private String shortDescription ;
    private String description ;
    private String image;
    private String link;
    private String trailerLink;
    private int categoryId;
    private double price;
    private double imdb;
    private double tomato;
    private int viewcount;
    private Instant Hours;
    private List<Actor> actors;


}
