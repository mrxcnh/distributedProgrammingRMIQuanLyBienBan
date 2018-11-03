/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author thanhdovan
 */
public class ReportPart implements Serializable {
    private int id;
    private int meetingId;
    private String fileName;
    private int type;
    private File content;

    public String getFileName() {
        return fileName;
    }
     
    public int getId() {
        return id;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public int getType() {
        return type;
    }

    public File getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContent(File content) {
        this.content = content;
    }
    
}
