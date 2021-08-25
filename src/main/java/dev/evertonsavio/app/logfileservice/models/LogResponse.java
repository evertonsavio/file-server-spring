package dev.evertonsavio.app.logfileservice.models;

public class LogResponse {

    private String mac;
    private String payload;
    private Long date;

    public LogResponse() {
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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
