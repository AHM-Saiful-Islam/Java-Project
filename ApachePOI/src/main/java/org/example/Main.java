package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        System.out.println("Apache POI!");
        // create PoiExcel class object
        PoiExcel poiExcel1 = new PoiExcel();
        // call createExcel method to create an Excel file and create a sheet named Sheet1
        poiExcel1.createExcel("Sheet1");
        // create PoiWord class object
        PoiWord poiWord1 = new PoiWord();
        // create a Word file by calling createWord method
        poiWord1.createWordDoc1();
        // create word file with 3 paragraph
        poiWord1.createWordDoc2();
        // create word file with 4 paragraph
        poiWord1.createWordDoc3();
        // create doc file with header and footer
        poiWord1.headerAndFooter();
        // create doc and add image
        poiWord1.adImage();

    }
}