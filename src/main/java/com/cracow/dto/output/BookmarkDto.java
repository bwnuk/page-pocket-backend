package com.cracow.dto.output;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Getter
public class BookmarkDto {

    private final String id;
    private final String title;
    private final String description;
    private final String source;
    private final List<String> tags;
    //private final Date createdDate;
    private final String createdDate;

    public BookmarkDto(String id, String title, String description, String source, List<String> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.tags = tags;
        this.createdDate = null;// Czy tak wolno, czy Manio sie wścieknie?
    }

    public BookmarkDto(String id, String title, String description, String source, List<String> tags, String createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.tags = tags;
        this.createdDate = createdDate;
    }
}
