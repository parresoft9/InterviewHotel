package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyDaemon {

    @Autowired
    ManagerContentFile managerContentFile;

    private long timeToexecute;

    @Scheduled(fixedRate = 5000)
    public void doSomething() {
        timeToexecute = System.currentTimeMillis();

        System.out.print("hola soy el demonio"+new Date(System.currentTimeMillis()));

    }
}
