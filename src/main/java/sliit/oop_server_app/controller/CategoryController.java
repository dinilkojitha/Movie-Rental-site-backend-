package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.entity.Users;
import sliit.oop_server_app.repository.CategoryRepository;
import sliit.oop_server_app.repository.UsersRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;



    @GetMapping(produces = "application/json")
    public List<Category> get() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories;
    }

    @PostMapping("/save")
    public List<Category> saveUsers(@RequestBody List<Category> categories) {
        if (categories.isEmpty()) {
            return Collections.emptyList();
        }
        System.out.print(categories);
        return categoryRepository.saveAll(categories);
    }

//    @PutMapping("/update")
//    public ResponseEntity<List<FinalPage>> updateFinalPages(@RequestBody List<FinalPage> finals) {
//        for (FinalPage fine : finals) {
//            Optional<FinalPage> existing = finalPageRepository.findById(fine.getId());
//            if (existing.isPresent()) {
//                FinalPage updated = existing.get();
//                updated.setTitle(fine.getTitle());
//                updated.setDescription(fine.getDescription());
//                updated.setImage(fine.getImage());
//                updated.setAbout_detail(fine.getAbout_detail());
//                updated.setAbout_title(fine.getAbout_title());
//                // Add any other fields you want to update
//                finalPageRepository.save(updated);
//            }
//        }
//        // Return the full MainPage list (or filter as needed)
//        List<FinalPage> mainPages = finalPageRepository.findAll();
//        return ResponseEntity.ok(mainPages);
//    }



}







