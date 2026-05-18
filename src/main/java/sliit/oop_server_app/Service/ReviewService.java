package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sliit.oop_server_app.DTO.ReviewListRequest;
import sliit.oop_server_app.DTO.ReviewListResponse;
import sliit.oop_server_app.entity.*;
import sliit.oop_server_app.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private CategoryHasMovieRepository categoryHasMovieRepository;

    public Review addnewReview(ReviewListRequest review) {
        Review newReview = new Review();
        newReview.setBody(review.getBody());

        // Default null likes/dislikes to 0 safely
        newReview.setLikes(review.getLikes() != null ? review.getLikes() : 0);
        newReview.setDislikes(review.getDislikes() != null ? review.getDislikes() : 0);
        newReview.setDate(review.getDate());

        // 1. Fix: Look up user using usersid
        User user = usersRepository.findById(review.getUsersid())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + review.getUsersid()));
        newReview.setUsers(user);

        // 2. Fix: Look up movie using moviesid
        Movie movie = movieRepository.findById(review.getMoviesid())
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + review.getMoviesid()));
        newReview.setMovies(movie);

        // 3. Save and return the fully populated object
        return reviewRepository.save(newReview);
    }

    public List<ReviewListResponse> getAll() {

        List<Review> reviews =  reviewRepository.findAll();
        List<ReviewListResponse> reviewListResponses = new ArrayList<>();
        for (Review review : reviews) {
            ReviewListResponse reviewListResponse = new ReviewListResponse();
            reviewListResponse.setId(review.getId());
            reviewListResponse.setBody(review.getBody());
            reviewListResponse.setLikes(review.getLikes());
            reviewListResponse.setDislikes(review.getDislikes());
            reviewListResponse.setUsers(review.getUsers());
            reviewListResponse.setMovies(review.getMovies());
            List<Category> categoryList = new ArrayList<>();
            List<CategoryHasMovie> categoryHasMovies = categoryHasMovieRepository.findByMovies_id(review.getMovies().getId());
            for(CategoryHasMovie categoryHasMovie : categoryHasMovies) {
                categoryList.add(categoryHasMovie.getCategory());
            }
            reviewListResponse.setCategoryList(categoryList);
            reviewListResponse.setDate(review.getDate());

            reviewListResponses.add(reviewListResponse);
        }
        return reviewListResponses;
    }

    public List<Review> getbyMovieId(int id) {
        return reviewRepository.findByMovies_Id(id);
    }

    public List<Review> getbyUserId(int id) {
        return reviewRepository.findByUsers_Id(id);
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

    public String delete(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        List<Reply> rip = replyRepository.findByReview_Id(id);
        replyRepository.deleteAll(rip);
        reviewRepository.deleteById(id);
        return "Deleted Successful";
    }


    public List<Review> sortByLikesTop5() {
        return reviewRepository.findTop5ByOrderByLikesDesc();
    }
}