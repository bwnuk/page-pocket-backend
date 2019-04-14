package com.cracow.services;
import com.cracow.entities.User;
import com.cracow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public Iterable<User>getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id)
    {
        return userRepository.findById(id);
    }

    public void deleteById(String id)
    {
        userRepository.deleteById(id);
    }

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }
}