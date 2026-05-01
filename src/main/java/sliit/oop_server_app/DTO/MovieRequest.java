package sliit.oop_server_app.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieRequest {


    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @Min(0)
    private int duration;

    @Min(0)
    private double price;

    @Min(0) @Max(10)
    private double imdb;

    @Min(0)
    private double tomato;

    @NotBlank
    private String language;

    private int viewcount;
    private String shortDescription ;
    private String description ;
    private String image;
    private String link;
    private String trailerLink;
    private String categoryId;
    private List<Actor> actors;


}
