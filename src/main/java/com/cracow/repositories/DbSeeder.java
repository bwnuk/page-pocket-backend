package com.cracow.repositories;

import com.cracow.entities.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DbSeeder implements CommandLineRunner {

    private SiteRepository siteRepository;

    @Autowired
    public DbSeeder(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Site strona1 = new Site(
                "a",
                "www.google.pl",
                Arrays.asList(
                        "GOOGLE", "INNYTAG"
                )
        );

        Site strona2 = new Site(
                "b",
                "www.redtube.pl",
                Arrays.asList(
                        "porn", "hot"
                )
        );

        //this.siteRepository.save(strona1);
        //this.siteRepository.save(strona2);

    }
}
