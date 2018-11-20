/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteInterface;

import entity.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author thanhdovan
 */
public interface RemoteAdminInterface extends Remote{
    public void adminUpdateUserTable(List<User> list) throws RemoteException;
}
