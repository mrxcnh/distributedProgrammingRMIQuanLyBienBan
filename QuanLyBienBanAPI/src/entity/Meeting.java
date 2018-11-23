/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author thanhdovan
 */
public class Meeting implements Serializable{
    private int id;
    private String title;
    private Date date;
    private String timeStart;
    private int userCreateId;
    public Meeting() {
    }
    //setter

    public void setUserCreateId(int userCreateId) {
        this.userCreateId = userCreateId;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }
    //end setter
    //getter

    public int getUserCreateId() {
        return userCreateId;
    }
    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getTimeStart() {
        return timeStart;
    }
    //end getter
}
