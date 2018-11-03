/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientController;

import entity.Meeting;
import java.util.List;
import quanlybienbanclientModel.MeetingModel;

/**
 *
 * @author thanhdovan
 */
public class MeetingController {
    private MeetingModel meetingModel;

    public MeetingController() {
        this.meetingModel = new MeetingModel();
    }
    
    public List<Meeting> getMeetings(){
        return meetingModel.getMeetings();
    }

    public Meeting getMeeting(int id){
        return meetingModel.getMeeting(id);
    }

    public int addMeeting(Meeting meeting){
        return meetingModel.addMeeting(meeting);
    }
    
    public int editMeeting(Meeting meeting){
        return meetingModel.editMeeting(meeting);
    }
    
    public int deleteMeeting(Meeting meeting){
        return meetingModel.deleteMeeting(meeting);
    }
}
