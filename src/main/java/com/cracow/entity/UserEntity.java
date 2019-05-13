package com.cracow.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document("users")
public class UserEntity {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Map<String, List<String>> bookmarksListMap;


    public UserEntity() {
    }

    public UserEntity(String id, String firstName, String lastName, String email, String password, Map<String, List<String>> bookmarksListMap) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bookmarksListMap = bookmarksListMap;
    }
}
