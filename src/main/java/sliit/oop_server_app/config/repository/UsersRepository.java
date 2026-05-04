package sliit.oop_server_app.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    boolean existsByGmail(String gmail);
    Optional<User> findByGmail(String gmail);
}
