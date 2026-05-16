package sliit.oop_server_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 80)
    @Column(name = "name", length = 80)
    private String name;

    @Size(max = 400)
    @Column(name = "img", length = 400)
    private String img;

}