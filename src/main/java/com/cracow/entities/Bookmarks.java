package com.cracow.entities;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.sql.Blob;
import java.util.List;

@Document
public class Bookmarks {

    @Id
    @ApiModelProperty(notes = "database generated bookmark ID")
    private String id;

    private String title;
    private String description;
    private String source;
    private List<String> tags;
    private String createdDate;
    private Blob blob;

    public Bookmarks(String id, String title, String description, String source, List<String> tags, String createdDate, Blob blob) {
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
