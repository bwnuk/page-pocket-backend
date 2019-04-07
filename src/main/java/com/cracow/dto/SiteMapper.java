package com.cracow.dto;

import com.cracow.entities.Site;

import java.util.ArrayList;
import java.util.List;

public class SiteMapper {

    public static SiteDto map(Site site){
        SiteDto dto = new SiteDto();
        dto.setId(site.getId());
        dto.setName(site.getName());
        dto.setTags(site.getTags());
        return dto;
    }

    public static List<SiteDto> map(List<Site> sites){
        List<SiteDto> dtos = new ArrayList<SiteDto>();
        for (Site site: sites) {
            dtos.add(map(site));
        }
        return dtos;
    }
}
