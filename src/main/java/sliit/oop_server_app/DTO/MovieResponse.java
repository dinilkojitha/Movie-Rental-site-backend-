package sliit.oop_server_app.DTO;

import lombok.Getter;
import lombok.Setter;
import sliit.oop_server_app.entity.Actors;

import java.util.List;

@Getter
@Setter
public class MovieResponse {

    private String id;
    private String name;
    private String language;
    private String country;
    private String shortDescription ;
    private String description ;
    private String image;
    private String link;
    private String trailerLink;
    private String categoryId;
    private double price;
    private double imdb;
    private double tomato;
    private int viewcount;
    private int duration;
    private List<Actors> actors;


}
