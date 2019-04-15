package com.cracow.controllers;

import com.cracow.entities.Bookmark;
import com.cracow.services.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmark")
public class BookmarkController {
    private BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/add")
    public void addBookmar(@RequestBody Bookmark bookmark){
        List<Bookmark> list = bookmarkService.getAll();
        if(list.size() == 0){
            //TODO zwracac statusy
        }else {
            for (Bookmark b: list){
                if(b.getLink().contains(bookmark.getLink()) || bookmark.getLink().contains(b.getLink())){
                    //TODO powtarza sie
                }
            }
        }
    }
}
