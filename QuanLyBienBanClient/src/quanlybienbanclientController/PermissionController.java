/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientController;

import entity.Meeting;
import entity.User;
import quanlybienbanclientModel.PermissionModel;

/**
 *
 * @author thanhdovan
 */
public class PermissionController {
    private PermissionModel permissionModel;
    public PermissionController(){
        permissionModel = new PermissionModel();
    }
    public String getPermission(User user, Meeting meeting){
        return permissionModel.getPermission(user, meeting);
    }
    public int addPermission(User user, Meeting meeting, String permission){
        return permissionModel.addPermission(user, meeting, permission);
    }
    public int deletePermission(int meetingId){
        return permissionModel.deletePermission(meetingId);
    }
    public int deletePermission(int userId, int meetingId){
        return permissionModel.deletePermission(userId, meetingId);
    }
    
}
