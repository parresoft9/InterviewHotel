package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.utils.ActionEnum;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class ManagerContentFileTests {

    @Test
    public void test1() throws IOException {
        File resource = new ClassPathResource("processing.txt").getFile();
        String text = new String(Files.readAllBytes(resource.toPath()));
        String[] linesResult = text.split("\n");
        List<LineDTO> lines = Arrays.stream(linesResult).filter(x -> x!=null && !x.isEmpty()).map(x -> parseLineFromFile(x)).filter(x -> x.getAction().equals(ActionEnum.SIGNIN_FAILURE.toString())).collect(Collectors.toList());
        lines.get(0);

    }

    public LineDTO parseLineFromFile (String line){
        String [] values = line.split(",");
        return new LineDTO(values[0],Long.parseLong(values[1]),values[2],values[3]);
    }

    @Test
    public void getLinesFailureFromIpAndTimeStamp () throws IOException {
        String ip = "123.187.29.221";
        long timestamp = 1659974007241L;
        List<LineDTO> lines = new ArrayList<>();
        File resource = new ClassPathResource("processing.txt").getFile();
        String text = new String(Files.readAllBytes(resource.toPath()));
        String[] linesResult = text.split("\n");
        lines = Arrays.stream(linesResult).filter(x -> x!=null && !x.isEmpty())
                .map(x -> parseLineFromFile(x))
                .filter(x -> x.getAction().equals(ActionEnum.SIGNIN_FAILURE.toString()))
                .filter(x -> x.getIp().equals(ip))
                .filter(x -> timestamp -50000 <=x.getDateMs() && x.getDateMs()<=timestamp)
                .collect(Collectors.toList());
        lines.stream().skip(lines.size()-5).forEach(x -> System.out.println(x.toString()));

    }

    @Test
    public void testFunction(){
        ManagerContentFile managerContentFile = new ManagerContentFile();
        long minutes = managerContentFile.getMinutesBetweenTimestamps(1659974007241L , 1659974067241L);
        System.out.println(minutes);
        long minutes1 = managerContentFile.getMinutesBetweenTimestamps(1659974037241L , 1659974007241L);
        System.out.println(Math.abs(minutes1));
    }
}
