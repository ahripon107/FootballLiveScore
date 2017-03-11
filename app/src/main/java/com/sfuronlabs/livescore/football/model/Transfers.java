package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transfers implements Serializable{
    private List<TransferPlayer> in;
    private List<TransferPlayer> out;

    public List<TransferPlayer> getIn() {
        return in;
    }

    public void setIn(List<TransferPlayer> in) {
        this.in = in;
    }

    public List<TransferPlayer> getOut() {
        return out;
    }

    public void setOut(List<TransferPlayer> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        String ins = "";
        String outs = "";

        for (int i=0;i<in.size();i++) {
            ins += in.get(i).toString();
        }

        for (int i=0;i<out.size();i++) {
            outs += out.get(i).toString();
        }
        return "Transfers{" +
                "in=" + ins +
                ", out=" + outs +
                '}';
    }
}
