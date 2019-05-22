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

import java.sql.Date;
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

    @CreatedDate
    private Date createdDate;
    private byte[] blob;

    public BookmarkEntity() {
    }

    public String getTitle() {
        return title;
    }

    public BookmarkEntity(BookmarkNewDto bookmarkNewDto) {
        this.title = bookmarkNewDto.getTitle();
        this.description = bookmarkNewDto.getDescription();
        this.source = bookmarkNewDto.getSource();
        this.tags = bookmarkNewDto.getTags();
    }

    public BookmarkEntity(BookmarkNewDto bookmarkNewDto, byte[] blob) {
        this(bookmarkNewDto);
        this.blob = blob;
    }

    public BookmarkDto toDto() {
        return new BookmarkDto(id, title, description, source, tags);
    }

    public BookmarkBlobDto toBlobDto() {
        return new BookmarkBlobDto(blob);
    }
}
