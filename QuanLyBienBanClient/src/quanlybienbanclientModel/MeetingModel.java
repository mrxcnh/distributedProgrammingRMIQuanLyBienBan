/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientModel;

import entity.Meeting;
import entity.User;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlybienbanclientView.GUIAdminClient;
import registry.Register;
import remoteInterface.RemoteInterface;

/**
 *
 * @author thanhdovan
 */
public class MeetingModel {
    public List<Meeting> getMeetings(){
        List<Meeting> list = null;
        try {
            RemoteInterface stub = Register.registry();
            list = (List) stub.getMeetings();
            return list;
        } catch (NotBoundException | RemoteException ex) {
            Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Meeting getMeeting(int id){
        Meeting user = null;
        try{
            RemoteInterface stub = Register.registry();
            user = stub.getMeeting(id);
            return user;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int addMeeting(Meeting user){
        try {
            RemoteInterface stub = Register.registry();
            int i = stub.addMeeting(user);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int editMeeting(Meeting user){
        try {
            RemoteInterface stub = Register.registry();
            int i = stub.editMeeting(user);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int deleteMeeting(Meeting user){
        try{
            RemoteInterface stub = Register.registry();
            int i = stub.deleteMeeting(user);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int addReporter(User user, Meeting meeting){
        try {
            RemoteInterface stub = Register.registry();
            int i = stub.addReporter(user, meeting);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<Integer> getReporterIds(int meetingId){
        try {
            RemoteInterface stub = Register.registry();
            List<Integer> reporterIds = stub.getReporterIds(meetingId);
            return reporterIds;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int deleteReporter(User user, Meeting meeting){
        try {
            RemoteInterface stub = Register.registry();
            int i = stub.deleteReporter(user, meeting);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getMeetingCreatorId(Meeting meeting){
        try {
            RemoteInterface stub = Register.registry();
            int i = stub.getMeetingCreatorId(meeting);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
