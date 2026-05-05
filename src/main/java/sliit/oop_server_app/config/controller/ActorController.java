package sliit.oop_server_app.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Actor;
import sliit.oop_server_app.repository.ActorsRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/actor")
public class ActorController {

    @Autowired
    private ActorsRepository actorsRepository;

    @GetMapping(produces = "application/json")
    public List<Actor> get() {
        return this.actorsRepository.findAll();
    }

    @PostMapping("/save")
    public List<Actor> saveUsers(@RequestBody List<Actor> actors) {
        if (actors.isEmpty()) {
            return Collections.emptyList();
        }
        return actorsRepository.saveAll(actors);
    }

    // UPDATE: Admin function
    @PutMapping("/update/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable int id, @RequestBody Actor actorDetails) {
        Optional<Actor> actor = actorsRepository.findById(id);

        if (actor.isPresent()) {
            Actor existingActor = actor.get();
            existingActor.setName(actorDetails.getName());
            existingActor.setImage(actorDetails.getImage());
            existingActor.setDescription(actorDetails.getDescription());
            return ResponseEntity.ok(actorsRepository.save(existingActor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Admin function
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable int id) {
        if (actorsRepository.existsById(id)) {
            actorsRepository.deleteById(id);
            return ResponseEntity.ok("Actor deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

