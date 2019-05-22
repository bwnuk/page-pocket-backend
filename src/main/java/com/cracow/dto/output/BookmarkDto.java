package com.cracow.dto.output;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@ToString
@Getter
public class BookmarkDto implements Comparable{

    private final String id;
    private final String title;
    private final String description;
    private final String source;
    private final Set<String> tags;
    private final String createdDate;

    public BookmarkDto(String id, String title, String description, String source, Set<String> tags) {
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

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        BookmarkDto other = (BookmarkDto) o;
        return id.compareTo(other.getId());
    }
}
