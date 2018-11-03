/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientModel;

import entity.ReportPart;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import registry.Register;
import remoteInterface.RemoteInterface;

/**
 *
 * @author thanhdovan
 */
public class ReportPartModel {
    private String reportPartContent;
    
    public int uploadFile(ReportPart reportPart){
        try {
            RemoteInterface stub = Register.registry();
            System.out.println(reportPart.getContent().getAbsolutePath());
            int i = stub.uploadFile(reportPart);
            return i;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportPartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<ReportPart> getReportPartIds(int i, int y){
        List<ReportPart> list = null;
        try{
            RemoteInterface stub = Register.registry();
            list = stub.getReportParts(i, y);
            return list;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportPartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String getReportPartContent(int reportPartId){
        String s = null;
        try{
            RemoteInterface stub = Register.registry();
            s = stub.getReportPartContent(reportPartId);
            return s;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportPartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    
    public String getReportPartContent() {
        return reportPartContent;
    }

    public void setReportPartContent(String reportPartContent) {
        this.reportPartContent = reportPartContent;
    }
}
