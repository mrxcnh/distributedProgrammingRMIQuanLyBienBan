/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteInterface;

import entity.Meeting;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author thanhdovan
 */
public interface TestRemoteClientInterface extends Remote {
    
    public String printmsg(String msg) throws RemoteException;
    
}
