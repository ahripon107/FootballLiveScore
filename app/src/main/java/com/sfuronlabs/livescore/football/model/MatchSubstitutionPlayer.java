package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchSubstitutionPlayer implements Serializable{
    private String off;
    private String on;
    private String on_id;
    private String off_id;
    private String minute;

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getOn_id() {
        return on_id;
    }

    public void setOn_id(String on_id) {
        this.on_id = on_id;
    }

    public String getOff_id() {
        return off_id;
    }

    public void setOff_id(String off_id) {
        this.off_id = off_id;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "MatchSubstitutionPlayer{" +
                "off='" + off + '\'' +
                ", on='" + on + '\'' +
                ", on_id='" + on_id + '\'' +
                ", off_id='" + off_id + '\'' +
                ", minute='" + minute + '\'' +
                '}';
    }
}
