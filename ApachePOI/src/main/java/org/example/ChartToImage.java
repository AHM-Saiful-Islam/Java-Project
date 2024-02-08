package org.example;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.*;

public class ChartToImage {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        XWPFDocument doc = new XWPFDocument();

        // Create the chart
        XWPFChart chart = doc.createChart(15 * Units.EMU_PER_CENTIMETER, 8 * Units.EMU_PER_CENTIMETER);

        // Create chart data sources
        String[] categories = {"Category 1", "Category 2", "Category 3"};
        Double[] values = {10.0, 20.0, 15.0};
        XDDFDataSource<String> categoryDataSource = XDDFDataSourcesFactory.fromArray(categories);
        XDDFNumericalDataSource<Double> valueDataSource = XDDFDataSourcesFactory.fromArray(values);

        // Create the category axis
        XDDFCategoryAxis categoryAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);

        // Create the value axis
        XDDFValueAxis valueAxis = chart.createValueAxis(AxisPosition.LEFT);
        valueAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        valueAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

        // Set chart title
        chart.setTitleText("Chart to Image");

        // Add series
        XDDFChartData data = chart.createData(ChartTypes.BAR, categoryAxis, valueAxis);
        ((XDDFBarChartData) data).setBarDirection(BarDirection.COL);
        XDDFChartData.Series series = data.addSeries(categoryDataSource, valueDataSource);
        series.setTitle("Series 1", null);

        // Set series fill color
        solidFillSeries(series, PresetColor.BLUE);

        // Plot the chart
        chart.plot(data);

        // Save the chart as an image
        saveChartAsImage(chart, "D:\\Software\\Project presentation\\ApachePOI\\output\\chart_image.jpeg");

        // Save the document
        try (FileOutputStream out = new FileOutputStream("D:\\Software\\Project presentation\\ApachePOI\\output\\chart_to_image.docx")) {
            doc.write(out);
        }

        System.out.println("Chart created and saved as an image successfully!");
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

    private static void saveChartAsImage(XWPFChart chart, String imagePath) throws IOException {
        try (FileOutputStream out = new FileOutputStream(imagePath)) {
            chart.getPackagePart().getPackage().save(out);
        }
    }
}

