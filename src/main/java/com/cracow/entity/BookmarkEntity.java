package com.cracow.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Blob;
import java.util.List;

@Data
@Document("bookmarks")
public class BookmarkEntity {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private String title;
    private String description;
    private String source;
    private List<String> tags;
    private String createdDate;
    private Blob blob;

    public BookmarkEntity() {
    }

    public BookmarkEntity(String id, String title, String description, String source, List<String> tags, String createdDate, Blob blob) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.tags = tags;
        this.createdDate = createdDate;
        this.blob = blob;
    }
}
