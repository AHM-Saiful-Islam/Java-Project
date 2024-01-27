package org.example;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PoiExcel {
    static void createExcel(String sheetName) throws IOException {
        // create file path
        String filePath = "D:\\Software\\Project presentation\\ApachePOI\\output\\mypoi.xlsx";
        // create myFile obj of File class in reference file path
        File myFile = new File(filePath);
        // create excel workbook obj with Workbook class. XSSF for Excel
        Workbook workbook1 = new XSSFWorkbook();
        // create sheet1 obj; type Sheet; give a name of sheet
        Sheet sheet1 =  workbook1.createSheet(sheetName); // write sheet name at method call
        // create file output stream
        FileOutputStream fileOut = new FileOutputStream(myFile);
        //
        workbook1.write(fileOut);
        fileOut.close();
        System.out.println("File created!");
    }
}