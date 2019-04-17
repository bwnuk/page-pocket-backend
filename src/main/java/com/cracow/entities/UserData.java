package com.cracow.entities;
import com.cracow.dto.UserNewDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
public class UserData implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private Map<String,List<String>> bookmarksListMap;


    public UserData() {
    }

    public UserData(String id, String firstName, String lastName, String mail, String password, Map<String, List<String>> bookmarksListMap) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.bookmarksListMap = bookmarksListMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, List<String>> getBookmarksListMap() {
        return bookmarksListMap;
    }

    public void setBookmarksListMap(Map<String, List<String>> bookmarksListMap) {
        this.bookmarksListMap = bookmarksListMap;
    }

}