package c3.msmb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import c3.msmb.model.Like;
import c3.msmb.service.LikeService;

@RestController
@RequestMapping("/api/likes")
public class MyController {
    @Autowired
    private LikeService likeService;

    @GetMapping
    public List<Like> getLikes() {
        return likeService.getLikes();
    }

    @GetMapping("/{id}")
    public List<Like> getLikesByPublicationId(@PathVariable(name = "id") Long publicationId) {
        return likeService.getLikesByPublicationId(publicationId);
    }

    @GetMapping("/{username}/{id}")
    public Boolean isLiked(@PathVariable(name = "username") String username,
            @PathVariable(name = "id") Long idPublication) {
        return likeService.isLiked(username, idPublication);
    }

    @PostMapping("/{username}/{id}")
    public void likePublication(@PathVariable(name = "username") String username,
            @PathVariable(name = "id") Long idPublication) {
        likeService.likePublication(username, idPublication);
    }

    @DeleteMapping("/{username}/{id}")
    public void unLikePublication(@PathVariable(name = "username") String username,
            @PathVariable(name = "id") Long idPublication) {
        likeService.unLikePublication(username, idPublication);
    }
}