package com.cracow.service;

import com.cracow.dto.input.BookmarkNewDto;
import com.cracow.dto.output.BookmarkBlobDto;
import com.cracow.dto.output.BookmarkDto;
import com.cracow.entity.BookmarkEntity;
import com.cracow.entity.UserEntity;
import com.cracow.error.common.ConflictProblem;
import com.cracow.error.common.NotFoundProblem;
import com.cracow.error.common.UnauthorizedProblem;
import com.cracow.repository.BookmarkRepository;
import com.cracow.service.parser.ParserService;
import com.cracow.service.security.SecurityService;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final SecurityService securityService;
    private final UserService userService;
    private final ParserService parserService;

    private static final int PAGE_SIZE = 10;

    public BookmarkService(BookmarkRepository bookmarkRepository, SecurityService securityService, UserService userService, ParserService parserService) {
        this.bookmarkRepository = bookmarkRepository;
        this.securityService = securityService;
        this.userService = userService;
        this.parserService = parserService;
    }

    public List<BookmarkDto> findAll(Optional<String> tag, Optional<Integer> page) {
        String email = securityService.findLoggedInEmail();
        UserEntity user = userService.findByEmailOrThrow404(email);
        Map<String, List<String>> bookmarks = user.getBookmarksListMap();
        Collection<List<String>> lists = bookmarks.values();
        List<String> bookmarkIDs = Lists.newArrayList(Iterables.concat(lists));

        List<BookmarkEntity> bookmarkEntities;
        if (tag.isPresent()) {
            String tagName = tag.get();
            bookmarkEntities = bookmarkRepository.findByTagsInAndIdIn(Lists.newArrayList(tagName), bookmarkIDs);
        } else {
            if (page.isPresent()) {
                Pageable pageable = PageRequest.of(page.get(), PAGE_SIZE);
                bookmarkEntities = Lists.newArrayList(bookmarkRepository.findAllById(bookmarkIDs, pageable));
            } else {
                bookmarkEntities = Lists.newArrayList(bookmarkRepository.findAllById(bookmarkIDs));
            }

        }

        return bookmarkEntities.stream().map(bookmarkEntity -> bookmarkEntity.toDto()).collect(Collectors.toList());
    }

    public BookmarkDto saveBookmark(BookmarkNewDto bookmarkNewDto, boolean parse) {
        String email = securityService.findLoggedInEmail();
        UserEntity user = userService.findByEmailOrThrow404(email);

        String title = bookmarkNewDto.getTitle();
        List<String> tags = bookmarkNewDto.getTags();
        Map<String, List<String>> userBookmarkMap = user.getBookmarksListMap();

        if (bookmarkRepository.findByTitle(title).isPresent()) {
            throw new ConflictProblem("bookmark", "title", title);
        }

        BookmarkEntity bookmarkEntity;
        if (parse) {
            byte[] blob = parserService.parseToByte(bookmarkNewDto.getSource());
            bookmarkEntity = new BookmarkEntity(bookmarkNewDto, blob);
        } else {
            bookmarkEntity = new BookmarkEntity(bookmarkNewDto);
        }
        bookmarkRepository.save(bookmarkEntity);


        tags.forEach(tag -> {
            List<String> list = userBookmarkMap.getOrDefault(tag, new ArrayList<>());
            list.add(bookmarkEntity.getId());
            userBookmarkMap.put(tag, list);
        });
        userService.saveUser(user);

        return bookmarkEntity.toDto();
    }

    public BookmarkBlobDto findById(String id) {
        String email = securityService.findLoggedInEmail();
        UserEntity user = userService.findByEmailOrThrow404(email);

        throw401IfAccessDenied(user, id);

        BookmarkEntity bookmarkEntity = findByIdOrThrow401(id);

        return bookmarkEntity.toBlobDto();
    }

    public BookmarkBlobDto parseLink(String id) {
        String email = securityService.findLoggedInEmail();
        UserEntity user = userService.findByEmailOrThrow404(email);

        throw401IfAccessDenied(user, id);

        BookmarkEntity bookmarkEntity = findByIdOrThrow401(id);
        byte[] bytes = parserService.parseToByte(bookmarkEntity.getSource());
        bookmarkEntity.setBlob(bytes);

        bookmarkRepository.save(bookmarkEntity);
        return bookmarkEntity.toBlobDto();
    }

    public void deleteById(String id) {
        String email = securityService.findLoggedInEmail();
        UserEntity user = userService.findByEmailOrThrow404(email);

        throw401IfAccessDenied(user, id);

        bookmarkRepository.deleteById(id);
    }

    private void throw401IfAccessDenied(UserEntity user, String bookmarkId) {
        Map<String, List<String>> userBookmark = user.getBookmarksListMap();

        boolean isAllowed = userBookmark.values().stream().anyMatch(e -> e.contains(bookmarkId));
        if (!isAllowed) {
            throw new UnauthorizedProblem("Access is denied");
        }
    }

    private BookmarkEntity findByIdOrThrow401(String id) {
        return bookmarkRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundProblem("bookmark", "id", id));
    }


}
