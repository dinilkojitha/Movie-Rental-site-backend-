package sliit.oop_server_app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieUpdateRequest {
    private String name;
    private int duration;
    private String categoryId;
    private String country;
    private String language;
    private double imdb;
    private double tomato;
    private int viewcount;
    private double price;
    private String description;
    private String shortDescription;
    private String image;
    private String link;
    private String trailerLink;
}