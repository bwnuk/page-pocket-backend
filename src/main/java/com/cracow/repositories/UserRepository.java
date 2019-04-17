package com.cracow.repositories;

import com.cracow.entities.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserData, String> {

}
