package c3.msmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.exceptions.like.ByPublicationId;
import c3.msmb.exceptions.like.GetLikesException;
import c3.msmb.exceptions.like.LikePublicationException;
import c3.msmb.exceptions.like.UnLikePublicationException;
import c3.msmb.model.Like;
import c3.msmb.model.User;
import c3.msmb.repository.LikeRepository;
import jakarta.transaction.Transactional;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserService userService;
    @Autowired 
    private PublicationService publicationService;
    @Autowired
    private NotificationService notificationService;


    public List<Like> getLikes() {
        List<Like> likes = likeRepository.findAll();
        if (likes.isEmpty()) {
            throw new GetLikesException("Likes not found");
        }
        return likes;
    }

    public List<Like> getLikesByPublicationId(Long publicationId) {
        List<Like> likes = likeRepository.findByIdPublicationId(publicationId);
        if (likes.isEmpty()) {
            throw new ByPublicationId("Likes not found for publication " + publicationId);
        }
        return likes;
    }

    public Boolean isLiked(String username, Long idPublication) {
        return likeRepository.existsByUsernameUsernameAndIdPublicationId(username, idPublication);
    }

    @Transactional
    public void likePublication(String username, Long idPublication) {
        try {
            int inserted = likeRepository.like(username, idPublication);
            if (inserted == 0) {
                throw new LikePublicationException("Can't to like " + idPublication);
            }
            User userLike = userService.getUserByUsername(username);
            User userLiked = publicationService.getPublicationById(idPublication).getUsername();
            if (!userLike.getUsername().equals(userLiked.getUsername())) {
                notificationService.saveNotification(userLike.getFullName() + " ha reaccionado a tu publicacion", userLiked);
            }
        } catch (Exception e) {
            throw new LikePublicationException("Error to like publication " + idPublication + ": " + e.getMessage());
        }
    }

    @Transactional
    public void unLikePublication(String username, Long idPublication) {
        int deleted = likeRepository.unlike(username, idPublication);
        if (deleted == 0) {
            throw new UnLikePublicationException("Doesn't exist a match with " + username + " and publication " + idPublication);
        }
    }
}