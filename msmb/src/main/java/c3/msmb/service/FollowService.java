package c3.msmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.exceptions.follow.FollowUserException;
import c3.msmb.exceptions.follow.GetFollowersException;
import c3.msmb.exceptions.follow.GetFollowingException;
import c3.msmb.exceptions.follow.GetFollowsException;
import c3.msmb.exceptions.follow.UnFollowUserException;
import c3.msmb.model.Follow;
import c3.msmb.model.User;
import c3.msmb.repository.FollowRepository;
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
            throw new FollowUserException("You can't follow yourself");
        }
        try {
            int inserted = followRepository.follow(followFrom, followTo);
            if (inserted == 0) {
                throw new FollowUserException("Can't to follow " + followTo);
            }
            User userFollowFrom = userService.getUserByUsername(followFrom);
            User userFollowTo = userService.getUserByUsername(followTo);
            notificationService.saveNotification(userFollowFrom.getFullName() + " ha comenzado a seguirte.", userFollowTo);
        } catch (Exception e) {
            throw new FollowUserException("Error to follow " + followTo + ": " + e.getMessage());
        }
    }

    @Transactional
    public void unFollowUser(String followFrom, String followTo) {
        int deleted = followRepository.unFollow(followFrom, followTo);
        if (deleted == 0) {
            throw new UnFollowUserException("Doesn't exist a match with " + followFrom + " and " + followTo);
        }
    }

    public List<Follow> getFollowers(String followToUser) {
        List<Follow> follows = followRepository.findByFollowToUsername(followToUser);
        if (follows.isEmpty()) {
            throw new GetFollowersException("Username " + followToUser + " not found or not have followers");
        }
        return follows;
    }

    public List<Follow> getFollowing(String followFromUser) {
        List<Follow> follows = followRepository.findByFollowFromUsername(followFromUser);
        if (follows.isEmpty()) {
            throw new GetFollowingException("Username " + followFromUser + " not found or not are following");
        }
        return follows;
    }

    public List<Follow> getFollows() {
        List<Follow> follows = followRepository.findAll();
        if (follows.isEmpty()) {
            throw new GetFollowsException("Follows not found");
        }
        return follows;
    }
}