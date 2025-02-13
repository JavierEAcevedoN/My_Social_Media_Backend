package c3.msmb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import c3.msmb.model.Comment;
import c3.msmb.service.CommentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    
    @GetMapping
    public List<Comment> getComments() {
        return commentService.getComments();
    }
    
    @GetMapping("/{id}")
    public List<Comment> getCommentsByUsername(@PathVariable(name = "id") Long idPublication) {
        return commentService.getCommentsByPublicationId(idPublication);
    }
    
    @PostMapping("/save")
    public Comment saveComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }
}