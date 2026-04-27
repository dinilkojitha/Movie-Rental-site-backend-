package sliit.oop_server_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "category")
public class Category {
    @Id
    private String id;
    private String name;
    private String img;





}
