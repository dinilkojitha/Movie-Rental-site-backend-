package sliit.oop_server_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document(collection = "review")
public class Review {
    @Id
    private String id;
    private String movieid;
    private String body;
    private String name;
    private String moviename;
}
