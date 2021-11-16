package dev.evertonsavio.app.logfileservice.models;

public class LogRequest {

    private String mac;
    private String payload;

    public LogRequest() {
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

}
