package org.example;

import java.io.FileOutputStream;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.*;

public class BarChartWord {

    static void barChart() throws Exception {
        XWPFDocument doc = new XWPFDocument();

        // Create document content
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText("Bar Chart Example");

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

        XDDFChartData data = categoryChart.createData(ChartTypes.BAR,  categoryAxis, valueAxis);
        // Set bar direction
        ((XDDFBarChartData)data).setBarDirection(BarDirection.COL);

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

        // Save the document
        FileOutputStream out = new FileOutputStream("D:\\Software\\Project presentation\\ApachePOI\\output\\bar_chart_document.docx");
        doc.write(out);
        out.close();
        doc.close();

        System.out.println("chart created successfully!");
    }

    static void solidFillSeries(XDDFChartData.Series series, PresetColor color) {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(color));
        XDDFShapeProperties properties = series.getShapeProperties();
        if (properties == null) {
            properties = new XDDFShapeProperties();
        }
        properties.setFillProperties(fill);
        series.setShapeProperties(properties);
    }
}