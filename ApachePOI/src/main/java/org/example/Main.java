package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Apache POI!");
        // create PoiExcel class object
        PoiExcel poiExcel1 = new PoiExcel();
        // call createExcel method to create an Excel file and create a sheet named Sheet1
        poiExcel1.createExcel("Sheet1");
        //create Sheet2
    }
}