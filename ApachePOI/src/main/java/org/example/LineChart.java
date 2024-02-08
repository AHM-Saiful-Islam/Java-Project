package org.example;

import java.io.FileOutputStream;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.*;

public class LineChart {

    public static void main(String[] args) {
        try {
            XWPFDocument doc = new XWPFDocument();

            // Create some normal XWPF content
            XWPFParagraph paragraph = doc.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = paragraph.createRun();
            run.setText("Line Chart Example:");

            // Create the chart
            XWPFChart lineChart = doc.createChart(15 * Units.EMU_PER_CENTIMETER, 8 * Units.EMU_PER_CENTIMETER);

            // Create chart data sources
            String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
            Double[] values = {10.0, 15.0, 7.0, 22.0, 18.0};
            XDDFDataSource<String> categoryDataSource = XDDFDataSourcesFactory.fromArray(categories);
            XDDFNumericalDataSource<Double> valueDataSource = XDDFDataSourcesFactory.fromArray(values);

            // Create the category axis
            XDDFCategoryAxis categoryAxis = lineChart.createCategoryAxis(AxisPosition.BOTTOM);

            // Create the value axis
            XDDFValueAxis valueAxis = lineChart.createValueAxis(AxisPosition.LEFT);
            valueAxis.setCrosses(AxisCrosses.AUTO_ZERO);

            // Set AxisCrossBetween
            valueAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

            XDDFChartData data = lineChart.createData(ChartTypes.LINE, categoryAxis, valueAxis);

            // Add series
            XDDFChartData.Series series = data.addSeries(categoryDataSource, valueDataSource);
            series.setTitle("Series 1", null);

            // Set series fill color
            solidFillSeries(series, PresetColor.RED);

            // Plot the chart
            lineChart.plot(data);
            lineChart.setTitleText("Line Chart");

            // Save the document
            try (FileOutputStream out = new FileOutputStream("D:\\Software\\Project presentation\\ApachePOI\\output\\line_chart_document.docx")) {
                doc.write(out);
            }

            System.out.println("Line chart created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void solidFillSeries(XDDFChartData.Series series, PresetColor color) {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(color));
        XDDFShapeProperties properties = series.getShapeProperties();
        if (properties == null) {
            properties = new XDDFShapeProperties();
        }
        properties.setFillProperties(fill);
        series.setShapeProperties(properties);
    }
}
