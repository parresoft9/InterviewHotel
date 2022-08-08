package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

@Component
public class MyDaemonWriter {

    @Autowired
    ManagerContentFile managerContentFile;

    private long timeToexecute;

    @Scheduled(fixedRate = 1000)
    public void doSomething() throws IOException {
//        File resource = new ClassPathResource("registers.txt").getFile();
//        String text = new String(Files.readAllBytes(resource.toPath()));
        BufferedWriter writer = new BufferedWriter(new FileWriter("./registers.txt",true));
        writer.write(new Date(System.currentTimeMillis()).toString()+"\n");

        writer.close();

    }
}
