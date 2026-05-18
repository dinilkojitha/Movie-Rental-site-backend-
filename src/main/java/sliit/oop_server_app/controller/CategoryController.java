package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.Service.CategoryService;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.entity.Movie;
import sliit.oop_server_app.repository.CategoryRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(produces = "application/json")
    public List<Category> get() {
       return categoryService.get();
    }

    @PostMapping("/save")
    public Category save(@RequestBody Category categories) {
        return categoryService.save(categories);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/filter/{id}")
    public List<Movie> getByFilter(@PathVariable int id){
        return categoryService.getByCategoryFilterd(id);
    }
}