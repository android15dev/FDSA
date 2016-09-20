package com.nkdroidsolutions.firedefence.storage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nkdroidsolutions.firedefence.web_api.UrlToBitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Sahil on 17-09-2016.
 */
public class Form1PDF implements AllObserver {

    private Activity context;

    public Form1PDF(Activity context) {
        this.context = context;
    }

    Font font_heading = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
    Font font_normal = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.NORMAL);
    Font font_title = new Font(Font.FontFamily.TIMES_ROMAN, 50, Font.BOLD);

    private String file;

    public void createForm() throws DocumentException, IOException {
        ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Creating PDF...");
        pDialog.setCancelable(false);
        pDialog.show();

        if (Build.VERSION.SDK_INT > 20) {
            String state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {
                file = Environment.getExternalStorageDirectory() + File.separator + "FDS_FORM_" + System.currentTimeMillis() + ".pdf";
            } else {
                file = context.getCacheDir() + File.separator + "FDS_FORM_" + System.currentTimeMillis() + ".pdf";
            }

        } else {
            file = Environment.getExternalStorageDirectory() + File.separator + "FDS_FORM_" + System.currentTimeMillis() + ".pdf";

        }
        Document document = new Document(PageSize.A1);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();

        //////// Add Logo  /////////

        PdfPTable table = new PdfPTable(2);
        //       table.setWidthPercentage(20);
        //      table.setSpacingBefore(10);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        float[] columnWidths = new float[]{1f, 5f};
        table.setWidths(columnWidths);
        table.addCell(createLogoCell());
        PdfPCell c1 = new PdfPCell(new Phrase("Engineers Report", font_title));
        c1.setBorderColor(BaseColor.WHITE);
        c1.setPadding(70f);
        //Paragraph para = new Paragraph(getString(R.string.app_name), font_title);
        table.addCell(c1);


        Chapter chapter = new Chapter("", 0);
        chapter.setNumberDepth(0);
        Section section = chapter.addSection("");
        section.setNumberDepth(0);
        section.add(table);

        //////////////////////////One///////////////////////////

        section.add(addHeading("Page 1"));

        PdfPTable table2 = new PdfPTable(2);
        table2.setWidthPercentage(100);
        float[] columnWidths1 = new float[]{1f, 2f};
        table2.setWidths(columnWidths1);
        table2.setHorizontalAlignment(Element.ALIGN_LEFT);

        table2.addCell(addCell("Job Refrence No."));
        table2.addCell(addCell(observerFormOne.getFormone().getResponse().getReport1().getJobNo()));
        table2.addCell(addCell("Date"));
        table2.addCell(addCell(observerFormOne.getFormone().getResponse().getReport1().getDate()));
        table2.addCell(addCell("Arrived"));
        table2.addCell(addCell(observerFormOne.getFormone().getResponse().getReport1().getArrived()));
        table2.addCell(addCell("Client Name / Site"));
        table2.addCell(addCell(observerFormOne.getFormone().getResponse().getReport1().getClientName()));
        table2.addCell(addCell("Notes"));
        table2.addCell(addCell(observerFormOne.getFormone().getResponse().getReport1().getNotes()));

        section.add(table2);
//////////////////////////---------///////////////////////////


//////////////////////////Two///////////////////////////

        section.add(addHeading("Page 2"));

        PdfPTable table4 = new PdfPTable(2);
        table4.setWidthPercentage(100);
        float[] columnWidths2 = new float[]{1f, 2f};
        table4.setWidths(columnWidths2);
        table4.setHorizontalAlignment(Element.ALIGN_LEFT);

        table4.addCell(addCell("Date"));
        table4.addCell(addCell(observerFormOne.getFormone().getResponse().getReport2().getDate()));
        table4.addCell(addCell("Authorised Signature Name"));
        table4.addCell(addCell(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedSignName()));
        table4.addCell(addCell("Authorised Signature"));
        //  table4.addCell(getImageCell(observerFormOne.getFormone().getResponse().getReport2().getSignBitmap()));
        if (observerFormOne.getFormone().getResponse().getReport2().getSignBitmap() != null)
            table4.addCell(getImageCell(observerFormOne.getFormone().getResponse().getReport2().getSignBitmap()));
        else
            table4.addCell("");
        table4.addCell(addCell("Client Unavailable to Sign"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedBy(), "1", table4);
        table4.addCell(addCell("For FDS only"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedBy(), "2", table4);

        section.add(table4);
//////////////////////////-----///////////////////////////

//////////////////////////Three///////////////////////////

        section.add(addHeading("Page 3"));

        section.add(addSubHeading("Type of System"));

        PdfPTable table6 = new PdfPTable(2);
        table6.setWidthPercentage(100);
        table6.setWidths(columnWidths2);
        table6.setHorizontalAlignment(Element.ALIGN_LEFT);


        table6.addCell(addCell("Water"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport3().getTypesOfSystems(), "1", table6);
        table6.addCell(addCell("Alarm"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport3().getTypesOfSystems(), "2", table6);
        table6.addCell(addCell("Gaseous"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport3().getTypesOfSystems(), "3", table6);
        table6.addCell(addCell("Halon"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport3().getTypesOfSystems(), "4", table6);

        table6.addCell(addCell("Other"));
        table6.addCell(addCell(observerFormOne.getFormone().getResponse().getReport3().getOtherSystemType()));
        section.add(table6);

        section.add(addSubHeading("Reason For Visit"));

        PdfPTable table7 = new PdfPTable(2);
        table7.setWidthPercentage(100);
        table7.setWidths(columnWidths2);
        table7.setHorizontalAlignment(Element.ALIGN_LEFT);


        table7.addCell(addCell("Installation"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit(), "1", table7);
        table7.addCell(addCell("Service"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit(), "2", table7);
        table7.addCell(addCell("Emergency Call"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit(), "3", table7);
        table7.addCell(addCell("Site Instruction"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit(), "4", table7);
        table7.addCell(addCell("Pressure Test"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit(), "5", table7);

        table7.addCell(addCell("Other"));
        table7.addCell(addCell(observerFormOne.getFormone().getResponse().getReport3().getOtherResonToVisit()));
        section.add(table7);

        //////////////////////////-----///////////////////////////
        document.add(section);

        document.newPage();

        Chapter chapter1 = new Chapter("", 0);
        chapter1.setNumberDepth(0);
        Section section1 = chapter1.addSection("");
        section1.setNumberDepth(0);

        //////////////////////////Four///////////////////////////

        section1.add(addHeading("Page 4"));
        section1.add(addSubHeading("System state on arrival"));

        PdfPTable table8 = new PdfPTable(2);
        table8.setWidthPercentage(100);
        table8.setWidths(columnWidths2);
        table8.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8.addCell(addCell("Note"));
        table8.addCell(addCell(observerFormOne.getFormone().getResponse().getReport4().getSysStateOnArrival()));
        section1.add(table8);

        section1.add(addSubHeading("Pressure Test"));

        PdfPTable table9 = new PdfPTable(2);
        table9.setWidthPercentage(100);
        table9.setWidths(columnWidths2);
        table9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table9.addCell(addCell("Set No"));
        table9.addCell(addCell(observerFormOne.getFormone().getResponse().getReport4().getSetNo()));
        table9.addCell(addCell("Tested as per instructions below"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport4().getPressureTest(), "1", table9);
        table9.addCell(addCell("Air test(2.5 bar for 24 hours)"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport4().getPressureTest(), "2", table9);
        table9.addCell(addCell("Wet test(15 bar for 2 hours)"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport4().getPressureTest(), "3", table9);
        table9.addCell(addCell("Standing pressure"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport4().getPressureTest(), "4", table9);
        table9.addCell(addCell("Photo Gage Before"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport4().getPressureTest(), "5", table9);
        table9.addCell(addCell("Photo Gage Upon Completetion"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport4().getPressureTest(), "6", table9);
        table9.addCell(addCell("Bar"));
        table9.addCell(addCell(observerFormOne.getFormone().getResponse().getReport4().getBar1()));
        table9.addCell(addCell("reason for special test: client instruction extension to old system"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport4().getPtRes(), "1", table9);
        table9.addCell(addCell("Special test"));
        addCheckBoxCell(observerFormOne.getFormone().getResponse().getReport4().getPtRes(), "2", table9);
        table9.addCell(addCell("Bar"));
        table9.addCell(addCell(observerFormOne.getFormone().getResponse().getReport4().getBar2()));
        table9.addCell(addCell("Hours"));
        table9.addCell(addCell(observerFormOne.getFormone().getResponse().getReport4().getHours()));
        table9.addCell(addCell("Notes"));
        table9.addCell(addCell(observerFormOne.getFormone().getResponse().getReport4().getNotes()));

        section1.add(table9);

        //////////////////////////------///////////////////////////

        //////////////////////////Five///////////////////////////

        section1.add(addHeading("Page 5"));
        section1.add(addSubHeading("Engineer's comments/ recommendations"));

        PdfPTable table10 = new PdfPTable(2);
        table10.setWidthPercentage(100);
        table10.setWidths(columnWidths2);
        table10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(addCell("Comments"));
        table10.addCell(addCell(observerFormOne.getFormone().getResponse().getReport5().getEngineerNote()));
        section1.add(table10);

        PdfPTable table12 = new PdfPTable(3);
        table12.setSpacingBefore(10f);
        table12.setWidthPercentage(100);
        table12.setHorizontalAlignment(Element.ALIGN_LEFT);
        if (observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImageBitmap1() != null)
            table12.addCell(getImageCell(observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImageBitmap1()));
        else
            table12.addCell("");
        if (observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImageBitmap2() != null)
            table12.addCell(getImageCell(observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImageBitmap2()));
        else
            table12.addCell("");
        if (observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImageBitmap3() != null)
            table12.addCell(getImageCell(observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImageBitmap3()));
        else
            table12.addCell("");

        section1.add(table12);

        section1.add(addSubHeading("Parts Used"));

        PdfPTable table11 = new PdfPTable(2);
        table11.setWidthPercentage(100);
        table11.setWidths(columnWidths2);
        table11.setHorizontalAlignment(Element.ALIGN_LEFT);
        table11.addCell(addCell("Note"));
        table11.addCell(addCell(observerFormOne.getFormone().getResponse().getReport5().getPartsUsed()));
        section1.add(table11);

        /////////////////////-----------/////////////////////

        //////////////////////////Six///////////////////////////

        section1.add(addHeading("Page 6"));
        section1.add(addSubHeading("Completion of Work"));

        PdfPTable table13 = new PdfPTable(2);
        table13.setWidthPercentage(100);
        table13.setWidths(columnWidths2);
        table13.setHorizontalAlignment(Element.ALIGN_LEFT);
        table13.addCell(addCell("Date"));
        table13.addCell(addCell(observerFormOne.getFormone().getResponse().getReport6().getDate()));
        table13.addCell(addCell("Time"));
        table13.addCell(addCell(observerFormOne.getFormone().getResponse().getReport6().getTime()));
        table13.addCell(addCell("Client Name"));
        table13.addCell(addCell(observerFormOne.getFormone().getResponse().getReport6().getClientName()));
        table13.addCell(addCell("Work Completed"));
        table13.addCell(addCell(observerFormOne.getFormone().getResponse().getReport6().getWorkCompleted()));
        table13.addCell(addCell("Back Online"));
        table13.addCell(addCell(observerFormOne.getFormone().getResponse().getReport6().getBackOnline()));
        table13.addCell(addCell("Client Signature"));
        if (observerFormOne.getFormone().getResponse().getReport6().getClientBitmap() != null)
            table13.addCell(getImageCell(observerFormOne.getFormone().getResponse().getReport6().getClientBitmap()));
        else
            table13.addCell("");
        table13.addCell(addCell("Engineer Signature"));
        if (observerFormOne.getFormone().getResponse().getReport6().getEngineerBitmap() != null) {
            table13.addCell(getImageCell(observerFormOne.getFormone().getResponse().getReport6().getEngineerBitmap()));
        } else {
            table13.addCell("");
        }
        //  table13.addCell(getImageCell(observerFormOne.getFormone().getResponse().getReport6().getClientBitmap()));
        //   table13.addCell(getImageCell(observerFormOne.getFormone().getResponse().getReport6().getEngineerBitmap()));
        table13.addCell(addCell("Notes"));
        table13.addCell(addCell(observerFormOne.getFormone().getResponse().getReport6().getNotes()));
        section1.add(table13);

        /////////////////////-----------/////////////////////

        //////////////////////////Seven///////////////////////////

        section1.add(addHeading("Page 7"));

        PdfPTable table14 = new PdfPTable(2);
        table14.setWidthPercentage(100);
        table14.setWidths(columnWidths2);
        table14.setHorizontalAlignment(Element.ALIGN_LEFT);
        table14.addCell(addCell("Time Completed"));
        table14.addCell(addCell(observerFormOne.getFormone().getResponse().getReport7().getTimeCompleted()));
        table14.addCell(addCell("Travel Time"));
        table14.addCell(addCell(observerFormOne.getFormone().getResponse().getReport7().getTraveltime()));
        table14.addCell(addCell("Mileage"));
        table14.addCell(addCell(observerFormOne.getFormone().getResponse().getReport7().getMileage()));
        table14.addCell(addCell("Work Completed"));
        table14.addCell(addCell(observerFormOne.getFormone().getResponse().getReport7().getWorkCompletedLast()));
        table14.addCell(addCell("Email Address"));
        table14.addCell(addCell(observerFormOne.getFormone().getResponse().getReport7().getEmailAddress()));
        section1.add(table14);

        /////////////////////-----------/////////////////////


        document.add(section1);
        document.close();

        pDialog.dismiss();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"servicing@fire-defence.com",observerFormOne.getFormone().getResponse().getReport7().getEmailAddress()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "FDS Engineers Report");
        //intent.putExtra(Intent.EXTRA_TEXT, "body text");

        Uri uri = Uri.parse("file://" + file);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, "Send email"));

    }

    private PdfPTable addHeading(String name) {
        PdfPTable table5 = new PdfPTable(1);
        table5.setSpacingBefore(20f);
        table5.setWidthPercentage(100);
        table5.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell c4 = new PdfPCell(new Phrase(name, font_title));
        c4.setBorderColor(BaseColor.DARK_GRAY);
        c4.setPadding(15f);
        c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table5.addCell(c4);
        return table5;
    }

    private PdfPTable addSubHeading(String name) {
        PdfPTable tabl7 = new PdfPTable(1);
        tabl7.setSpacingBefore(10f);
        tabl7.setWidthPercentage(100);
        tabl7.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabl7.addCell(addCell(name));
        return tabl7;
    }

    private PdfPCell addCell(String name) {
        PdfPCell cell1 = new PdfPCell(new Phrase(name, font_heading));
        cell1.setPadding(10f);
        return cell1;
    }

    private void addCheckBoxCell(String str, String number, PdfPTable table4) {
        PdfPCell cell1 = new PdfPCell();

        PdfPTable table3 = new PdfPTable(1);
        table3.setWidthPercentage(5);
        table3.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell cell = new PdfPCell();
        if (str.contains(number))
            cell.setCellEvent(new CheckboxCellEvent("cb", 0));
        cell.setBorderColor(BaseColor.BLACK);
        cell.setFixedHeight(40f);

        table3.addCell(cell);
        cell1.setPadding(10f);
        cell1.addElement(table3);
        table4.addCell(cell1);
    }

    public PdfPCell getImageCell(Bitmap bmp) throws IOException, BadElementException {
        // Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 50, stream);
        Image image = Image.getInstance(stream.toByteArray());
        //   image.scaleToFit(1, 1);
        PdfPCell cell = new PdfPCell(image, true);
        cell.setFixedHeight(300f);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    public PdfPCell createLogoCell() throws DocumentException, IOException {
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 50, stream);
        Image image = Image.getInstance(stream.toByteArray());
        image.setBorderColor(BaseColor.WHITE);
        image.setAlignment(Element.ALIGN_CENTER);
        image.setWidthPercentage(20);
        image.scaleToFit(1, 1);
        PdfPCell cell = new PdfPCell(image, true);
        cell.setBorderColor(BaseColor.WHITE);
        return cell;
    }

    class CheckboxCellEvent implements PdfPCellEvent {
        // The name of the check box field
        protected String name;
        protected int i;

        // We create a cell event
        public CheckboxCellEvent(String name, int i) {
            this.name = name;
            this.i = i;
        }

        // We create and add the check box field
        public void cellLayout(PdfPCell cell, Rectangle position,
                               PdfContentByte[] canvases) {
            PdfWriter writer = canvases[0].getPdfWriter();
            // define the coordinates of the middle
            float x = (position.getLeft() + position.getRight()) / 2;
            float y = (position.getTop() + position.getBottom()) / 2;
            // define the position of a check box that measures 20 by 20
            Rectangle rect = new Rectangle(x - 10, y - 10, x + 10, y + 10);
            // define the check box
            RadioCheckField checkbox = new RadioCheckField(
                    writer, rect, name, "Yes");
            switch (i) {
                case 0:
                    checkbox.setCheckType(RadioCheckField.TYPE_CHECK);
                    break;
                case 1:
                    checkbox.setCheckType(RadioCheckField.TYPE_CIRCLE);
                    break;
                case 2:
                    checkbox.setCheckType(RadioCheckField.TYPE_CROSS);
                    break;
                case 3:
                    checkbox.setCheckType(RadioCheckField.TYPE_DIAMOND);
                    break;
                case 4:
                    checkbox.setCheckType(RadioCheckField.TYPE_SQUARE);
                    break;
                case 5:
                    checkbox.setCheckType(RadioCheckField.TYPE_STAR);
                    break;
            }
            checkbox.setChecked(true);
            // add the check box as a field
            try {
                writer.addAnnotation(checkbox.getCheckField());
            } catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        }
    }
}
