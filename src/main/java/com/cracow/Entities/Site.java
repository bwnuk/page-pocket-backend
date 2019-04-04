package com.cracow.Entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Site {

    @Id
    private String Id;
    private String name;
    private List<String> tags;

    public Site() {
    }

    public Site(String id, String name, List<String> tags) {
        Id = id;
        this.name = name;
        this.tags = tags;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


}
