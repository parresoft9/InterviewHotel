package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.utils.ActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Component
public class ManagerContentFile {

    @Autowired
    HackerDetector hackerDetector;

    private List<LineDTO> pendingLines = new ArrayList<>();
    private List<LineDTO> candidateLines = new ArrayList<>();
    private int limitToDetect = 5;
    private Map<String, Integer> mapDetection= new HashMap();

    private List<LineDTO> getLinesUntilTime (long time) throws IOException {
        List<LineDTO> lines = new ArrayList<>();

        File resource = new ClassPathResource("registers.txt").getFile();
        String text = new String(Files.readAllBytes(resource.toPath()));
        String[] linesResult = text.split("\n");
        for (String lineText: linesResult){
            if (!lineText.isEmpty()){
                LineDTO line = parseLineFromFile(lineText);
                if (line.getDateMs()<= time && line.getDateMs()>= time - (1000))
                    pendingLines.add(parseLineFromFile(lineText));
            }
        }
        return pendingLines;
    }

    private LineDTO parseLineFromFile (String line){
        String [] values = line.split(",");
        return new LineDTO(values[0],Long.parseLong(values[1]),values[2],values[3]);
    }

    public void processLote (long time) throws IOException {
        System.out.println("Entre "+(time- (1000))+" ms("+new Date(time-(1000)).toString()+") y "+time+" ms("+new Date(time).toString()+")");
        getLinesUntilTime(time);
        for (LineDTO line : pendingLines){
            if (!mapDetection.containsKey(line.getIp()) && line.getAction().equals(ActionEnum.SIGNIN_FAILURE.toString())){
                mapDetection.put(line.getIp(),1);
            } else {
                if (line.getAction().equals(ActionEnum.SIGNIN_FAILURE.toString())){
                    int detecciones = mapDetection.get(line.getIp())+1;
                    mapDetection.remove(line.getIp());
                    mapDetection.put(line.getIp(),detecciones);
                    if (detecciones==5){
                        candidateLines.add(line);
                    }
                }
            }
        }
        lookingForHacker(mapDetection);
        mapDetection.clear();
        pendingLines.clear();
        candidateLines.clear();
    }

    private void lookingForHacker ( Map<String,Integer> detects){
       mapDetection.forEach((k,v) -> System.out.println("IP: " + k + ": FAILED ATTEMPS: " + v));
       candidateLines.forEach((line) -> System.out.println(line.toString()));
    }

}
