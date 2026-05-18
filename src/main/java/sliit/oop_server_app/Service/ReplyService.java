package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import sliit.oop_server_app.DTO.ReviewListRequest;
import sliit.oop_server_app.DTO.ReviewListResponse;
import sliit.oop_server_app.entity.*;
import sliit.oop_server_app.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Reply> getAll() {
        return replyRepository.findAll();
    }

    public List<Reply> findByReview_Id(Integer id) {
        return replyRepository.findByReview_Id(id);
    }
    public Reply editReply(@RequestBody Reply reply) {
        replyRepository.findById(reply.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return replyRepository.save(reply);
    }

    public String deleteReply(Integer id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        replyRepository.deleteById(id);
        return "Reply with name " + reply.getUsers().getName() + " has been deleted";
    }

    public Reply replyViewedUpdate(Integer id){
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(!reply.getViewed()){
            reply.setViewed(true);
        }
        return replyRepository.save(reply);
    }

    public Reply saveNewReview(Reply reply) {
        Reply newreview = new Reply();
        newreview.setUsers(reply.getUsers());
        newreview.setReview(reply.getReview());
        newreview.setDislikes(0);
        newreview.setLike(0);
        newreview.setBody(reply.getBody());

        return replyRepository.save(newreview);
    }
}