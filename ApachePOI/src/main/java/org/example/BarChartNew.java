package org.example;

import java.io.FileOutputStream;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.*;

import static org.example.BarChartWord.solidFillSeries;

public class BarChartNew {

    public static void main(String[] args) throws Exception {
        XWPFDocument doc = new XWPFDocument();

        createFirstPage(doc);
        createSecondPage(doc);
        createChartOnThirdPage(doc);

        // Save the document
        FileOutputStream out = new FileOutputStream("D:\\Software\\Project presentation\\ApachePOI\\output\\bar_chart_document2.docx");
        doc.write(out);
        out.close();
        doc.close();

        System.out.println("Document with chart created successfully!");
    }

    private static void createFirstPage(XWPFDocument doc) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setPageBreak(true);
        XWPFRun run = paragraph.createRun();
        run.setText("Content for the First Page");
        run.addCarriageReturn();  // Move to the next line for separation
    }

    private static void createSecondPage(XWPFDocument doc) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setPageBreak(true);
        XWPFRun run = paragraph.createRun();
        run.setText("Content for the Second Page");
        run.addCarriageReturn();  // Move to the next line for separation
    }

    static XWPFParagraph createChartOnThirdPage(XWPFDocument doc) throws Exception {
        // Create the chart on the third page
        XWPFParagraph paragraph = doc.createParagraph();
        //paragraph.setPageBreak(true);  // Page break to start a new page for the chart

        // Create the chart
        XWPFChart categoryChart = doc.createChart(5 * Units.EMU_PER_CENTIMETER, 3 * Units.EMU_PER_CENTIMETER);

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

        // Save the chart data on the third page
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
        return null;
    }
}
