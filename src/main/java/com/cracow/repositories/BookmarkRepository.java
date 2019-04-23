package com.cracow.repositories;

import com.cracow.entities.Bookmarks;
import com.cracow.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends MongoRepository<Bookmarks, String> {
}
