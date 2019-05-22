package com.cracow.entity;

import com.cracow.dto.input.UserNewDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private Map<String, Set<String>> bookmarksListMap;


    public UserEntity() {
    }

    public UserEntity(UserNewDto userNewDto, String password) {
        this.firstName = userNewDto.getFirstName();
        this.lastName = userNewDto.getLastName();
        this.email = userNewDto.getEmail();
        this.password = password;
        bookmarksListMap = Collections.emptyMap();
    }
}
