/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientModel;

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
public class UserModel {
    public List<User> getUsers(){
        List<User> list = null;
        try {
            RemoteInterface stub = Register.registry();
            list = (List) stub.getUsers();
            return list;
        } catch (NotBoundException | RemoteException ex) {
            Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public User getUser(String username, String password){
        RemoteInterface stub;
        User user = null;
        try {
            stub = Register.registry();
            user = (User) stub.getUser(username, password);
            return user;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User getUser(int id){
        User user = null;
        try{
            RemoteInterface stub = Register.registry();
            user = stub.getUser(id);
            return user;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int addUser(User user){
        try {
            RemoteInterface stub = Register.registry();
            int i = stub.addUser(user);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int editUser(User user){
        try {
            RemoteInterface stub = Register.registry();
            int i = stub.editUser(user);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int deleteUser(User user){
        try{
            RemoteInterface stub = Register.registry();
            int i = stub.deleteUser(user);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
