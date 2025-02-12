package c3.msmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.exceptions.user.GetByUsernameException;
import c3.msmb.exceptions.user.GetUsersException;
import c3.msmb.exceptions.user.PatchUserException;
import c3.msmb.exceptions.user.SaveUserException;
import c3.msmb.model.User;
import c3.msmb.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findById(username).orElseThrow(() -> new GetByUsernameException("User not found"));
    }

    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new SaveUserException("Information incomplete");
        }
    }

    @Transactional
    public void updateInfoUser(String username, User user) {
        int updatedRows = userRepository.updatePartialUser(user.getFullName(), user.getPhone(), user.getBiography(), user.getProfilePhoto(), username);
        if (updatedRows == 0) {
            throw new PatchUserException("User not found: " + username);
        }
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new GetUsersException("Users not Found");
        }
        return users;
    }
}