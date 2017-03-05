package com.sfuronlabs.ripon.livecricketscore;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {
    private String id;
    private String time;
    private String status;
    private String visitorTeamId;
    private String localTeamId;
    private String date;
    private String scoreTime;
    private String localTeam;
    private String visitorTeam;
    private String leagueId;
    private String leagueName;
    private String fileGroup;
    private String stageId;
    private String stageRound;
    private String stageName;
    private String season;
    private String leagueKey;
    private String localCoach;
    private String localCoachId;
    private String visitorCoach;
    private String visitorCoachId;
    private String localTeamOrg;
    private String visitorTeamOrg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("gs_visitorteamid")
    public String getVisitorTeamId() {
        return visitorTeamId;
    }

    public void setVisitorTeamId(String visitorTeamId) {
        this.visitorTeamId = visitorTeamId;
    }

    @JsonProperty("gs_localteamid")
    public String getLocalTeamId() {
        return localTeamId;
    }

    public void setLocalTeamId(String localTeamId) {
        this.localTeamId = localTeamId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("scoretime")
    public String getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(String scoreTime) {
        this.scoreTime = scoreTime;
    }

    @JsonProperty("localteam")
    public String getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(String localTeam) {
        this.localTeam = localTeam;
    }

    @JsonProperty("visitorteam")
    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    @JsonProperty("leagueid")
    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    @JsonProperty("leaguename")
    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    @JsonProperty("filegroup")
    public String getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(String fileGroup) {
        this.fileGroup = fileGroup;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStageRound() {
        return stageRound;
    }

    public void setStageRound(String stageRound) {
        this.stageRound = stageRound;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getLeagueKey() {
        return leagueKey;
    }

    public void setLeagueKey(String leagueKey) {
        this.leagueKey = leagueKey;
    }

    @JsonProperty("coachLocal")
    public String getLocalCoach() {
        return localCoach;
    }

    public void setLocalCoach(String localCoach) {
        this.localCoach = localCoach;
    }

    @JsonProperty("coachLocalId")
    public String getLocalCoachId() {
        return localCoachId;
    }

    public void setLocalCoachId(String localCoachId) {
        this.localCoachId = localCoachId;
    }

    @JsonProperty("coachVisitor")
    public String getVisitorCoach() {
        return visitorCoach;
    }

    public void setVisitorCoach(String visitorCoach) {
        this.visitorCoach = visitorCoach;
    }

    @JsonProperty("coachVisitorId")
    public String getVisitorCoachId() {
        return visitorCoachId;
    }

    public void setVisitorCoachId(String visitorCoachId) {
        this.visitorCoachId = visitorCoachId;
    }

    @JsonProperty("localteam_org")
    public String getLocalTeamOrg() {
        return localTeamOrg;
    }

    public void setLocalTeamOrg(String localTeamOrg) {
        this.localTeamOrg = localTeamOrg;
    }

    @JsonProperty("visitorteam_org")
    public String getVisitorTeamOrg() {
        return visitorTeamOrg;
    }

    public void setVisitorTeamOrg(String visitorTeamOrg) {
        this.visitorTeamOrg = visitorTeamOrg;
    }
}
