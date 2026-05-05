package sliit.oop_server_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 45)
    @Column(name = "language", length = 45)
    private String language;

    @Size(max = 45)
    @Column(name = "country", length = 45)
    private String country;

    @Column(name = "hours")
    private Double hours;

    @Size(max = 45)
    @Column(name = "shortdescription", length = 45)
    private String shortdescription;

    @Size(max = 45)
    @Column(name = "description", length = 45)
    private String description;

    @Lob
    @Column(name = "image")
    private String image;

    @Size(max = 45)
    @Column(name = "link", length = 45)
    private String link;

    @Size(max = 45)
    @Column(name = "trailerlink", length = 45)
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "ratings")
    private Integer ratings;

}