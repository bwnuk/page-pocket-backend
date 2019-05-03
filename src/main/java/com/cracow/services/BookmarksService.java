package com.cracow.services;

import com.cracow.entities.Bookmarks;
import com.cracow.entities.User;
import com.cracow.repositories.BookmarkRepository;
import com.cracow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookmarksService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public Iterable<Bookmarks>getAllBookmarks(){
        return bookmarkRepository.findAll();
    }

    public boolean saveBook(Bookmarks book)
    {
        bookmarkRepository.save(book);
        return true;
    }

    public Optional<Bookmarks> findById(String id)
    {
        return bookmarkRepository.findById(id);
    }

    public void deleteById(String id)
    {
        bookmarkRepository.deleteById(id);
    }
}
