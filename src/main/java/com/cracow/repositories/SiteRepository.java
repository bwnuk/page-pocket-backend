package com.cracow.repositories;

import com.cracow.entities.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends MongoRepository<Site, String> {

}
