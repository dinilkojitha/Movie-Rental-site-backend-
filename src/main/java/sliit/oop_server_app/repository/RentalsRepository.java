package sliit.oop_server_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Rental;

import java.util.List;

@Repository
public interface RentalsRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findByUsers_Id(Integer id);

}
