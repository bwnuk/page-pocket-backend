package com.cracow.dto;

import java.io.Serializable;
import java.util.List;

public class SiteDto implements Serializable {

    private String Id;
    private String name;
    private List<String> tags;

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
