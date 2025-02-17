package c3.msmb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import c3.msmb.model.Follow;
import c3.msmb.service.FollowService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/follows")
public class FollowController {
    @Autowired
    private FollowService followService;

    @GetMapping
    public List<Follow> getFollows() {
        return followService.getFollows();
    }

    @GetMapping("/followers/{username}")
    public List<Follow> getFollowers(@PathVariable(name = "username") String username) {
        return followService.getFollowers(username);
    }

    @GetMapping("/following/{username}")
    public List<Follow> getFollowing(@PathVariable(name = "username") String username) {
        return followService.getFollowing(username);
    }

    @GetMapping("/{followfrom}/{followto}")
    public Boolean isFollow(@PathVariable(name = "followfrom") String followFrom,
            @PathVariable(name = "followto") String followTo) {
                return followService.isFollow(followFrom, followTo);
    }

    @PostMapping("/{followfrom}/{followto}")
    public void followUser(@PathVariable(name = "followfrom") String followFrom,
            @PathVariable(name = "followto") String followTo) {
                followService.followUser(followFrom, followTo);
    }

    @DeleteMapping("/{followfrom}/{followto}")
    public void unFollowUser(@PathVariable(name = "followfrom") String followFrom,
            @PathVariable(name = "followto") String followTo) {
                followService.unFollowUser(followFrom, followTo);
    }
}
