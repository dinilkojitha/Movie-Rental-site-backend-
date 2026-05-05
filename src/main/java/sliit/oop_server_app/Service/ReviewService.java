package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sliit.oop_server_app.entity.Review;
import sliit.oop_server_app.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public Review add(Review data) {
        Review rating = new Review();
        rating.setUsers(data.getUsers());
        rating.setBody(data.getBody());
        rating.setMovies(data.getMovies());


        return reviewRepository.save(rating);
    }

    public Review update(Review data, int id) {
        // Use .map() for a cleaner way to handle Optional or throw an error
        return reviewRepository.findById(id)
                .map(existingReview -> {
                    existingReview.setBody(data.getBody());
                    existingReview.setMovies(data.getMovies());
                    existingReview.setDislikes(data.getDislikes());
                    existingReview.setUsers(data.getUsers());
                    // Note: Usually, we don't update the User associated with a review
                    // for integrity, but you can add existingReview.setUsers() if needed.
                    return reviewRepository.save(existingReview);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
    }

    public Review like(int id) {
        return reviewRepository.findById(id)
                .map(existingReview -> {
                    // Fixed: Incrementing likes, not dislikes
                    existingReview.setLikes(existingReview.getLikes() + 1);
                    return reviewRepository.save(existingReview);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
    }

    public Review dislike(int id) {
        return reviewRepository.findById(id)
                .map(existingReview -> {
                    // Fixed: Correct syntax for conditional logic
                    int currentDislikes = existingReview.getDislikes();
                    existingReview.setDislikes(currentDislikes > 0 ? currentDislikes + 1 : 1);

                    // Note: If you just want to increment regardless of the current count,
                    // existingReview.setDislikes(existingReview.getDislikes() + 1) is sufficient.

                    return reviewRepository.save(existingReview);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
    }

    public int delete(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        reviewRepository.deleteById(id);
        return id;
    }
}