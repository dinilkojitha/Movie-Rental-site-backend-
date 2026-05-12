package sliit.oop_server_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @Size(max = 150)
    @Column(name = "language", length = 150)
    private String language;

    @Size(max = 150)
    @Column(name = "country", length = 150)
    private String country;

    @Column(name = "hours")
    private Double hours;

    @Size(max = 255)
    @Column(name = "shortdescription")
    private String shortdescription;

    @Size(max = 1500)
    @Column(name = "description", length = 1500)
    private String description;

    @Size(max = 1500)
    @Column(name = "image", length = 1500)
    private String image;

    @Size(max = 500)
    @Column(name = "link", length = 500)
    private String link;

    @Size(max = 500)
    @Column(name = "trailerlink", length = 500)
    private String trailerlink;

    @Column(name = "imdb")
    private Double imdb;

    @Column(name = "tomato")
    private Double tomato;

    @Column(name = "viewcount")
    private Integer viewcount;

    @Column(name = "year")
    private Integer year;

    @Column(name = "price")
    private Double price;

    @Column(name = "ratings")
    private Integer ratings;

}