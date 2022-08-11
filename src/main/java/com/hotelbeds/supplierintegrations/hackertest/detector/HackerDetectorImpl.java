package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class HackerDetectorImpl implements HackerDetector{

    @Value("${request.failed.attemps.limit}")
    private int numberFailedAttemps;

    @Value("${info.extended}")
    private boolean moreInfo;

    @Autowired
    ManagerContentFile managerContentFile;



    @Override
    public String parseLine(String line) throws IOException {

        LineDTO lineDTO = managerContentFile.parseLineFromFile(line);
        List<LineDTO> failureLines5minutesForIp = managerContentFile.getLinesFailureFromIpAndTimeStamp(lineDTO.getIp(), lineDTO.getDateMs());
        if (moreInfo)
            printMoreInfo(failureLines5minutesForIp);
        return  failureLines5minutesForIp.size()>=numberFailedAttemps ? failureLines5minutesForIp.get(0).getIp(): null;
    }


    private void printMoreInfo (List<LineDTO> failureLines5minutesForIp){

        if (failureLines5minutesForIp.size()>=numberFailedAttemps)
                failureLines5minutesForIp.stream().skip(failureLines5minutesForIp.size()-numberFailedAttemps).forEach(x -> System.out.println(x.toString()));

    }
}
