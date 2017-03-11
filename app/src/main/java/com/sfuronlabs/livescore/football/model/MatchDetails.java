package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetails implements Serializable{
    private String id;
    private String status;
    private List<MatchEvent> events;
    private String localTeamId;
    private String visitorTeamId;
    private String scoreLine;
    private MatchLineup lineups;
    private String localTeam;
    private String visitorTeam;
    private String venue;
    private String leagueId;
    private String venueId;
    private String date;
    private String time;
    private String leagueName;
    private String countryOfLeague;
    private String season;
    private String leagueKey;
    private String localTeamPastMatches;
    private String visitorTeamPastMatches;
    private List<MatchSummary> localTeamPastMatchesList;
    private List<MatchSummary> visitorTeamPastMatchesList;
    private MatchCommentry commentaries;
    private String coachLocal;
    private String coachVisitor;
    private String coachLocalId;
    private String coachVisitorId;
    private String halfTime;
    private TeamStanding localTeamStanding;
    private TeamStanding visitorTeamStanding;
    private Head2Head stats;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MatchEvent> getEvents() {
        return events;
    }

    public void setEvents(List<MatchEvent> events) {
        this.events = events;
    }

    @JsonProperty("gs_localteamid")
    public String getLocalTeamId() {
        return localTeamId;
    }

    public void setLocalTeamId(String localTeamId) {
        this.localTeamId = localTeamId;
    }

    @JsonProperty("gs_visitorteamid")
    public String getVisitorTeamId() {
        return visitorTeamId;
    }

    public void setVisitorTeamId(String visitorTeamId) {
        this.visitorTeamId = visitorTeamId;
    }

    @JsonProperty("scoretime")
    public String getScoreLine() {
        return scoreLine;
    }

    public void setScoreLine(String scoreLine) {
        this.scoreLine = scoreLine;
    }

    public MatchLineup getLineups() {
        return lineups;
    }

    public void setLineups(MatchLineup lineups) {
        this.lineups = lineups;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @JsonProperty("leagueid")
    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    @JsonProperty("venueid")
    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("leaguename")
    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    @JsonProperty("filegroup")
    public String getCountryOfLeague() {
        return countryOfLeague;
    }

    public void setCountryOfLeague(String countryOfLeague) {
        this.countryOfLeague = countryOfLeague;
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

    @JsonProperty("localteamshape")
    public String getLocalTeamPastMatches() {
        return localTeamPastMatches;
    }

    public void setLocalTeamPastMatches(String localTeamPastMatches) {
        this.localTeamPastMatches = localTeamPastMatches;
    }

    @JsonProperty("visitorteamshape")
    public String getVisitorTeamPastMatches() {
        return visitorTeamPastMatches;
    }

    public void setVisitorTeamPastMatches(String visitorTeamPastMatches) {
        this.visitorTeamPastMatches = visitorTeamPastMatches;
    }

    @JsonProperty("localteamshapefixtures")
    public List<MatchSummary> getLocalTeamPastMatchesList() {
        return localTeamPastMatchesList;
    }

    public void setLocalTeamPastMatchesList(List<MatchSummary> localTeamPastMatchesList) {
        this.localTeamPastMatchesList = localTeamPastMatchesList;
    }

    @JsonProperty("visitorteamshapefixtures")
    public List<MatchSummary> getVisitorTeamPastMatchesList() {
        return visitorTeamPastMatchesList;
    }

    public void setVisitorTeamPastMatchesList(List<MatchSummary> visitorTeamPastMatchesList) {
        this.visitorTeamPastMatchesList = visitorTeamPastMatchesList;
    }

    public MatchCommentry getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(MatchCommentry commentaries) {
        this.commentaries = commentaries;
    }

    public String getCoachLocal() {
        return coachLocal;
    }

    public void setCoachLocal(String coachLocal) {
        this.coachLocal = coachLocal;
    }

    public String getCoachVisitor() {
        return coachVisitor;
    }

    public void setCoachVisitor(String coachVisitor) {
        this.coachVisitor = coachVisitor;
    }

    public String getCoachLocalId() {
        return coachLocalId;
    }

    public void setCoachLocalId(String coachLocalId) {
        this.coachLocalId = coachLocalId;
    }

    public String getCoachVisitorId() {
        return coachVisitorId;
    }

    public void setCoachVisitorId(String coachVisitorId) {
        this.coachVisitorId = coachVisitorId;
    }

    @JsonProperty("ht")
    public String getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(String halfTime) {
        this.halfTime = halfTime;
    }

    @JsonProperty("localteamStanding")
    public TeamStanding getLocalTeamStanding() {
        return localTeamStanding;
    }

    public void setLocalTeamStanding(TeamStanding localTeamStanding) {
        this.localTeamStanding = localTeamStanding;
    }

    @JsonProperty("visitorteamStanding")
    public TeamStanding getVisitorTeamStanding() {
        return visitorTeamStanding;
    }

    public void setVisitorTeamStanding(TeamStanding visitorTeamStanding) {
        this.visitorTeamStanding = visitorTeamStanding;
    }

    public Head2Head getStats() {
        return stats;
    }

    public void setStats(Head2Head stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        String evnt = "";
        String localPastMatch = "";
        String visitorPastMatch = "";
        String loclTmStng = "";
        String vstrTmStng = "";
        String lnup = "";
        String cmntrs = "";
        String stts = "";
        for (int i=0;i<events.size();i++) {
            evnt += events.get(i).toString();
        }

        if (localTeamPastMatchesList != null) {
            for (int i=0;i<localTeamPastMatchesList.size();i++) {
                localPastMatch += localTeamPastMatchesList.get(i).toString();
            }
        }

        if (visitorTeamPastMatchesList != null) {
            for (int i=0;i<visitorTeamPastMatchesList.size();i++) {
                visitorPastMatch += visitorTeamPastMatchesList.get(i).toString();
            }
        }

        if (lineups != null) {
            lnup = lineups.toString();
        }

        if (commentaries != null) {
            cmntrs = commentaries.toString();
        }

        if (stats != null) {
            stts = stats.toString();
        }
        if (localTeamStanding != null) loclTmStng = localTeamStanding.toString();
        if (visitorTeamStanding != null) vstrTmStng = visitorTeamStanding.toString();
        return "MatchDetails{" +
                "status='" + status + '\'' +
                ", events=" + evnt +
                ", localTeamId='" + localTeamId + '\'' +
                ", visitorTeamId='" + visitorTeamId + '\'' +
                ", scoreLine='" + scoreLine + '\'' +
                ", lineups=" + lnup +
                ", localTeam='" + localTeam + '\'' +
                ", visitorTeam='" + visitorTeam + '\'' +
                ", venue='" + venue + '\'' +
                ", leagueId='" + leagueId + '\'' +
                ", venueId='" + venueId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", countryOfLeague='" + countryOfLeague + '\'' +
                ", season='" + season + '\'' +
                ", leagueKey='" + leagueKey + '\'' +
                ", localTeamPastMatches='" + localTeamPastMatches + '\'' +
                ", visitorTeamPastMatches='" + visitorTeamPastMatches + '\'' +
                ", localTeamPastMatchesList=" + localPastMatch +
                ", visitorTeamPastMatchesList=" + visitorPastMatch +
                ", commentaries=" + cmntrs +
                ", coachLocal='" + coachLocal + '\'' +
                ", coachVisitor='" + coachVisitor + '\'' +
                ", coachLocalId='" + coachLocalId + '\'' +
                ", coachVisitorId='" + coachVisitorId + '\'' +
                ", halfTime='" + halfTime + '\'' +
                ", localTeamStanding=" + loclTmStng +
                ", visitorTeamStanding=" + vstrTmStng +
                ", head2Head=" + stts +
                '}';
    }
}
