package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.*;

public class ChartInsideTableCell {
    public static void main(String[] args) throws Exception {
        XWPFDocument doc = new XWPFDocument();

        // Create a table with one row and one cell
        XWPFTable table = doc.createTable(2, 2);

        //Creating first Row
        XWPFTableRow row1 = table.getRow(0);
        row1.getCell(0).setText("Bar Chart 1");
        row1.getCell(1).setText("Bar Chart 2");

        // Get the cell
        XWPFTableCell cell1 = table.getRow(1).getCell(0);
        XWPFTableCell cell2 = table.getRow(1).getCell(1);
//        cell1.setText("bar chart");
//        cell2.setText("bar chart");

        XWPFRun r1 = cell1.addParagraph().createRun();
        XWPFRun r2 = cell2.addParagraph().createRun();

        String imageFile = "D:\\Software\\Project presentation\\ApachePOI\\src\\test\\nature.jpeg";
        String imageFile2 = "D:\\Software\\Project presentation\\ApachePOI\\output\\chart_image.jpeg";
        // add jpeg image to cell 1
        try (FileInputStream is = new FileInputStream(imageFile)) {
            r1.addPicture(is,
                    Document.PICTURE_TYPE_JPEG,    // jpeg file
                    imageFile,                     // image file path
                    Units.toEMU(200),
                    Units.toEMU(100));       // 400x200 pixels
        }
        // add jpeg image to cell 2
        try (FileInputStream is = new FileInputStream(imageFile2)) {
            r2.addPicture(is,
                    Document.PICTURE_TYPE_JPEG,    // jpeg file
                    imageFile,                     // image file path
                    Units.toEMU(200),
                    Units.toEMU(100));       // 400x200 pixels
        }

        // Create the chart inside the cell
//        createChartInsideCell(doc, cell1);
//        createChartInsideCell(doc, cell2);

        // Save the document
        try (FileOutputStream out = new FileOutputStream("D:\\Software\\Project presentation\\ApachePOI\\output\\chart_inside_cell.docx")) {
            doc.write(out);
        }

        System.out.println("Chart inside cell created successfully!");
    }

    private static void createChartInsideCell(XWPFDocument doc, XWPFTableCell cell) throws IOException, InvalidFormatException {
        // Create the chart
        XWPFChart chart = doc.createChart(8 * Units.EMU_PER_CENTIMETER, 5 * Units.EMU_PER_CENTIMETER);

        // Create chart data sources
        String[] categories = {"Category 1", "Category 2", "Category 3"};
        Double[] values = {5.0, 8.0, 10.0};
        XDDFDataSource<String> categoryDataSource = XDDFDataSourcesFactory.fromArray(categories);
        XDDFNumericalDataSource<Double> valueDataSource = XDDFDataSourcesFactory.fromArray(values);

        // Create the category axis
        XDDFCategoryAxis categoryAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);

        // Create the value axis
        XDDFValueAxis valueAxis = chart.createValueAxis(AxisPosition.LEFT);
        valueAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        valueAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

        // Set chart title
        chart.setTitleText("Chart Inside Cell");

        // Add series
        XDDFChartData data = chart.createData(ChartTypes.BAR, categoryAxis, valueAxis);
        ((XDDFBarChartData) data).setBarDirection(BarDirection.COL);
        XDDFChartData.Series series = data.addSeries(categoryDataSource, valueDataSource);
        series.setTitle("Series 1", null);

        // Set series fill color
        solidFillSeries(series, PresetColor.BLUE);

        // Plot the chart
        chart.plot(data);

        // Add the chart to the cell
        // cell.removeParagraph(0);
        XWPFParagraph paragraph = cell.addParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("yes");
        run.addChart(String.valueOf(chart));
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