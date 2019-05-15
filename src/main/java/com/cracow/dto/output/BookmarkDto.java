package com.cracow.dto.output;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class BookmarkDto {

    private final String id;
    private final String title;
    private final String description;
    private final String source;
    private final List<String> tags;

    public BookmarkDto(String id, String title, String description, String source, List<String> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.tags = tags;
    }
}
