package com.cracow.repositories;

import com.cracow.entities.Bookmarks;
import com.cracow.entities.Tag;
import com.cracow.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DbSeeder implements CommandLineRunner {

    private UserRepository userRepository;

    @Autowired
    public DbSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(
                "ID1",
                "Jan",
                "Kowalski",
                "jkowalski@gmail.com",
                "pass1",
                        Arrays.asList(
                        new Tag(
                        "TAGID1",
                        Arrays.asList(
                                new Bookmarks("BKID1",Arrays.asList("www.onet.pl","www.gmail.com")),
                                new Bookmarks("BKID2",Arrays.asList("www.facebook.pl","www.nk.pl"))
                        ))));

        User user2 = new User(
                "ID2",
                "Adam",
                "Mickiewicz",
                "mail@gmail.com",
                "pass2",
                Arrays.asList(
                        new Tag(
                                "TAGID2",
                                Arrays.asList(
                                        new Bookmarks("BKID3",Arrays.asList("www.youtube.pl","www.wfmii.pl","www.pudelek.pl")),
                                        new Bookmarks("BKID4", Arrays.asList("www.spotify.com"))
                                )
                        )
                )

        );




        this.userRepository.save(user1);
        this.userRepository.save(user2);

    }
}
