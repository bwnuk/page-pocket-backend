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

//    @CreatedDate
//    private Date createdDate;
    private String createdDate;
    private byte[] blob;

    public BookmarkEntity() {
    }

    public BookmarkEntity(BookmarkNewDto bookmarkNewDto) {
        this.title = bookmarkNewDto.getTitle();
        this.description = bookmarkNewDto.getDescription();
        this.source = bookmarkNewDto.getSource();
        this.tags = bookmarkNewDto.getTags();
        //this.createdDate = new Date();
        //Date date = new Date();
        createdDate = DateFormat.getDateInstance().format(new Date());
        //createdDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public BookmarkEntity(BookmarkNewDto bookmarkNewDto, byte[] blob) {
        this(bookmarkNewDto);
        this.blob = blob;
        createdDate = DateFormat.getDateInstance().format(new Date());
        //this.createdDate = new Date();
    }

//    @PrePersist
//    protected void onCreate() {
//        this.createdDate = new Date();
//    }

    public BookmarkDto toDto() {
        return new BookmarkDto(id, title, description, source, tags, createdDate);
    }

    public BookmarkBlobDto toBlobDto() {
        return new BookmarkBlobDto(blob);
    }
}
