/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author thanhdovan
 */
public class Meeting implements Serializable{
    private int id;
    private String title;
    private Date date;
    private Time timeStart;

    public Meeting() {
    }
    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }
    //end setter
    //getter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Time getTimeStart() {
        return timeStart;
    }
    //end getter
}
