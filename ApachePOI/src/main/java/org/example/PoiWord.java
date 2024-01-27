/*
Use try-with-resource
The XWPFDocument object is a resource that implements the AutoCloseable interface.
By using try-with-resource declaration (XWPFDocument doc = new XWPFDocument()),
you ensure that the XWPFDocument will be closed automatically when the try block is exited.
try-with-resources is especially helps prevent resource leaks by automatically
closing the file stream (FileOutputStream here) when it is no longer needed.
 */

package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

public class PoiWord {
    // create docx file with 1 paragraph
    public static void createWordDoc1() throws IOException {

        String fileName = "D:\\Software\\Project presentation\\ApachePOI\\output\\report1.docx";

        try (XWPFDocument doc = new XWPFDocument()) {

            // create a paragraph
            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);

            // set properties
            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setItalic(true);
            r1.setFontSize(22);
            r1.setFontFamily("New Roman");
            r1.setText("I am first paragraph.");

            // create header
            XWPFHeader header = doc.createHeader(HeaderFooterType.DEFAULT);
            header.createParagraph()
                    .createRun()
                    .setText("This is a header");
            // create  footer
            XWPFFooter footer = doc.createFooter(HeaderFooterType.DEFAULT);
            footer.createParagraph()
                    .createRun()
                    .setText("This is a footer");

            // save it to .docx file
            try (FileOutputStream out = new FileOutputStream(fileName)) {
                doc.write(out);
            }
        }
        System.out.println("Report 1 is created");
    }

    // create a simple docx file with 3 paragraph
    static void createWordDoc2() throws IOException {
        // path + file name
        String fileName = "D:\\Software\\Project presentation\\ApachePOI\\output\\Report2.docx";
        XWPFDocument doc = new XWPFDocument();  // create doc

        // create paragraph, create run, se properties
        XWPFParagraph p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r1 = p1.createRun();
        r1.setText("This is first Paragraph.");
        r1.setFontSize(28);
        r1.setFontFamily("New Roman");

        // create paragraph, create run, se properties
        XWPFParagraph p2 = doc.createParagraph();
        p2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2 = p2.createRun();
        r2.setText("This is second paragraph");
        r2.setFontSize(24);
        r2.setFontFamily("Roboto");
        r2.setBold(true);

        // create paragraph, create run, se properties
        XWPFParagraph p3 = doc.createParagraph();
        p3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r3 = p3.createRun();
        r3.setText("This is third paragraph");
        r3.setFontSize(20);
        r3.setFontFamily("New Roman");

        // create header
        XWPFHeader header = doc.createHeader(HeaderFooterType.DEFAULT);
        header.createParagraph()
                .createRun()
                .setText("This is a header");
        // create a footer
        XWPFFooter footer = doc.createFooter(HeaderFooterType.DEFAULT);
        footer.createParagraph()
                .createRun()
                .setText("This is a footer");

        // save all in previously created file
        FileOutputStream fileOut = new FileOutputStream(fileName);
        doc.write(fileOut); // writing inside file
        fileOut.close(); // closing file. The XWPFDocument object is a resource. It should be closed after using.
        // print confirmation
        System.out.println("Report 2 is created");
    }

    // create new file with 4 paragraph
    static void createWordDoc3() throws IOException {

        try (XWPFDocument doc = new XWPFDocument()) {
            // file path declared in FileOutputStream at last of method
            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);

            // Set Text to Bold and font size to 22 for first paragraph
            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setItalic(true);
            r1.setFontSize(22);
            r1.setText("I am first paragraph. My Text is bold, italic, Courier and capitalized");
            r1.setFontFamily("Courier");

            XWPFParagraph p2 = doc.createParagraph();
            //Set color for second paragraph
            XWPFRun r2 = p2.createRun();
            r2.setText("I am second paragraph. My Text is Red in color and is embossed");
            r2.setColor("ff0000");
            r2.setEmbossed(true);

            XWPFParagraph p3 = doc.createParagraph();
            //Set strike for third paragraph and capitalization
            XWPFRun r3 = p3.createRun();
            r3.setStrikeThrough(true);
            r3.setCapitalized(true);
            r3.setText("I am third paragraph. My Text is strike through and is capitalized");

            XWPFParagraph p4 = doc.createParagraph();
            p4.setWordWrapped(true);
            p4.setPageBreak(true);  // write in new page
            p4.setIndentationFirstLine(600);

            XWPFRun r4 = p4.createRun();
            r4.setFontSize(40);
            r4.setItalic(true);
            //r4.setTextPosition(100);
            r4.setText("Line 1");
            r4.addBreak();
            r4.setText("Line 2");
            r4.addBreak();
            r4.setText("Line 3");

            // document header
            XWPFHeader header = doc.createHeader(HeaderFooterType.DEFAULT);
            header.createParagraph()
                    .createRun()
                    .setText("This is a header");
            // document footer
            XWPFFooter footer = doc.createFooter(HeaderFooterType.DEFAULT);
            footer.createParagraph()
                    .createRun()
                    .setText("This is a footer");
            // save the docs
            try (FileOutputStream out = new FileOutputStream("D:\\Software\\Project presentation\\ApachePOI\\output\\report3.docx")) {
                doc.write(out);
            }
        }
        System.out.println("Report 3 is created");
    }

    // document header and footer
    static void headerAndFooter() throws IOException {

        try (XWPFDocument doc = new XWPFDocument()) {

            XWPFParagraph p = doc.createParagraph();
            XWPFRun r = p.createRun();
            r.setBold(true);
            r.setFontSize(30);
            r.setText("Create document header and footer!");

            // next page
            XWPFParagraph p2 = doc.createParagraph();
            p2.setWordWrapped(true); // new line
            p2.setPageBreak(true);  // new page

            XWPFRun r2 = p2.createRun();
            r2.setFontSize(40);
            r2.setItalic(true);
            r2.setText("New Page");

            // document header and footer
            XWPFHeader head = doc.createHeader(HeaderFooterType.DEFAULT);
            head.createParagraph()
                    .createRun()
                    .setText("This is document header");

            XWPFFooter foot = doc.createFooter(HeaderFooterType.DEFAULT);
            foot.createParagraph()
                    .createRun()
                    .setText("This is document footer");

            try (OutputStream os = new FileOutputStream(new File("D:\\Software\\Project presentation\\ApachePOI\\output\\header1.docx"))) {
                doc.write(os);
            }
        }
        System.out.println("Header & Footer are created");
    }

    // add image with description
    static void adImage() throws IOException, InvalidFormatException {

        String imageFile = "D:\\Software\\Project presentation\\ApachePOI\\src\\test\\nature.jpeg";

        try (XWPFDocument doc = new XWPFDocument()) {

            XWPFParagraph p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r = p.createRun();
            r.setText("Nature Image");
            r.setBold(true);
            r.addBreak();
            r.addBreak();

            // add jpeg image
            try (FileInputStream is = new FileInputStream(imageFile)) {
                r.addPicture(is,
                        Document.PICTURE_TYPE_JPEG,    // jpeg file
                        imageFile,                     // image file path
                        Units.toEMU(400),
                        Units.toEMU(200));       // 400x200 pixels
                r.addBreak();
                r.addBreak();
            }
            // write image description
            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.BOTH);
            p1.setWordWrapped(true);  //  long lines of text should be automatically wrapped onto the next line within the paragraph
            XWPFRun r1 = p1.createRun();
            r1.setText("Nestled between the rolling hills and the vast expanse of the sea, a picturesque landscape unfolds under the vast " +
                    "canvas of the sky. Majestic hills, cloaked in hues of green and brown, form undulating waves that create a breathtaking " +
                    "backdrop. The sea, stretching to the horizon, mirrors the serene blue of the sky above, meeting the land with a gentle " +
                    "ebb and flow.\n" + "\n" + "Clusters of trees, standing tall and proud, populate the landscape, their foliage swaying " +
                    "in harmony with the whispers of the wind. The leaves create a mosaic of colors, from rich greens to earthy browns, " +
                    "adding depth and texture to the scene. A sense of tranquility pervades as the natural symphony of rustling leaves " +
                    "and distant waves creates a soothing melody.\n" + "\n" + "Above, the sky is a masterpiece of hues—soft blues, " +
                    "wisps of white clouds, and perhaps a hint of the golden glow from the setting or rising sun. The landscape beneath " +
                    "is embraced by the open sky, a vast canvas that frames the beauty of the hills, sea, and trees. This tableau of " +
                    "nature invites contemplation, each element contributing to the harmony of a scene that feels both timeless and " +
                    "alive with the energy of the natural world.");
            r1.setFontFamily("New Roman");
            r1.setFontSize(14);

            // save all
            try (FileOutputStream out = new FileOutputStream("D:\\Software\\Project presentation\\ApachePOI\\output\\images.docx")) {
                doc.write(out);
            }
        }
        System.out.println("Report with image is created");
    }

    //
}
