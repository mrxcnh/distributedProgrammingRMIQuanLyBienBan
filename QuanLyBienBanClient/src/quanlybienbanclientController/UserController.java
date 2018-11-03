/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientController;

import entity.User;
import java.util.List;
import quanlybienbanclientModel.UserModel;

/**
 *
 * @author thanhdovan
 */
public class UserController {
    private UserModel userModel;

    public UserController() {
        this.userModel = new UserModel();
    }
    
    public List<User> getUsers(){
        return userModel.getUsers();
    }
    
    public User getUser(String username, String password){
        return userModel.getUser(username,password);
    }

    public User getUser(int id){
        return userModel.getUser(id);
    }

    public int addUser(User user){
        return userModel.addUser(user);
    }
    
    public int editUser(User user){
        return userModel.editUser(user);
    }
    
    public int deleteUser(User user){
        return userModel.deleteUser(user);
    }
}
