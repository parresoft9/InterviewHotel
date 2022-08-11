package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.io.IOException;

public interface HackerDetector {
    String parseLine(String line) throws IOException;
}
