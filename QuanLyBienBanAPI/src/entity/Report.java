/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author thanhdovan
 */
public class Report implements Serializable{
    private int id;
    private int meetingId;
    private String reportName;
    private List<PersonContentTime> personContentTimes;
    private String reportContent;
    private Time timeCreate;
    private String authors;

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
    
    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public void setTimeCreate(Time timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getReportName() {
        return reportName;
    }

    public Time getTimeCreate() {
        return timeCreate;
    }
    public Report() {
    }

    public int getId() {
        return id;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public List<PersonContentTime> getPersonContentTimes() {
        return personContentTimes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public void setPersonContentTimes(List<PersonContentTime> personContentTimes) {
        this.personContentTimes = personContentTimes;
    }
    
}
