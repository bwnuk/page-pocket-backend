package com.cracow.repositories;

import com.cracow.entities.Bookmark;
import com.cracow.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends MongoRepository<Bookmark, String> {
}
