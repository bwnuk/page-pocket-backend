package com.cracow.services;
import com.cracow.entities.Site;
import com.cracow.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SiteService {

    private SiteRepository siteRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository)
    {
        this.siteRepository=siteRepository;
    }

    public Iterable<Site>getAllSites(){
        return siteRepository.findAll();
    }

    public Optional<Site> getSiteById(String id)
    {
        return siteRepository.findById(id);
    }

    public void deleteById(String id)
    {
        siteRepository.deleteById(id);
    }

    public Site saveSite(Site site)
    {
        return siteRepository.save(site);
    }
}