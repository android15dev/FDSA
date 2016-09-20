package com.nkdroidsolutions.firedefence.storage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

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
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.HalfYearly;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.Quarterly;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.TripTest;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.Yearly;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sahil on 17-09-2016.
 */
public class Form2PDF implements AllObserver {

    private Activity context;

    public Form2PDF(Activity context) {
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
        PdfPCell c1 = new PdfPCell(new Phrase("Sprinkle Maintainance Check List", font_title));
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
        table2.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport1().getJobNo()));
        table2.addCell(addCell("Visit No."));
        table2.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport1().getVisitNo()));
        table2.addCell(addCell("Date"));
        table2.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport1().getReportdate1()));
        table2.addCell(addCell("Arrived"));
        table2.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport1().getArrived()));
        table2.addCell(addCell("Service Report No."));
        table2.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport1().getServiceReportNo()));
        table2.addCell(addCell("Client Name / Site"));
        table2.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport1().getClientName()));
        table2.addCell(addCell("Notes"));
        table2.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport1().getNotes1()));

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
        table4.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport2().getReportdate2()));
        table4.addCell(addCell("Authorised Signature Name"));
        table4.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedSignName()));
        table4.addCell(addCell("Authorised Signature"));
        //  table4.addCell(getImageCell(observerFormTwo.getFormTwo().getResponse().getReport2().getSignBitmap()));
        if (observerFormTwo.getFormTwo().getResponse().getReport2().getSignBitmap() != null)
            table4.addCell(getImageCell(observerFormTwo.getFormTwo().getResponse().getReport2().getSignBitmap()));
        else
            table4.addCell("");
        table4.addCell(addCell("Client Unavailable to Sign"));
        addCheckBoxCell(observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedBy(), "1", table4);
        table4.addCell(addCell("For FDS only"));
        addCheckBoxCell(observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedBy(), "2", table4);

        section.add(table4);
//////////////////////////-----///////////////////////////

//////////////////////////Three///////////////////////////

        section.add(addHeading("Page 3"));

        PdfPTable table6 = new PdfPTable(2);
        table6.setWidthPercentage(100);
        table6.setWidths(columnWidths2);
        table6.setHorizontalAlignment(Element.ALIGN_LEFT);

        table6.addCell(addCell("Quarterly"));
        addCheckBoxCell(observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems(), "1", table6);
        table6.addCell(addCell("Half Yearly"));
        addCheckBoxCell(observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems(), "2", table6);
        table6.addCell(addCell("Yearly"));
        addCheckBoxCell(observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems(), "3", table6);
        table6.addCell(addCell("Alternate Trip Test"));
        addCheckBoxCell(observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems(), "4", table6);

        table6.addCell(addCell("Notes"));
        table6.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport3().getSystemNotes()));
        section.add(table6);

        //////////////////////////-----///////////////////////////

        //////////////////////////Four///////////////////////////

        section.add(addHeading("Page 4"));
        section.add(addSubHeading("Checks Done"));
        section.add(addCommon());
        if (observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems().contains("1")) {
            section.add(addQuartely());
        }
        if (observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems().contains("2")) {
            section.add(addHalfYearly());
        }
        if (observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems().contains("3")) {
            section.add(addYarly());
        }
        if (observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems().contains("4")) {
            section.add(addAlternateList());
        }

        PdfPTable table7 = new PdfPTable(2);
        table7.setSpacingBefore(15f);
        table7.setWidthPercentage(100);
        table7.setWidths(columnWidths2);
        table7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(addCell("Notes"));
        table7.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport4().getNotes2()));

        section.add(table7);

        //////////////////////////------///////////////////////////

        //////////////////////////Five///////////////////////////

        section.add(addHeading("Page 5"));
        section.add(addSubHeading("Pump"));
        PdfPTable table9 = new PdfPTable(3);
        table9.setWidthPercentage(100);
        float[] columnWidths3 = new float[]{1f, 1f, 1f};
        table9.setWidths(columnWidths3);
        table9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table9.addCell(addCell("Pump No."));
        table9.addCell(addCell("Pump Start Reading"));
        table9.addCell(addCell("Pump Stop Reading"));

        for (int i = 0; i < observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp().getPumpData().size(); i++) {
            table9.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp().getPumpData().get(i).getPumpNo()));
            table9.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp().getPumpData().get(i).getPumpStartReading()));
            table9.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp().getPumpData().get(i).getPumpStopReading()));
        }
        section.add(table9);

        section.add(addSubHeading("Valve"));
        PdfPTable table8 = new PdfPTable(3);
        table8.setWidthPercentage(100);
        table8.setWidths(columnWidths3);
        table8.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8.addCell(addCell("Valve No."));
        table8.addCell(addCell("Flow Number"));
        table8.addCell(addCell("Pressure Valve"));

        for (int i = 0; i < observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp().getValveData().size(); i++) {
            table8.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp().getValveData().get(i).getValveNumber()));
            table8.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp().getValveData().get(i).getFlowValve()));
            table8.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp().getValveData().get(i).getPressureValve()));

        }
        section.add(table8);

        /////////////////////-----------/////////////////////

        //////////////////////////Six///////////////////////////

        section.add(addHeading("Page 6"));
        section.add(addSubHeading("Completion of Work"));

        PdfPTable table13 = new PdfPTable(2);
        table13.setWidthPercentage(100);
        table13.setWidths(columnWidths2);
        table13.setHorizontalAlignment(Element.ALIGN_LEFT);
        table13.addCell(addCell("Date"));
        table13.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport6().getDate()));
        table13.addCell(addCell("Time"));
        table13.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport6().getTime()));
        table13.addCell(addCell("Client Name"));
        table13.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport6().getClientNameWork()));
        table13.addCell(addCell("Work Completed"));
        table13.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport6().getWorkCompleted()));
        table13.addCell(addCell("Back Online"));
        table13.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport6().getBackOnline()));
        table13.addCell(addCell("Client Signature"));
        if (observerFormTwo.getFormTwo().getResponse().getReport6().getClientBitmap() != null)
            table13.addCell(getImageCell(observerFormTwo.getFormTwo().getResponse().getReport6().getClientBitmap()));
        else
            table13.addCell("");
        table13.addCell(addCell("Engineer Signature"));
        if (observerFormTwo.getFormTwo().getResponse().getReport6().getEngineerBitmap() != null) {
            table13.addCell(getImageCell(observerFormTwo.getFormTwo().getResponse().getReport6().getEngineerBitmap()));
        } else {
            table13.addCell("");
        }

        table13.addCell(addCell("Notes"));
        table13.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport6().getNotes()));
        section.add(table13);

        /////////////////////-----------/////////////////////

        //////////////////////////Seven///////////////////////////

        section.add(addHeading("Page 7"));

        PdfPTable table14 = new PdfPTable(2);
        table14.setWidthPercentage(100);
        table14.setWidths(columnWidths2);
        table14.setHorizontalAlignment(Element.ALIGN_LEFT);
        table14.addCell(addCell("Time Completed"));
        table14.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport7().getTimeCompleted()));
        table14.addCell(addCell("Travel Time"));
        table14.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport7().getTraveltime()));
        table14.addCell(addCell("Mileage"));
        table14.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport7().getMileage()));
        table14.addCell(addCell("Work Completed"));
        table14.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport7().getWorkCompletedLast()));
        table14.addCell(addCell("Email Address"));
        table14.addCell(addCell(observerFormTwo.getFormTwo().getResponse().getReport7().getEmailAddress()));
        section.add(table14);

        /////////////////////-----------/////////////////////


        document.add(section);
        document.close();

        pDialog.dismiss();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"servicing@fire-defence.com",observerFormTwo.getFormTwo().getResponse().getReport7().getEmailAddress()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "FDS Sprinkle Maintainance Check List");
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

    private PdfPTable addQuartely() throws DocumentException {
        ArrayList<String> item = new ArrayList<>();
        item.addAll(new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.quarterly))));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        float[] columnWidths2 = new float[]{4f, 1f, 1f, 8f};
        table.setWidths(columnWidths2);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        for (int i = 0; i < item.size(); i++) {
            table.addCell(addCell(item.get(i)));
            Quarterly quarterly = observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp().getQuarterly().get(i);
            if (i == 0) {
                addCheckBoxCellFull(quarterly.getRiskLayoutChangesDone(), "1", table);
                addCheckBoxCellFull(quarterly.getRiskLayoutChangesNa(), "1", table);
                table.addCell(addCell(quarterly.getRiskLayoutChangesComment()));
            } else if (i == 1) {
                addCheckBoxCellFull(quarterly.getOccupacyChangesDone(), "1", table);
                addCheckBoxCellFull(quarterly.getOccupacyChangesNa(), "1", table);
                table.addCell(addCell(quarterly.getOccupacyChangesComment()));
            } else if (i == 2) {
                addCheckBoxCellFull(quarterly.getHeatingChangesDone(), "1", table);
                addCheckBoxCellFull(quarterly.getHeatingChangesNa(), "1", table);
                table.addCell(addCell(quarterly.getHeatingChangesComment()));
            } else if (i == 3) {
                addCheckBoxCellFull(quarterly.getLightingChangesDone(), "1", table);
                addCheckBoxCellFull(quarterly.getLightingChangesNa(), "1", table);
                table.addCell(addCell(quarterly.getLightingChangesComment()));
            } else if (i == 4) {
                addCheckBoxCellFull(quarterly.getEquipmentChangesDone(), "1", table);
                addCheckBoxCellFull(quarterly.getEquipmentChangesNa(), "1", table);
                table.addCell(addCell(quarterly.getEquipmentChangesComment()));
            } else if (i == 5) {
                addCheckBoxCellFull(quarterly.getWaterClassificationDone(), "1", table);
                addCheckBoxCellFull(quarterly.getWaterClassificationNa(), "1", table);
                table.addCell(addCell(quarterly.getWaterClassificationComment()));
            } else if (i == 6) {
                addCheckBoxCellFull(quarterly.getSprinklerObstructionDone(), "1", table);
                addCheckBoxCellFull(quarterly.getSprinklerObstructionNa(), "1", table);
                table.addCell(addCell(quarterly.getSprinklerObstructionComment()));
            } else if (i == 7) {
                addCheckBoxCellFull(quarterly.getClearanceOverStockDone(), "1", table);
                addCheckBoxCellFull(quarterly.getClearanceOverStockNa(), "1", table);
                table.addCell(addCell(quarterly.getClearanceOverStockComment()));
            } else if (i == 8) {
                addCheckBoxCellFull(quarterly.getSprinklerTempRateDone(), "1", table);
                addCheckBoxCellFull(quarterly.getSprinklerTempRateNa(), "1", table);
                table.addCell(addCell(quarterly.getSprinklerTempRateComment()));
            } else if (i == 9) {
                addCheckBoxCellFull(quarterly.getSprinklerConditionDone(), "1", table);
                addCheckBoxCellFull(quarterly.getSprinklerConditionNa(), "1", table);
                table.addCell(addCell(quarterly.getSprinklerConditionComment()));
            } else if (i == 10) {
                addCheckBoxCellFull(quarterly.getPipworkConditionDone(), "1", table);
                addCheckBoxCellFull(quarterly.getPipworkConditionNa(), "1", table);
                table.addCell(addCell(quarterly.getPipworkConditionComment()));
            } else if (i == 11) {
                addCheckBoxCellFull(quarterly.getHangerConditionDone(), "1", table);
                addCheckBoxCellFull(quarterly.getHangerConditionNa(), "1", table);
                table.addCell(addCell(quarterly.getHangerConditionComment()));
            } else if (i == 12) {
                addCheckBoxCellFull(quarterly.getElectricalConnectionDone(), "1", table);
                addCheckBoxCellFull(quarterly.getElectricalConnectionNa(), "1", table);
                table.addCell(addCell(quarterly.getElectricalConnectionComment()));
            } else if (i == 13) {
                addCheckBoxCellFull(quarterly.getAlarmValveProvingPipeDone(), "1", table);
                addCheckBoxCellFull(quarterly.getAlarmValveProvingPipeNa(), "1", table);
                table.addCell(addCell(quarterly.getAlarmValveProvingPipeComment()));
            } else if (i == 14) {
                addCheckBoxCellFull(quarterly.getPumpAutoStartDone(), "1", table);
                addCheckBoxCellFull(quarterly.getPumpAutoStartNa(), "1", table);
                table.addCell(addCell(quarterly.getPumpAutoStartComment()));
            } else if (i == 15) {
                addCheckBoxCellFull(quarterly.getBatteryLevelDone(), "1", table);
                addCheckBoxCellFull(quarterly.getBatteryLevelNa(), "1", table);
                table.addCell(addCell(quarterly.getBatteryLevelComment()));
            } else if (i == 16) {
                addCheckBoxCellFull(quarterly.getBatteryDensityDone(), "1", table);
                addCheckBoxCellFull(quarterly.getBatteryDensityNa(), "1", table);
                table.addCell(addCell(quarterly.getBatteryDensityComment()));
            } else if (i == 17) {
                addCheckBoxCellFull(quarterly.getStopValvesDone(), "1", table);
                addCheckBoxCellFull(quarterly.getStopValvesNa(), "1", table);
                table.addCell(addCell(quarterly.getStopValvesComment()));
            } else if (i == 18) {
                addCheckBoxCellFull(quarterly.getFlowSwitchesDone(), "1", table);
                addCheckBoxCellFull(quarterly.getFlowSwitchesNa(), "1", table);
                table.addCell(addCell(quarterly.getFlowSwitchesComment()));
            } else if (i == 19) {
                addCheckBoxCellFull(quarterly.getSparesDone(), "1", table);
                addCheckBoxCellFull(quarterly.getSparesNa(), "1", table);
                table.addCell(addCell(quarterly.getSparesComment()));
            } else if (i == 20) {
                addCheckBoxCellFull(quarterly.getHighestSprinklerDone(), "1", table);
                addCheckBoxCellFull(quarterly.getHighestSprinklerNa(), "1", table);
                table.addCell(addCell(quarterly.getHighestSprinklerComment()));
            }
        }

        return table;
    }

    private PdfPTable addHalfYearly() throws DocumentException {
        ArrayList<String> item = new ArrayList<>();
        item.addAll(new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.half_yearly))));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        float[] columnWidths2 = new float[]{4f, 1f, 1f, 8f};
        table.setWidths(columnWidths2);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        for (int i = 0; i < item.size(); i++) {
            table.addCell(addCell(item.get(i)));
            HalfYearly halfYearly = observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp().getHalfYearly().get(i);
            if (i == 0) {
                addCheckBoxCellFull(halfYearly.getAlarmValveInspectionDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getAlarmValveInspectionNa(), "1", table);
                table.addCell(addCell(halfYearly.getAlarmValveInspectionComment()));
            } else if (i == 1) {
                addCheckBoxCellFull(halfYearly.getAirValveInspectionDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getAirValveInspectionNa(), "1", table);
                table.addCell(addCell(halfYearly.getAirValveInspectionComment()));
            } else if (i == 2) {
                addCheckBoxCellFull(halfYearly.getAlarmTurbineDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getAlarmTurbineNa(), "1", table);
                table.addCell(addCell(halfYearly.getAlarmTurbineComment()));
            } else if (i == 3) {
                addCheckBoxCellFull(halfYearly.getAlarmFilterDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getAlarmFilterNa(), "1", table);
                table.addCell(addCell(halfYearly.getAlarmFilterComment()));
            } else if (i == 4) {
                addCheckBoxCellFull(halfYearly.getBellTestDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getBellTestNa(), "1", table);
                table.addCell(addCell(halfYearly.getBellTestComment()));
            } else if (i == 5) {
                addCheckBoxCellFull(halfYearly.getChainLocksDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getChainLocksNa(), "1", table);
                table.addCell(addCell(halfYearly.getChainLocksComment()));
            } else if (i == 6) {
                addCheckBoxCellFull(halfYearly.getChangeOverWaterDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getChangeOverWaterNa(), "1", table);
                table.addCell(addCell(halfYearly.getChangeOverWaterComment()));
            } else if (i == 7) {
                addCheckBoxCellFull(halfYearly.getCleanResetAcceleratorDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getCleanResetAcceleratorNa(), "1", table);
                table.addCell(addCell(halfYearly.getCleanResetAcceleratorComment()));
            } else if (i == 8) {
                addCheckBoxCellFull(halfYearly.getCompressorOilLevelDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getCompressorOilLevelNa(), "1", table);
                table.addCell(addCell(halfYearly.getCompressorOilLevelComment()));
            } else if (i == 9) {
                addCheckBoxCellFull(halfYearly.getCompressorAirFilterDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getCompressorAirFilterNa(), "1", table);
                table.addCell(addCell(halfYearly.getCompressorAirFilterComment()));
            } else if (i == 10) {
                addCheckBoxCellFull(halfYearly.getChangeOverWaterDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getChangeOverWaterNa(), "1", table);
                table.addCell(addCell(halfYearly.getChangeOverWaterComment()));
            } else if (i == 11) {
                addCheckBoxCellFull(halfYearly.getOrainAirFromRemotePointDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getOrainAirFromRemotePointNa(), "1", table);
                table.addCell(addCell(halfYearly.getOrainAirFromRemotePointComment()));
            } else if (i == 12) {
                addCheckBoxCellFull(halfYearly.getFailToStartTestDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getFailToStartTestNa(), "1", table);
                table.addCell(addCell(halfYearly.getFailToStartTestComment()));
            } else if (i == 13) {
                addCheckBoxCellFull(halfYearly.getFireInPumphouseDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getFireInPumphouseNa(), "1", table);
                table.addCell(addCell(halfYearly.getFireInPumphouseComment()));
            } else if (i == 14) {
                addCheckBoxCellFull(halfYearly.getOverSpeedTestDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getOverSpeedTestNa(), "1", table);
                table.addCell(addCell(halfYearly.getOverSpeedTestComment()));
            } else if (i == 15) {
                addCheckBoxCellFull(halfYearly.getPressureReliefTestDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getPressureReliefTestNa(), "1", table);
                table.addCell(addCell(halfYearly.getPressureReliefTestComment()));
            } else if (i == 16) {
                addCheckBoxCellFull(halfYearly.getOpticalTacoTestDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getOpticalTacoTestNa(), "1", table);
                table.addCell(addCell(halfYearly.getOpticalTacoTestComment()));
            } else if (i == 17) {
                addCheckBoxCellFull(halfYearly.getTankBallValveDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getTankBallValveNa(), "1", table);
                table.addCell(addCell(halfYearly.getTankBallValveComment()));
            } else if (i == 18) {
                addCheckBoxCellFull(halfYearly.getTraceHeatingDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getTraceHeatingNa(), "1", table);
                table.addCell(addCell(halfYearly.getTraceHeatingComment()));
            } else if (i == 19) {
                addCheckBoxCellFull(halfYearly.getStorageTankLevelDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getStorageTankLevelNa(), "1", table);
                table.addCell(addCell(halfYearly.getStorageTankLevelComment()));
            } else if (i == 20) {
                addCheckBoxCellFull(halfYearly.getStorageTankHeightDone(), "1", table);
                addCheckBoxCellFull(halfYearly.getStorageTankHeightNa(), "1", table);
                table.addCell(addCell(halfYearly.getStorageTankHeightComment()));
            }
        }

        return table;
    }

    private PdfPTable addYarly() throws DocumentException {
        ArrayList<String> item = new ArrayList<>();
        item.addAll(new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.yearly))));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        float[] columnWidths2 = new float[]{4f, 1f, 1f, 8f};
        table.setWidths(columnWidths2);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        for (int i = 0; i < item.size(); i++) {
            table.addCell(addCell(item.get(i)));
            Yearly yearly = observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp().getYearly().get(i);
            if (i == 0) {
                addCheckBoxCellFull(yearly.getPumpsGeneralInspectionDone(), "1", table);
                addCheckBoxCellFull(yearly.getPumpsGeneralInspectionNa(), "1", table);
                table.addCell(addCell(yearly.getPumpsGeneralInspectionComment()));
            } else if (i == 1) {
                addCheckBoxCellFull(yearly.getLooseGaurdBoltsDone(), "1", table);
                addCheckBoxCellFull(yearly.getLooseGaurdBoltsNa(), "1", table);
                table.addCell(addCell(yearly.getLooseGaurdBoltsComment()));
            } else if (i == 2) {
                addCheckBoxCellFull(yearly.getFluidLeaksDone(), "1", table);
                addCheckBoxCellFull(yearly.getFluidLeaksNa(), "1", table);
                table.addCell(addCell(yearly.getFluidLeaksComment()));
            } else if (i == 3) {
                addCheckBoxCellFull(yearly.getDieselPumpRunHalfHourDone(), "1", table);
                addCheckBoxCellFull(yearly.getDieselPumpRunHalfHourNa(), "1", table);
                table.addCell(addCell(yearly.getDieselPumpRunHalfHourComment()));
            } else if (i == 4) {
                addCheckBoxCellFull(yearly.getElectricPumpRun10minDone(), "1", table);
                addCheckBoxCellFull(yearly.getElectricPumpRun10minNa(), "1", table);
                table.addCell(addCell(yearly.getElectricPumpRun10minComment()));
            } else if (i == 5) {
                addCheckBoxCellFull(yearly.getOilFilterChangeDone(), "1", table);
                addCheckBoxCellFull(yearly.getOilFilterChangeNa(), "1", table);
                table.addCell(addCell(yearly.getOilFilterChangeComment()));
            } else if (i == 6) {
                addCheckBoxCellFull(yearly.getStorageTankDone(), "1", table);
                addCheckBoxCellFull(yearly.getStorageTankNa(), "1", table);
                table.addCell(addCell(yearly.getStorageTankComment()));
            }
        }

        return table;
    }

    private PdfPTable addAlternateList() throws DocumentException {
        ArrayList<String> item = new ArrayList<>();
        item.addAll(new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.alternate_trip))));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        float[] columnWidths2 = new float[]{4f, 1f, 1f, 8f};
        table.setWidths(columnWidths2);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        for (int i = 0; i < item.size(); i++) {
            table.addCell(addCell(item.get(i)));
            TripTest tripTest = observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp().getTripTest().get(i);
            if (i == 0) {
                addCheckBoxCellFull(tripTest.getStartAirPressureDone(), "1", table);
                addCheckBoxCellFull(tripTest.getStartAirPressureNa(), "1", table);
                table.addCell(addCell(tripTest.getStartAirPressureComment()));
            } else if (i == 1) {
                addCheckBoxCellFull(tripTest.getAcceleratorSetDone(), "1", table);
                addCheckBoxCellFull(tripTest.getAcceleratorSetNa(), "1", table);
                table.addCell(addCell(tripTest.getAcceleratorSetComment()));
            } else if (i == 2) {
                addCheckBoxCellFull(tripTest.getTestValveDiaDone(), "1", table);
                addCheckBoxCellFull(tripTest.getTestValveDiaNa(), "1", table);
                table.addCell(addCell(tripTest.getTestValveDiaComment()));
            } else if (i == 3) {
                addCheckBoxCellFull(tripTest.getTimeToTripDone(), "1", table);
                addCheckBoxCellFull(tripTest.getTimeToTripNa(), "1", table);
                table.addCell(addCell(tripTest.getTimeToTripComment()));
            } else if (i == 4) {
                addCheckBoxCellFull(tripTest.getTimeToDischargeWaterDone(), "1", table);
                addCheckBoxCellFull(tripTest.getTimeToDischargeWaterNa(), "1", table);
                table.addCell(addCell(tripTest.getTimeToDischargeWaterComment()));
            } else if (i == 5) {
                addCheckBoxCellFull(tripTest.getLocationTestTripValveDone(), "1", table);
                addCheckBoxCellFull(tripTest.getLocationTestTripValveNa(), "1", table);
                table.addCell(addCell(tripTest.getLocationTestTripValveComment()));
            }
        }

        return table;
    }

    private PdfPTable addCommonPage5(String str1, String str2, String str3) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        float[] columnWidths2 = new float[]{1f, 1f, 1f};
        table.setWidths(columnWidths2);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(addCell(str1));
        table.addCell(addCell(str2));
        table.addCell(addCell(str3));


        return table;
    }

    private PdfPTable addCommon() throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        float[] columnWidths2 = new float[]{4f, 1f, 1f, 8f};
        table.setWidths(columnWidths2);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(addCell("Check"));
        table.addCell(addCell("Done"));
        table.addCell(addCell("N/A"));
        table.addCell(addCell("Comment"));

        return table;
    }

    private void addCheckBoxCellFull(String str, String number, PdfPTable table4) {
        PdfPCell cell1 = new PdfPCell();

        PdfPTable table3 = new PdfPTable(1);
        table3.setWidthPercentage(50);
        table3.setHorizontalAlignment(Element.ALIGN_CENTER);

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
}
