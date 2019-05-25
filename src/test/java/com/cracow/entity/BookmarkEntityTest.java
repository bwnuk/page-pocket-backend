package com.cracow.entity;

import com.cracow.dto.input.BookmarkNewDto;
import com.cracow.dto.output.BookmarkDto;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookmarkEntityTest {
    private BookmarkNewDto bookmarkNewDto = new BookmarkNewDto("title", "desc", "src", Stream.of("t1", "t2")
            .collect(Collectors.toSet()));

    @Test
    public void toBlobDtoTest() {
        byte[] toCheck = {1, 2, 3};
        byte[] expected = {1, 2, 3};
        BookmarkEntity bookmarkEntity = new BookmarkEntity(bookmarkNewDto, toCheck);
        assertArrayEquals(expected, bookmarkEntity.toBlobDto().getBlob());
    }

    @Test
    public void toDtoTest() {
        BookmarkEntity bookmarkEntity = new BookmarkEntity(bookmarkNewDto);
        BookmarkDto dto = bookmarkEntity.toDto();
        assertEquals("title", dto.getTitle());
    }
}