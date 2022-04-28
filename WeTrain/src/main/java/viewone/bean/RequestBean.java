package viewone.bean;

import java.time.LocalDateTime;

public class RequestBean {
    private int id;
    private LocalDateTime requestDate;
    private String info;
    private String athleteFc;
    private String athleteUsername;
    private String trainer;

    public RequestBean(int id, LocalDateTime requestDate, String info, String athleteFc, String athleteUsername, String trainer) {
        this.id = id;
        this.requestDate = requestDate;
        this.info = info;
        this.athleteFc = athleteFc;
        this.athleteUsername = athleteUsername;
        this.trainer = trainer;
    }

    public RequestBean(String info, String athleteFc, String athleteUsername, String trainer) {
        this.requestDate = LocalDateTime.now();
        setInfo(info);
        this.athleteFc = athleteFc;
        this.athleteUsername = athleteUsername;
        this.trainer = trainer;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public String getAthleteFc() {
        return athleteFc;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAthleteUsername() {
        return athleteUsername;
    }
}