package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.utils.ActionEnum;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

@Component
public class MyDaemonWriter {

    private String[] ipsValues= new String[10];

    private Random r = new Random();

    public MyDaemonWriter(){
        for (int i=0; i<5;i++){
            ipsValues[i] = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
        }
    }
    //Escribimos un registo cada 0,1seg 10 req/sec
    @Scheduled(fixedRate = 10)
    public void doSomething() throws IOException {
        File resource = new ClassPathResource("registers.txt").getFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(resource.getAbsolutePath(),true));

        String ip = ipsValues[r.nextInt(5)];
        String operation = ActionEnum.values()[r.nextInt(ActionEnum.values().length)].toString();
        String user = RandomStringUtils.random(5,true,false)+"."+RandomStringUtils.random(5,true,false);
        String toWrite = ip+","+System.currentTimeMillis()+","+operation+","+user+"\n";

//        System.out.print(toWrite);
        writer.write(toWrite);

        writer.close();

    }
}
