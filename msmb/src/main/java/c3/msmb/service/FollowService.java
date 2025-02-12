package c3.msmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.model.Follow;
import c3.msmb.model.User;
import c3.msmb.repository.FollowRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @Transactional
    public void followUser(String followFrom, String followTo) {
        if (followFrom.endsWith(followTo)) {
            throw new RuntimeException("You can't follow your self");
        }
        try {
            int inserted = followRepository.follow(followFrom, followTo);
            if (inserted == 0) {
                throw new EntityNotFoundException("Can't to follow " + followTo);
            }
            User userFollowFrom = userService.getUserByUsername(followFrom);
            User userFollowTo = userService.getUserByUsername(followTo);
            notificationService.saveNotification(userFollowFrom.getFullName() + " ha comenzado a seguirte.", userFollowTo);
        } catch (Exception e) {
            throw new RuntimeException("Error to follow " + followTo + ": " + e.getMessage());
        }
    }

    @Transactional
    public void unFollowUser(String followFrom, String followTo) {
        int deleted = followRepository.unFollow(followFrom, followTo);
        if (deleted == 0) {
            throw new EntityNotFoundException("Doesn't exist a match with " + followFrom + " and " + followTo);
        }
    }

    public List<Follow> getFollowers(String followToUser) {
        return followRepository.findByFollowToUsername(followToUser);
    }

    public List<Follow> getFollowing(String followFromUser) {
        return followRepository.findByFollowFromUsername(followFromUser);
    }

    public List<Follow> getFollows() {
        return followRepository.findAll();
    }
}