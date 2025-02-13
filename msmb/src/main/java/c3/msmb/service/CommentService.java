package c3.msmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.exceptions.comment.GetCommentsByPublicationIdException;
import c3.msmb.exceptions.comment.GetCommentsException;
import c3.msmb.exceptions.comment.SaveCommentException;
import c3.msmb.model.Comment;
import c3.msmb.model.User;
import c3.msmb.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UserService userService;

    public List<Comment> getComments() {
        List<Comment> comments = commentRepository.findAll();
        if (comments.isEmpty()) {
            throw new GetCommentsException("Comments not found");
        }
        return comments;
    }

    public List<Comment> getCommentsByPublicationId(Long idPublication) {
        List<Comment> comments = commentRepository.findByIdPublicationId(idPublication);
        if (comments.isEmpty()) {
            throw new GetCommentsByPublicationIdException("Comments not found for publication: " + idPublication);
        }
        return comments;
    }

    public Comment saveComment(Comment comment) {
        try {
            Comment newComment = commentRepository.save(comment);
            User userComment = userService.getUserByUsername(comment.getUsername().getUsername());
            User userPublication = publicationService.getPublicationById(comment.getIdPublication().getId()).getUsername();
            if (!userComment.getUsername().equals(userPublication.getUsername())) {
                notificationService.saveNotification(userComment.getFullName() + " ha comentado en tu publicacion", userPublication);
            }
            if (comment.getTagged() != null) {
                User userTagged = userService.getUserByUsername(comment.getTagged().getUsername());
                if (!userComment.getUsername().equals(userTagged.getUsername())) {
                    notificationService.saveNotification(userComment.getFullName() + " te ha etiquetado en un comentario de una publicacion de " + userPublication.getFullName(), userTagged);
                }
            }
            return newComment;
        } catch (Exception e) {
            throw new SaveCommentException("The user or publication doesn't exist");
        }
    }
}