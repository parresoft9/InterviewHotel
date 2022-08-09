package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyDaemon {

    @Autowired
    ManagerContentFile managerContentFile;


    @Scheduled(fixedRateString = "${daemon.read.registers.time}")
    public void doSomething() throws IOException {
        managerContentFile.processLote(System.currentTimeMillis());
    }
}
