package c3.msmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import c3.msmb.model.Comment;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIdPublicationId(Long idPublication);
}