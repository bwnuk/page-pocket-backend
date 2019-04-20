package com.cracow.repositories;

import com.cracow.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private UserRepository userRepository;

    @Autowired
    public DbSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User("1L",
                "Jan",
                "Kowalski",
                "pass1",
                "jkowalski@gmail.com",
                new HashMap<String, List<String>>() {
                    {
                        put("home", Arrays.asList("id1","id2"));
                    }
                });

        User user2 = new User("2L",
                "Adam",
                "Mickiewicz",
                "pass2",
                "amickiewicz@gmail.com",
                new HashMap<String, List<String>>() {
                    {
                        put("home", Arrays.asList("id3","id4"));
                    }
                });


                //this.userRepository.save(user1);
                 //this.userRepository.save(user2);

    }
}
