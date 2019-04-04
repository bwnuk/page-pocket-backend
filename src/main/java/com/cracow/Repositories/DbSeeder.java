package com.cracow.Repositories;

import com.cracow.Entities.Site;
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
                "First",
                "www.google.pl",
                Arrays.asList(
                        "GOOGLE", "INNYTAG"
                )
        );

        this.siteRepository.save(strona1);

    }
}
