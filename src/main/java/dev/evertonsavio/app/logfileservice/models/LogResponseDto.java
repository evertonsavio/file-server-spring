package dev.evertonsavio.app.logfileservice.models;

import java.util.Date;

public class LogResponseDto {

    private String mac;
    private String payload;
    private Date date;

    public LogResponseDto() {
    }

    public LogResponseDto(String mac, String payload, Date date) {
        this.mac = mac;
        this.payload = payload;
        this.date = date;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
