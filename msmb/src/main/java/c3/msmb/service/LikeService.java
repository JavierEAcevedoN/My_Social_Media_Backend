package c3.msmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.model.Like;
import c3.msmb.model.User;
import c3.msmb.repository.LikeRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return likeRepository.findAll();
    }

    public List<Like> getLikesByPublicationId(Long publicationId) {
        return likeRepository.findByIdPublicationId(publicationId);
    }

    public Boolean isLiked(String username, Long idPublication) {
        return likeRepository.existsByUsernameUsernameAndIdPublicationId(username, idPublication);
    }

    @Transactional
    public void likePublication(String username, Long idPublication) {
        try {
            int inserted = likeRepository.like(username, idPublication);
            if (inserted == 0) {
                throw new EntityNotFoundException("Can't to like " + idPublication);
            }
            User userLike = userService.getUserByUsername(username);
            User userLiked = publicationService.getPublicationById(idPublication).getUsername();
            if (!userLike.getUsername().equals(userLiked.getUsername())) {
                notificationService.saveNotification(userLike.getFullName() + " ha reaccionado a tu publicacion", userLiked);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error to like " + idPublication + ": " + e.getMessage());
        }
    }

    @Transactional
    public void unLikePublication(String username, Long idPublication) {
        int deleted = likeRepository.unlike(username, idPublication);
        if (deleted == 0) {
            throw new EntityNotFoundException("Doesn't exist a match with " + username + " and " + idPublication);
        }
    }
}