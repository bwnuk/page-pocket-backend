package com.cracow.services;

import com.cracow.entities.Bookmarks;
import com.cracow.entities.User;
import com.cracow.repositories.BookmarkRepository;
import com.cracow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
