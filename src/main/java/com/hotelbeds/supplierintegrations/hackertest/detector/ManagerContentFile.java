package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.utils.ActionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ManagerContentFile {

    @Value("${request.read.register.filename}")
    private String filenameToReadRequest;

    @Value("${period.hacking.minutes}")
    private int periodHacking;


    public List<LineDTO> getLinesFailureFromIpAndTimeStamp (String ip, long timestamp) throws IOException {

        File resource = new ClassPathResource(filenameToReadRequest).getFile();
        String text = new String(Files.readAllBytes(resource.toPath()));
        String[] linesResult = text.split("\n");

        return Arrays.stream(linesResult).filter(x -> x!=null && !x.isEmpty())
                .map(x -> parseLineFromFile(x))
                .filter(x -> x.getAction().equals(ActionEnum.SIGNIN_FAILURE.toString()))
                .filter(x -> x.getIp().equals(ip))
                .filter(x -> timestamp -(periodHacking*60*1000) <=x.getDateMs() && x.getDateMs()<=timestamp)
                .collect(Collectors.toList());

    }

    public LineDTO parseLineFromFile (String line){
        String [] values = line.split(",");
        return new LineDTO(values[0],Long.parseLong(values[1]),values[2],values[3]);
    }

    public long getMinutesBetweenTimestamps(long t1, long t2){
        return Math.abs(TimeUnit.MILLISECONDS.toMinutes(t2-t1));
    }

}
