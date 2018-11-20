/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteInterface;

import entity.Meeting;
import entity.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author thanhdovan
 */
public interface RemoteManagerInterface extends Remote {
    public void updateMeetingTable(List<Meeting> list) throws RemoteException;
    public void updatePermissionTable(List<User> list, Meeting meeting) throws RemoteException;
    public void updateReporterComboBox(int meetingId) throws RemoteException;
    public void updateReporterTable(int meetingId) throws RemoteException;
    public void updateUserNotSharedYet(int meetingId) throws RemoteException;
}
