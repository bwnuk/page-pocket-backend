package com.cracow.service;

import com.cracow.dto.input.BookmarkNewDto;
import com.cracow.dto.output.BookmarkBlobDto;
import com.cracow.dto.output.BookmarkDto;
import com.cracow.entity.BookmarkEntity;
import com.cracow.entity.UserEntity;
import com.cracow.error.common.ConflictProblem;
import com.cracow.error.common.NotFoundProblem;
import com.cracow.repository.BookmarkRepository;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public BookmarkService(BookmarkRepository bookmarkRepository, UserService userService, ModelMapper modelMapper) {
        this.bookmarkRepository = bookmarkRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<BookmarkDto> findAllByEmail(String email) {
        UserEntity user = userService.findByEmailOrThrow404(email);

        Map<String, List<String>> bookmarks = user.getBookmarksListMap();
        Collection<List<String>> lists = bookmarks.values();

        List<String> bookmarkIDs = Lists.newArrayList(Iterables.concat(lists));
        List<BookmarkEntity> bookmarkEntities = Lists.newArrayList(bookmarkRepository.findAllById(bookmarkIDs));

        return bookmarkEntities.stream().map(bookmarkEntity -> entityToDto(bookmarkEntity)).collect(Collectors.toList());
    }

    public BookmarkDto saveBookmarkByEmail(String email, BookmarkNewDto bookmarkNewDto) {
        UserEntity user = userService.findByEmailOrThrow404(email);

        String title = bookmarkNewDto.getTitle();
        List<String> tags = bookmarkNewDto.getTags();
        Map<String, List<String>> userBookmark = user.getBookmarksListMap();

        if (bookmarkRepository.findByTitle(title).isPresent()) {
            throw new ConflictProblem("bookmarkEntity", "title", title);
        }

        BookmarkEntity bookmarkEntity = newDtoToEntity(bookmarkNewDto);
        bookmarkRepository.save(bookmarkEntity);

        tags.forEach(tag -> {
            List<String> list = userBookmark.getOrDefault(tag, new ArrayList<>());
            list.add(bookmarkEntity.getId());
        });

        return entityToDto(bookmarkEntity);
    }

    //TODO dokonczyc
    public BookmarkBlobDto findByIdForEmail(String id, String email) {
        BookmarkEntity bookmarkEntity = bookmarkRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundProblem("bookmarkEntity", "id", id));

        return bookmarkToDto(bookmarkEntity);
    }

    //TODO dokonczyc
    public void deleteByIdForEmail(String id, String email) {
        bookmarkRepository.deleteById(id);
    }


    private BookmarkNewDto bookmarksConvertToNewDTO(BookmarkEntity bookmarkEntity) {
        BookmarkNewDto newBookmarks = modelMapper.map(bookmarkEntity, BookmarkNewDto.class);
        return newBookmarks;
    }

    private BookmarkEntity newDtoToEntity(BookmarkNewDto bookmarkNewDto) {
        BookmarkEntity bookmarkEntity = modelMapper.map(bookmarkNewDto, BookmarkEntity.class);
        return bookmarkEntity;
    }

    private BookmarkDto entityToDto(BookmarkEntity bookmarkEntity) {
        BookmarkDto newBookmarks = modelMapper.map(bookmarkEntity, BookmarkDto.class);
        return newBookmarks;
    }

    private BookmarkBlobDto bookmarkToDto(BookmarkEntity bookmarkEntity) {
        BookmarkBlobDto newBookmarks = modelMapper.map(bookmarkEntity, BookmarkBlobDto.class);
        return newBookmarks;
    }

}
