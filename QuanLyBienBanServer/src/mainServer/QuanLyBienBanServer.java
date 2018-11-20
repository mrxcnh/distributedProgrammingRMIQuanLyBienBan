/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainServer;

import remoteImpl.RemoteImpl;

/**
 *
 * @author thanhdovan
 */
public class QuanLyBienBanServer{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUIServer guiserver = new GUIServer();
        guiserver.setResizable(false);
        guiserver.setVisible(true);
    }
    
}
