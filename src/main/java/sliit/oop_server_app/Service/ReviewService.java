package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sliit.oop_server_app.entity.Review;
import sliit.oop_server_app.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // REMOVED: The self-referencing constructor and field that caused the crash.

    public List<Review> getAll() {
        // Simplified: Return the list directly from the database
        return reviewRepository.findAll();
    }

    public Review add(Review data) {
        // You can save the 'data' object directly if it matches the Entity structure
        // Or keep the manual mapping if you want to ensure specific fields are set
        Review review = new Review();
        review.setName(data.getName());
        review.setBody(data.getBody());
        review.setMoviename(data.getMoviename());
        review.setMovieid(data.getMovieid());

        return reviewRepository.save(review);
    }

    public String delete(String id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        reviewRepository.deleteById(id);
        return id;
    }
}