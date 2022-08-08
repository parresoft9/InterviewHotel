package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

@Component
public class MyDaemon {

    @Autowired
    ManagerContentFile managerContentFile;

    private long timeToexecute;

    @Scheduled(fixedRate = 1000)
    public void doSomething() throws IOException {
        managerContentFile.processLote(System.currentTimeMillis());
    }
}
