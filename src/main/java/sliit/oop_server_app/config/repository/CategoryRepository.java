package sliit.oop_server_app.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
