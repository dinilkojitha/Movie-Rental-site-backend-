package sliit.oop_server_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Reply;
import sliit.oop_server_app.entity.Review;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findByReview_Id(Integer id);
    List<Reply> findByUsers_Id(Integer id);
    void deleteByMovies_id(Integer id);


}
