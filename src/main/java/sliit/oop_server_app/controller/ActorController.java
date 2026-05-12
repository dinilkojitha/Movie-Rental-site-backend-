package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.Service.ActorsService;
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
    private ActorsService actorsService;

    @GetMapping(produces = "application/json")
    public List<Actor> get() {
       return actorsService.get();
    }

    @PostMapping("/save")
    public List<Actor> saveUsers(@RequestBody List<Actor> actors) {
       return actorsService.saveUsers(actors);
    }

    // UPDATE: Admin function
    @PutMapping("/update/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable int id, @RequestBody Actor actorDetails) {
        return actorsService.updateActor(id, actorDetails);
    }

    // DELETE: Admin function
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable int id) {
       return actorsService.deleteActor(id);
    }
}

