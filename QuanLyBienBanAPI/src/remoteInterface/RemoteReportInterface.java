/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteInterface;

import entity.Report;
import entity.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author thanhdovan
 */
public interface RemoteReportInterface extends Remote {
    public void updateReportTable(List<Report> list) throws RemoteException;
    public void updateUserEdittingTable(List<User> list, int reportId) throws RemoteException;
    public void updateReportContent(String content, int reportId) throws RemoteException;
}
