package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointTable implements Serializable{
    private String filegroup;
    private String leaguename;

    private List<PointTableGroup> groups;

    public String getFilegroup() {
        return filegroup;
    }

    public void setFilegroup(String filegroup) {
        this.filegroup = filegroup;
    }

    public String getLeaguename() {
        return leaguename;
    }

    public void setLeaguename(String leaguename) {
        this.leaguename = leaguename;
    }

    public List<PointTableGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<PointTableGroup> groups) {
        this.groups = groups;
    }
}
