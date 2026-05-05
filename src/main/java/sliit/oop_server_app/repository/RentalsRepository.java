package sliit.oop_server_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Rental;

@Repository
public interface RentalsRepository extends JpaRepository<Rental, Integer> {

}
