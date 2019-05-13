package com.cracow.dto.output;

import lombok.Getter;
import lombok.ToString;

import java.sql.Blob;

@ToString
@Getter
public class BookmarkBlobDto {

    private final Blob blob;

    public BookmarkBlobDto(Blob blob) {
        this.blob = blob;
    }
}
