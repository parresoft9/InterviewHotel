package com.hotelbeds.supplierintegrations.hackertest.detector;

public class LineDTO {

    private String ip;
    private long dateMs;
    private String operation;
    private String user;

    public LineDTO(String ip, long dateMs, String operation, String user) {
        this.ip = ip;
        this.dateMs = dateMs;
        this.operation = operation;
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getDateMs() {
        return dateMs;
    }

    public void setDateMs(long dateMs) {
        this.dateMs = dateMs;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
