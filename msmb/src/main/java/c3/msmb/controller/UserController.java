package c3.msmb.controller;

import org.springframework.web.bind.annotation.RestController;

import c3.msmb.model.User;
import c3.msmb.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    @GetMapping("/{username}")
    public User getUserUsername(@PathVariable(name = "username") String  username) {
        return userService.getUserByUsername(username);
    }
    
    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }
    
    @PatchMapping("/patch/{username}")
    public void pachUser(@PathVariable(name = "username") String username, @RequestBody User user) {
        userService.updateInfoUser(username, user);
    }
}