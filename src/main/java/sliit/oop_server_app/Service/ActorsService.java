package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Actor;
import sliit.oop_server_app.repository.ActorsRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ActorsService {
    @Autowired
    private ActorsRepository actorsRepository;

    public List<Actor> get() {
        return this.actorsRepository.findAll();
    }


    public List<Actor> saveUsers(@RequestBody List<Actor> actors) {
        if (actors.isEmpty()) {
            return Collections.emptyList();
        }
        return actorsRepository.saveAll(actors);
    }

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


    public ResponseEntity<String> deleteActor(@PathVariable int id) {
        if (actorsRepository.existsById(id)) {
            actorsRepository.deleteById(id);
            return ResponseEntity.ok("Actor deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
