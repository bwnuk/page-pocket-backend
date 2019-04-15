package com.cracow.services;

import com.cracow.entities.Bookmark;
import com.cracow.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    private BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public List<Bookmark> getAll(){
        return bookmarkRepository.findAll();
    }

}
