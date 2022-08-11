package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.utils.ActionEnum;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${request.write.register.filename}")
    private String filenameToRegisterRequest;

    private Integer numberIpsGame;
    private String[] ipsValues;

    private Random r = new Random();

    @Autowired
    HackerDetector hackerDetector;

    public MyDaemonWriter(@Value("${ips.number.simulated}")Integer ipsNumber){
        numberIpsGame = ipsNumber;
        ipsValues = new String[numberIpsGame];
        for (int i=0; i<numberIpsGame;i++){
            ipsValues[i] = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
        }
    }
    //Escribimos un registo cada x ms
    @Scheduled(fixedRateString = "${daemon.writer.request.ms}")
    public void registerAttempLogin() throws IOException {
        File resource = new ClassPathResource(filenameToRegisterRequest).getFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(resource.getAbsolutePath(),true));

        String line= makeLineToWrite();
        writer.write(line);

        writer.close();

        String result = hackerDetector.parseLine(line);
        if (result != null)
            System.out.println("Attemp HACKING from IP: " + result);


    }

    private String makeLineToWrite (){

        String ip = ipsValues[r.nextInt(numberIpsGame)];
        String operation = ActionEnum.values()[r.nextInt(ActionEnum.values().length)].toString();
        String user = RandomStringUtils.random(5,true,false)+"."+RandomStringUtils.random(5,true,false);
        return  ip+","+System.currentTimeMillis()+","+operation+","+user+"\n";

    }
}
