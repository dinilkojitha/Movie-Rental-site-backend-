package sliit.oop_server_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Rentals;

@Repository
public interface RentalsRepository extends MongoRepository<Rentals, String>{
    boolean existsByGmail(String gmail);
}
