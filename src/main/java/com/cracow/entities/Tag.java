package com.cracow.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Tag {

    @Id
    private String id;
    private List<Bookmarks> bookmarks;

    public Tag() {
    }

    public Tag(String id, List<Bookmarks> bookmarks) {
        this.id = id;
        this.bookmarks = bookmarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Bookmarks> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmarks> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
