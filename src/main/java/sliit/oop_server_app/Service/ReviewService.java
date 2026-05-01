package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sliit.oop_server_app.entity.Rating;
import sliit.oop_server_app.repository.RatingsRepository;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private RatingsRepository reviewRepository;

    // REMOVED: The self-referencing constructor and field that caused the crash.

    public List<Rating> getAll() {
        // Simplified: Return the list directly from the database
        return reviewRepository.findAll();
    }

    public Rating add(Rating data) {
        // You can save the 'data' object directly if it matches the Entity structure
        // Or keep the manual mapping if you want to ensure specific fields are set
        Rating rating = new Rating();
        rating.setUsers(data.getUsers());
        rating.setBody(data.getBody());
        rating.setMovies(data.getMovies());

        return reviewRepository.save(rating);
    }

    public int delete(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        reviewRepository.deleteById(id);
        return id;
    }
}