package com.cracow.Controllers;

import com.cracow.Entities.Site;
import com.cracow.Repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class SiteController {

    private SiteRepository siteRepository;

    public SiteController(SiteRepository siteRepository) {
        this.siteRepository=siteRepository;

    }

    @GetMapping("/getall")
    public Iterable<Site> getAll()
    {
        return siteRepository.findAll();
    }

    /*@GetMapping("/getbyid")
    public Optional<Site> getById(@RequestParam Long id)
    {
        return siteService.findById(id);
    }*/
}

