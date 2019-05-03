package com.cracow.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.sql.Blob;
import java.util.List;

@Document
public class Bookmarks {

    @Id
    private String id;

    private String tittle;
    private String description;
    private String source;
    private List<String> tags;
    private String createdDate;
    private Blob blob;

    public Bookmarks(String id, String tittle, String description, String source, List<String> tags, String createdDate, Blob blob) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.source = source;
        this.tags = tags;
        this.createdDate = createdDate;
        this.blob = blob;
    }


    public Bookmarks() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }
}