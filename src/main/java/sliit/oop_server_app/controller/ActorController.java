package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Actors;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.repository.ActorsRepository;
import sliit.oop_server_app.repository.CategoryRepository;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/actor")
public class ActorController {
    @Autowired
    private ActorsRepository actorsRepository;



    @GetMapping(produces = "application/json")
    public List<Actors> get() {
        List<Actors> actors = this.actorsRepository.findAll();
        return actors;
    }

    @PostMapping("/save")
    public List<Actors> saveUsers(@RequestBody List<Actors> actors) {
        if (actors.isEmpty()) {
            return Collections.emptyList();
        }
        return actorsRepository.saveAll(actors);
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







