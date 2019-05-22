package com.cracow.entity;

import com.cracow.dto.input.BookmarkNewDto;
import com.cracow.dto.output.BookmarkBlobDto;
import com.cracow.dto.output.BookmarkDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.PrePersist;
//import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Document("bookmarks")
public class BookmarkEntity {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private String title;
    private String description;
    private String source;
    private Set<String> tags;
    private String createdDate;
    private byte[] blob;

    public BookmarkEntity() {
    }

    public BookmarkEntity(BookmarkNewDto bookmarkNewDto) {
        this.title = bookmarkNewDto.getTitle();
        this.description = bookmarkNewDto.getDescription();
        this.source = bookmarkNewDto.getSource();
        this.tags = bookmarkNewDto.getTags();
        createdDate = DateFormat.getDateInstance().format(new Date());
    }

    public BookmarkEntity(BookmarkNewDto bookmarkNewDto, byte[] blob) {
        this(bookmarkNewDto);
        this.blob = blob;
        createdDate = DateFormat.getDateInstance().format(new Date());
    }


    public BookmarkDto toDto() {
        return new BookmarkDto(id, title, description, source, tags, createdDate);
    }

    public BookmarkBlobDto toBlobDto() {
        return new BookmarkBlobDto(blob);
    }
}
