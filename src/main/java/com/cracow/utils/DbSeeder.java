package com.cracow.utils;

import com.cracow.entity.BookmarkEntity;
import com.cracow.entity.UserEntity;
import com.cracow.repository.BookmarkRepository;
import com.cracow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        UserEntity userEntity1 = new UserEntity("1L",
                "Jan",
                "Kowalski",
                "pass1",
                "jkowalski@gmail.com",
                new HashMap<String, List<String>>() {
                    {
                        put("home", Arrays.asList("id1", "id2"));
                    }
                });

        UserEntity userEntity2 = new UserEntity("2L",
                "Adam",
                "Mickiewicz",
                "pass2",
                "amickiewicz@gmail.com",
                new HashMap<String, List<String>>() {
                    {
                        put("home", Arrays.asList("id3", "id4"));
                    }
                });

        BookmarkEntity book1 = new BookmarkEntity("1L",
                "tytul1",
                "opis",
                "zrodlo",
                Arrays.asList("tag1", "tag2"),
                "data",
                null
        );

        BookmarkEntity book2 = new BookmarkEntity("2L",
                "tytul1",
                "opis",
                "zrodlo",
                Arrays.asList("tag1", "tag2"),
                "data",
                null
        );


        //this.userRepository.save(userEntity1);
        //this.userRepository.save(userEntity2);
        this.bookmarkRepository.save(book1);
        this.bookmarkRepository.save(book2);

    }
}
