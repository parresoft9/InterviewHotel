package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.util.Date;

public class LineDTO {


    private String ip;
    private long dateMs;
    private String action;
    private String username;

    public LineDTO(String ip, long dateMs, String action, String username) {
        this.ip = ip;
        this.dateMs = dateMs;
        this.action = action;
        this.username = username;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LineDTO{" +
                "ip='" + ip + '\'' +
                ", dateMs=" +dateMs+ " FECHA: "+ new Date(dateMs) +
                ", action='" + action + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
