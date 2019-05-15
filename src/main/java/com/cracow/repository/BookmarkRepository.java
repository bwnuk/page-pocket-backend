package com.cracow.repository;

import com.cracow.entity.BookmarkEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends MongoRepository<BookmarkEntity, String> {
    Optional<BookmarkEntity> findByTitle(String title);

    List<BookmarkEntity> findByTagsInAndIdIn(List<String> tags, List<String> id);
}
