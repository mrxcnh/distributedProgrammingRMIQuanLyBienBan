/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpfile;

/**
 *
 * @author thanhdovan
 */
public class CheckReportPart {
    public static int reportPartType;
    public static boolean checkReportPart(String reportPartContent, int reportPartType){
        if (reportPartType == 0){
            String[] linesInCTPart = reportPartContent.split("\n");
            for (String line: linesInCTPart){
                if (line.length() != 0){
                    String[] parts = line.split("\\[");
                    if (parts.length != 2){
                        return false;
                    }
                    String[] timeparts = parts[1].split("\\~");
                    if (timeparts.length != 2){
                        return false;
                    }
                }
            }
            return true;
        }
        else if (reportPartType == 1){
            String[] linesInCTPart = reportPartContent.split("\n");
            for (String line: linesInCTPart){
                if (line.length() != 0){
                    String[] parts = line.split("\\[");
                    if (parts.length != 2){
                        return false;
                    }
                    String[] timeparts = parts[1].split("\\~");
                    if (timeparts.length != 2){
                        return false;
                    }
                }
            }
            return true;
        }
        else{
            String[] linesInTranscript = reportPartContent.split("\n");
            for (String line: linesInTranscript){
                if (line.length() != 0){
                    String[] parts = line.split("\\]");
                    if (parts.length != 2){
                        return false;
                    }
                    String[] timeparts = parts[0].split("\\~");
                    if (timeparts.length != 2){
                        return false;
                    }
                    String[] PCparts = parts[1].split("-");
                    if (PCparts.length != 2){
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
