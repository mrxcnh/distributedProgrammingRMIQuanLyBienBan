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
import java.util.logging.Level;
import java.util.logging.Logger;
import registry.Register;
import remoteInterface.RemoteInterface;

/**
 *
 * @author thanhdovan
 */
public class PermissionModel {
    public String getPermission(User user, Meeting meeting){
        try {
            RemoteInterface stub = Register.registry();
            String o = stub.getPermission(user,meeting);
            return o;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int addPermission(User user, Meeting meeting, String permission){
        try {
            RemoteInterface stub = Register.registry();
            int o = stub.addPermission(user,meeting, permission);
            return o;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int deletePermission(int meetingId){
        try {
            RemoteInterface stub = Register.registry();
            int o = stub.deletePermission(meetingId);
            return o;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int deletePermission(int userId, int meetingId){
        try {
            RemoteInterface stub = Register.registry();
            int o = stub.deletePermission(userId, meetingId);
            return o;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MeetingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
