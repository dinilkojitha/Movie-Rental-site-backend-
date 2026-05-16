package sliit.oop_server_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Actor;

@Repository
public interface ActorsRepository extends JpaRepository<Actor, Integer> {

}
