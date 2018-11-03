/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientController;

import entity.ReportPart;
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
    
    public int uploadFile(ReportPart reportPart){
        return reportPartModel.uploadFile(reportPart);
    }
}
