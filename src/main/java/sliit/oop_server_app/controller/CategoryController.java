package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(produces = "application/json")
    public List<Category> get() {
        return categoryRepository.findAll();
    }

    @PostMapping("/save")
    public Category saveUsers(@RequestBody Category categories) {
        return categoryRepository.save(categories);
    }

    // DELETE: Admin function
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("Category deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}