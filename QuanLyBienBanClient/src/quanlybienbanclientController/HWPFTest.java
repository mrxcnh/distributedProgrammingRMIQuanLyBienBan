/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientController;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Created by Long
 * Date: 11/21/2018
 * Time: 2:12 PM
 */
public class HWPFTest {
    public static HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText) {
        Range r = doc.getRange();
        for (int i = 0; i < r.numSections(); ++i) {
            Section s = r.getSection(i);
            for (int j = 0; j < s.numParagraphs(); j++) {
                Paragraph p = s.getParagraph(j);
                for (int k = 0; k < p.numCharacterRuns(); k++) {
                    CharacterRun run = p.getCharacterRun(k);
                    String text = run.text();
//                    System.out.println(text);
                    if (text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
        return doc;
    }

    public static HWPFDocument openDocument(String filePath) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
            return new HWPFDocument(fs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean saveDocument(String filePath, HWPFDocument doc) {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            doc.write(out);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
