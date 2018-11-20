/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author thanhdovan
 */
public class PeopleEditReport implements Serializable{
    private int id;
    private int userId;
    private int reportId;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
    
}
