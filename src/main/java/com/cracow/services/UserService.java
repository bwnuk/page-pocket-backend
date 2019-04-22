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

    public boolean saveUser(User userData)
    {
        for (User user : getAllUsers())
        {
            if (userData.getMail().equals(user.getMail())) return false;
        }
        userRepository.save(userData);
        return true;
    }

    public boolean login(String email, String password)
    {
        for (User user : getAllUsers())
        {
            if (email.equals(user.getMail()))
                if (password.equals(user.getPassword()))
                    return true;
        }
        return false;
    }
    public User findByEmail(String email)
    {
        for (User user : getAllUsers())
        {
            if (email.equals(user.getMail())) return user;
        }
        return null;

    }

}