package com.cracow.entities;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Document
public class User implements Serializable {

    @Id
    @ApiModelProperty(notes = "database generated user ID")
    private String id;

    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    @ApiModelProperty(notes = "user bookmarks list")
    private Map<String,List<String>> bookmarksListMap;


    public User() {
    }

    public User(String id, String firstName, String lastName, String mail, String password, Map<String, List<String>> bookmarksListMap) {
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
