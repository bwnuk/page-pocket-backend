package com.cracow.entity;

import com.cracow.dto.input.UserNewDto;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserEntityTest {

    private UserNewDto userNewDto = new UserNewDto("fname", "lname", "pass", "email");

    @Test
    public void deletedByIdTest() {
        Map<String, Set<String>> bookmarksListMap = new TreeMap<>();

        UserEntity userEntity = new UserEntity(userNewDto, "password");
        Set<String> set1 = Stream.of("v1", "v1", "v1", "v1")
                .collect(Collectors.toSet());
        Set<String> set2 = Stream.of("v1", "v2", "v3", "v1")
                .collect(Collectors.toSet());

        bookmarksListMap.put("k1", set1);
        bookmarksListMap.put("k2", set2);
        Map<String, Set<String>> map = new TreeMap<>();
        map.put("k1", set1);
        map.put("k2", set2);

        userEntity.setBookmarksListMap(bookmarksListMap);
        userEntity.deleteById("v1");
        assertTrue(userEntity.getBookmarksListMap().get("k1").isEmpty());
        assertFalse(userEntity.getBookmarksListMap().get("k2").contains("v1"));
    }
}