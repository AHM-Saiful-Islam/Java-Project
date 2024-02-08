package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.*;

import static org.example.BarChartWord.solidFillSeries;

public class AddChartToExistingDocument {

    public static void main(String[] args) {
        try {
            String existingFilePath = "D:\\Software\\Project presentation\\ApachePOI\\output\\bar_chart_document2.docx";
            int targetPage = 4; // Change this to the desired page number

            XWPFDocument existingDoc = new XWPFDocument(new FileInputStream(existingFilePath));

            // Estimate the number of pages
            int estimatedPages = countPages(existingDoc);

            if (targetPage <= estimatedPages) {
                // Navigate to the x-th page (page numbering starts from 0)
                for (int i = 0; i < targetPage; i++) {
                    existingDoc.createParagraph(); // Create a blank paragraph to move to the next page
                }

                // Create the chart on the x-th page
                createChartOnPage(existingDoc);

                // Save the modified document
                String modifiedFilePath = "D:\\Software\\Project presentation\\ApachePOI\\output\\chart4.docx";
                try (FileOutputStream out = new FileOutputStream(modifiedFilePath)) {
                    existingDoc.write(out);
                }

                System.out.println("Chart added successfully.");
            } else {
                System.err.println("Invalid target page number. The document has fewer pages.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int countPages(XWPFDocument doc) {
        int pageCount = 0;

        for (XWPFParagraph paragraph : doc.getParagraphs()) {
            // Check if the paragraph has a page break
            if (paragraph.isPageBreak()) {
                pageCount++;
            }
        }

        return pageCount;
    }


    private static void createChartOnPage(XWPFDocument doc) throws Exception {
        // Create the chart on the current page
        XWPFParagraph paragraph = doc.createParagraph();

        // Create the chart
        XWPFChart categoryChart = doc.createChart(15 * Units.EMU_PER_CENTIMETER, 8 * Units.EMU_PER_CENTIMETER);

        // Create chart data sources
        String[] categories = {"Critical", "High", "Medium","Low","Best Practice"};
        Double[] values = {(double) 1,(double) 2,(double) 3,(double) 4,(double) 5};
        XDDFDataSource<String> categoryDataSource = XDDFDataSourcesFactory.fromArray(categories);
        XDDFNumericalDataSource<Double> valueDataSource = XDDFDataSourcesFactory.fromArray(values);

        // Create the category axis
        XDDFCategoryAxis categoryAxis = categoryChart.createCategoryAxis(AxisPosition.BOTTOM);

        XDDFValueAxis valueAxis = categoryChart.createValueAxis(AxisPosition.LEFT);

        // Set how the axis cross - for example at zero point of value axis
        valueAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        // Set AxisCrossBetween, so the value axis crosses the category axis between the categories.
        // Else first and last category is exactly on cross points and the bars are only half visible.
        valueAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

        // Save the chart data on the current page
        XDDFChartData data = categoryChart.createData(ChartTypes.BAR, categoryAxis, valueAxis);
        ((XDDFBarChartData) data).setBarDirection(BarDirection.COL);

        // Set chart title
        categoryChart.setTitleText("Report December 2023");

        // Add series
        XDDFChartData.Series series = data.addSeries(categoryDataSource, valueDataSource);

        // Set series title - not optional
        series.setTitle("Report", null);

        // Set series fill color - necessary for LibreOffice as this will not use default colors
        solidFillSeries(series, PresetColor.BLUE);

        // Plot the chart
        categoryChart.plot(data);

        // Move to the next line for separation
        XWPFRun run = paragraph.createRun();
        run.addCarriageReturn();
    }
}
