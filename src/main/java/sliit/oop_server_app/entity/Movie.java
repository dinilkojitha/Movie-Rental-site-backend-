package sliit.oop_server_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@Document(collection = "movies")
public class Movie {

    @Id
    private String id;
    private String categoryId;
    private String name;
    private String language;
    private String country;
    private String shortDescription ;
    private String description ;
    private String image;
    private String link;
    private String trailerLink;
    private double price;
    private int year;
    private double imdb;
    private double tomato;
    private int viewcount;
    private int duration;
    private List<Actors> actors;

}