package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String[] linesResult = text.split("/n");
        for (String lineText: linesResult){
            LineDTO line = parseLineFromFile(lineText);
            if (line.getDateMs()<= time)
            pendingLines.add(parseLineFromFile(lineText));
        }
        return pendingLines;
    }

    private LineDTO parseLineFromFile (String line){
        String [] values = line.split(",");
        return new LineDTO(values[0],Long.parseLong(values[1]),values[2],values[3]);
    }

    private String processLote (){
        for (LineDTO line : pendingLines){
            if (!mapDetection.containsKey(line.getIp()) && line.getOperation().equals("SIGNIN_FAILURE")){
                mapDetection.put(line.getIp(),1);
            } else {
                int detecciones = mapDetection.get(line.getIp());
                if (line.getOperation().equals("SIGNIN_FAILURE")){
                    mapDetection.remove(line.getIp());
                    mapDetection.put(line.getIp(),detecciones++);
                }
            }
        }
        return null;
    }

}
