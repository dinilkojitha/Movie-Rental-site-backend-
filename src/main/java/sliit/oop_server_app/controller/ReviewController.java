package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.DTO.ReviewListRequest;
import sliit.oop_server_app.DTO.ReviewListResponse;
import sliit.oop_server_app.Service.ReplyService;
import sliit.oop_server_app.Service.ReviewService;
import sliit.oop_server_app.entity.Reply;
import sliit.oop_server_app.entity.Review;
import sliit.oop_server_app.repository.ReviewRepository;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReplyService replyService;

    @GetMapping(produces = "application/json")
    public List<ReviewListResponse> get() {
       return reviewService.getAll();
    }

    @GetMapping("{id}")
    public List<Review> get(@PathVariable int id) {
        return reviewService.getbyMovieId(id);
    }

    @GetMapping("/user/{id}")
    public List<Review> getByReviewId(@PathVariable int id) {
        return reviewService.getbyUserId(id);
    }

    @PostMapping("/save")
    public Review saveReview(@RequestBody ReviewListRequest reviews) {
        return reviewService.addnewReview(reviews);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteReview(@PathVariable int id) {
        return reviewService.delete(id);
    }
    @PutMapping("/update")
    public Review updateReview(@RequestBody Review reviews) {
        return reviewService.update(reviews , reviews.getId());
    }

    @PutMapping("/like/{id}")
    public Review like(@PathVariable int id) {
        return reviewService.like(id);
    }

    @PutMapping("/unlike/{id}")
    public Review unlike(@PathVariable int id) {
        return reviewService.dislike(id);
    }

    @PostMapping("/reply")
    public Reply addreply(@RequestBody Reply reply) {
        return replyService.saveNewReview(reply);
    }
    @GetMapping("/reply/{id}")
    public List<Reply> getreviews(@PathVariable int id) {
        return replyService.findByReview_Id(id);
    }

    @PutMapping("/reply/edit")
    public Reply editreply(@RequestBody Reply reply) {
        return replyService.editReply(reply);
    }

    @DeleteMapping("reply/delete/{id}")
    public String deletereply(@PathVariable int id) {
        return replyService.deleteReply(id);
    }



}







