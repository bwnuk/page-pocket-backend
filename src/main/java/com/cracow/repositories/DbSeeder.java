package com.cracow.repositories;

import com.cracow.entities.Bookmarks;
import com.cracow.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private UserRepository userRepository;

    private BookmarkRepository bookmarkRepository;

    @Autowired
    public DbSeeder(UserRepository userRepository, BookmarkRepository bookmarkRepository) {
        this.userRepository = userRepository;
        this.bookmarkRepository = bookmarkRepository;
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

        Bookmarks book1 = new Bookmarks("1L",
                "tytul1",
                "opis",
                "zrodlo",
                Arrays.asList("tag1","tag2"),
                "data",
                null
        );

        Bookmarks book2 = new Bookmarks("2L",
                "tytul1",
                "opis",
                "zrodlo",
                Arrays.asList("tag1","tag2"),
                "data",
                null
        );


                //this.userRepository.save(user1);
                 //this.userRepository.save(user2);
                this.bookmarkRepository.save(book1);
                this.bookmarkRepository.save(book2);

    }
}
