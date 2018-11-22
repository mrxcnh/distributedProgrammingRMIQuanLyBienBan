/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteInterface;

import entity.Meeting;
import entity.ReportPart;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author thanhdovan
 */
public interface RemoteStaffInterface extends Remote {
    public void updateMeetingTable(List<Meeting> list) throws RemoteException;
    public void updateReportPartTable(List<ReportPart> list, int meetingId, String userUpload) throws RemoteException;
    public void updateStatus(int meetingId, int stat) throws RemoteException;
}
