package com.bajajFinserv.javaApp.config;

import com.bajajFinserv.javaApp.service.webhookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class appStartupRunner implements CommandLineRunner {

    private final webhookService webhookService;

    public appStartupRunner(webhookService webhookService) {
        this.webhookService = webhookService;
    }

    
}
