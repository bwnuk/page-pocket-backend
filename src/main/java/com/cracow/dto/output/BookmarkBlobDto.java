package com.cracow.dto.output;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BookmarkBlobDto {

    private final byte[] blob;

    public BookmarkBlobDto(byte[] blob) {
        this.blob = blob;
    }
}
