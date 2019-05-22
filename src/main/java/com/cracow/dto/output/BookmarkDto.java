package com.cracow.dto.output;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@ToString
@Getter
public class BookmarkDto {

    private final String id;
    private final String title;
    private final String description;
    private final String source;
    private final Set<String> tags;

    public BookmarkDto(String id, String title, String description, String source, Set<String> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.tags = tags;
    }
}
