/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientController;

import entity.ReportPart;
import java.util.List;
import quanlybienbanclientModel.ReportPartModel;

/**
 *
 * @author thanhdovan
 */
public class ReportPartController {
    private ReportPartModel reportPartModel;

    public ReportPartController() {
        this.reportPartModel = new ReportPartModel();
    }
    
    
    public int deleteReportPart(int reportPartId){
        return reportPartModel.deleteReportPart(reportPartId);
    }
    public int uploadFile(ReportPart reportPart){
        return reportPartModel.uploadFile(reportPart);
    }
    
    public List<ReportPart> getReportPartIds(int i, int y){
        return reportPartModel.getReportPartIds(i, y);
    }
    
    public String getReportPartContent(int reportPartId){
        return reportPartModel.getReportPartContent(reportPartId);
    }
    
    public int deleteReportParts(int meetingId){
        return reportPartModel.deleteReportParts(meetingId);
    }
}
