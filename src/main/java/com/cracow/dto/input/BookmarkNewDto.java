package com.cracow.dto.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ToString
@Getter
public class BookmarkNewDto {

    @NotEmpty
    private final String title;

    @NotEmpty
    private final String description;

    @NotEmpty
    private final String source;

    @NotEmpty
    private final List<String> tags;

    @JsonCreator
    public BookmarkNewDto(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("source") String source,
            @JsonProperty("tags") List<String> tags) {
        this.title = title;
        this.description = description;
        this.source = source;
        this.tags = tags;
    }
}
