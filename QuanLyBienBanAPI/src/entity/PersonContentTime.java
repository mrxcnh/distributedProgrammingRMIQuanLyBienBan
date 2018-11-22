/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanhdovan
 */
public class PersonContentTime implements Serializable, Comparable{
    private String name;
    private String content;
    private String timeBegin;
    private String timeEnd;

    public PersonContentTime() {
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public int compareTo(Object o) {
        String timeBegin = ((PersonContentTime)o).getTimeBegin();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            Date d1 = sdf.parse(timeBegin);
            Date d2 = sdf.parse(this.timeBegin);
            return (int) (d2.getTime() - d1.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(PersonContentTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public boolean equals(Object obj){
        try{
            PersonContentTime pct = (PersonContentTime) obj;
            return this.name.equals(pct.getName()) && this.content.equals(pct.getContent()) && this.timeBegin.equals(pct.getTimeBegin()) && this.timeEnd.equals(pct.getTimeEnd());
        }catch(Exception e){
            return false;
        }
    }
}
