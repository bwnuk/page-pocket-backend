package com.cracow.Controllers;
import com.cracow.Entities.Site;
import com.cracow.Services.SiteService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class SiteController {

    private SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;

    }

    @GetMapping("/getall")
    public Iterable<Site> getAll()
    {
        return siteService.getAllSites();
    }

    @GetMapping
    public Optional<Site> getSideById(@RequestParam String id)
    {
        return siteService.getSiteById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestParam String id)
    {
        siteService.deleteById(id);
    }

    @PostMapping("/add")
    public Site saveSite(@RequestBody Site site)
    {
        return siteService.saveSite(site);
    }

}

