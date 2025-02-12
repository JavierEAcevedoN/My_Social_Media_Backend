package c3.msmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.model.User;
import c3.msmb.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void updateInfoUser(String username, User user) {
        int updatedRows = userRepository.updatePartialUser(user.getFullName(), user.getPhone(), user.getBiography(), user.getProfilePhoto(), username);
        if (updatedRows == 0) {
            throw new EntityNotFoundException("User not found: " + username);
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}