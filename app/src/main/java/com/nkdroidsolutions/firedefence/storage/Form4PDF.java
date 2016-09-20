package com.nkdroidsolutions.firedefence.storage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import com.google.gson.Gson;
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
import com.nkdroidsolutions.firedefence.model.Form4Model.Engine;
import com.nkdroidsolutions.firedefence.model.Form4Model.General;
import com.nkdroidsolutions.firedefence.model.Form4Model.Report2Vehicle;
import com.nkdroidsolutions.firedefence.model.Form4Model.Report3General;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sahil on 17-09-2016.
 */
public class Form4PDF implements AllObserver {

    private Activity context;

    public Form4PDF(Activity context) {
        this.context = context;
    }

    Font font_heading = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
    Font font_normal = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.NORMAL);
    Font font_title = new Font(Font.FontFamily.TIMES_ROMAN, 50, Font.BOLD);

    private String file;

    public void createForm(String email) throws DocumentException, IOException {
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
        PdfPCell c1 = new PdfPCell(new Phrase("Vehicle CheckList", font_title));
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

        table2.addCell(addCell("Vehicle Registration"));
        table2.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport1().getVehicleReg()));
        table2.addCell(addCell("Date"));
        table2.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport1().getDate()));
        table2.addCell(addCell("Driver Name"));
        table2.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport1().getDriverName()));
        table2.addCell(addCell("Odometer (km/miles) reading"));
        table2.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport1().getOdometer()));
        table2.addCell(addCell("Comments"));
        table2.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport1().getComments()));

        section.add(table2);
//////////////////////////---------///////////////////////////


//////////////////////////Two///////////////////////////

        section.add(addHeading("Page 2"));
        section.add(addCommon());

        PdfPTable table3 = new PdfPTable(4);
        table3.setWidthPercentage(100);
        float[] columnWidths3 = new float[]{4f, 2f, 8f, 3f};
        table3.setWidths(columnWidths3);
        table3.setHorizontalAlignment(Element.ALIGN_LEFT);

        Report2Vehicle report2Vehicle = new Gson().fromJson(observerFormFour.getFormfour().getResponse().getReport2().getVehicleChecklist().replace("\\\\", ""), Report2Vehicle.class);
        List<Engine> engine = report2Vehicle.getEngine();

        table3.addCell(addCell("Engine Oil"));
        addCheckBoxCellFull(engine.get(0).getEngineOilChk(), "1", table3);
        table3.addCell(addCell(engine.get(0).getEngineOilComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Coolant Level"));
        addCheckBoxCellFull(engine.get(1).getCoolantLvlChk(), "1", table3);
        table3.addCell(addCell(engine.get(1).getCoolantLvlComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Brake Fluid Level"));
        addCheckBoxCellFull(engine.get(2).getBrakeFluidLvlChk(), "1", table3);
        table3.addCell(addCell(engine.get(2).getBrakeFluidLvlComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Steering Fluid Level"));
        addCheckBoxCellFull(engine.get(3).getSteeringFluidLvlChk(), "1", table3);
        table3.addCell(addCell(engine.get(3).getSteeringFluidLvlComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Washer Fluid Level"));
        addCheckBoxCellFull(engine.get(4).getWasherFluidLvlChk(), "1", table3);
        table3.addCell(addCell(engine.get(4).getWasherFluidLvlComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Washer and Wipers"));
        addCheckBoxCellFull(engine.get(5).getWasherWiperChk(), "1", table3);
        table3.addCell(addCell(engine.get(5).getWasherWiperComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Lights and horn"));
        addCheckBoxCellFull(engine.get(6).getLightHornChk(), "1", table3);
        table3.addCell(addCell(engine.get(6).getLightHornComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Tyre tread and sidewalls"));
        addCheckBoxCellFull(engine.get(7).getTyreTreadSidewallsChk(), "1", table3);
        table3.addCell(addCell(engine.get(7).getTyreTreadSidewallsComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Tyre pressures"));
        addCheckBoxCellFull(engine.get(8).getTyrePressuresChk(), "1", table3);
        table3.addCell(addCell(engine.get(8).getTyrePressuresComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Wheel nuts secure"));
        addCheckBoxCellFull(engine.get(9).getWheelNutsSecureChk(), "1", table3);
        table3.addCell(addCell(engine.get(9).getWheelNutsSecureComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Condition of battery"));
        addCheckBoxCellFull(engine.get(10).getConditionBatteryChk(), "1", table3);
        table3.addCell(addCell(engine.get(10).getConditionBatteryComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Bodywk, glass,mirrors"));
        addCheckBoxCellFull(engine.get(11).getBodywkChk(), "1", table3);
        table3.addCell(addCell(engine.get(11).getBodywkChkComment()));
//        //table3.addCell(getImageCell(engine.get(11).getBodywkChkBitmap()));
        if (observerFormFour.getFormfour().getResponse().getReport2().getBitmap() != null)
            table3.addCell(getImageCell(observerFormFour.getFormfour().getResponse().getReport2().getBitmap()));
        else
            table3.addCell(addCell(""));

        table3.addCell(addCell("First aid kit contents"));
        addCheckBoxCellFull(engine.get(12).getFirstAidKidContentChk(), "1", table3);
        table3.addCell(addCell(engine.get(12).getFirstAidKidContentComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Fire Extinguisher"));
        addCheckBoxCellFull(engine.get(13).getFireExteingusherChk(), "1", table3);
        table3.addCell(addCell(engine.get(13).getFireExteingusherComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Clean and tidy?"));
        addCheckBoxCellFull(engine.get(14).getCleanTidyChk(), "1", table3);
        table3.addCell(addCell(engine.get(14).getCleanTidyComment()));
        table3.addCell(addCell(""));

        table3.addCell(addCell("Brake Pads and Disks"));
        addCheckBoxCellFull(engine.get(15).getBreakPadsDisksChk(), "1", table3);
        table3.addCell(addCell(engine.get(15).getBreakPadsDisksComment()));
        table3.addCell(addCell(""));

        section.add(table3);

        //////////////////////////------///////////////////////////

        //////////////////////////Three////////////////////////////

        section.add(addHeading("Page 3"));
        section.add(addCommon());

        PdfPTable table4 = new PdfPTable(4);
        table4.setWidthPercentage(100);
        table4.setWidths(columnWidths3);
        table4.setHorizontalAlignment(Element.ALIGN_LEFT);

        Report3General report3General = new Gson().fromJson(observerFormFour.getFormfour().getResponse().getReport3().getVehicleChecklist().replace("\\\\", ""), Report3General.class);
        List<General> general = report3General.getGeneral();

        table4.addCell(addCell("General mechanical condition (e.g. How good are the brakes? Oil leaks?)"));
        addCheckBoxCellFull(general.get(0).getGeneralMechanical(), "1", table4);
        table4.addCell(addCell(general.get(0).getGeneralMechanicalComment()));
        table4.addCell(addCell(""));

        table4.addCell(addCell("General Body Work"));
        addCheckBoxCellFull(general.get(1).getGeneralBodyWork(), "1", table4);
        table4.addCell(addCell(general.get(1).getGeneralBodyWorkComment()));
        if (observerFormFour.getFormfour().getResponse().getReport3().getBitmap1() != null)
            table4.addCell(getImageCell(observerFormFour.getFormfour().getResponse().getReport3().getBitmap1()));
        else
            table4.addCell(addCell(""));

        table4.addCell(addCell("Condition of Interior"));
        addCheckBoxCellFull(general.get(2).getConditionInteriorWork(), "1", table4);
        table4.addCell(addCell(general.get(2).getConditionInteriorComment()));
        if (observerFormFour.getFormfour().getResponse().getReport3().getBitmap2() != null)
            table4.addCell(getImageCell(observerFormFour.getFormfour().getResponse().getReport3().getBitmap2()));
        else
            table4.addCell(addCell(""));

        section.add(table4);

        ////////////////////////////////////////

        //////////////////////////Four///////////////////////////

        section.add(addHeading("Page 4"));
        PdfPTable table9 = new PdfPTable(4);
        table9.setWidthPercentage(100);
        table9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table9.addCell(addCell("Defects"));
        table9.addCell(addCell("Preferred Repair Date"));
        table9.addCell(addCell("Deadline for Repair"));
        table9.addCell(addCell("Importance"));

        for (int i = 0; i < observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp().getAddDefect().size(); i++) {
            table9.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp().getAddDefect().get(i).getDefects()));
            table9.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp().getAddDefect().get(i).getPrefferDate()));
            table9.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp().getAddDefect().get(i).getRepairDeadline()));
            table9.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp().getAddDefect().get(i).getImportance()));
        }
        section.add(table9);

        /////////////////////-----------/////////////////////

        //////////////////////////Five///////////////////////////

        section.add(addHeading("Page 5"));
        section.add(addSubHeading("Vehicle Checklist Completion"));

        PdfPTable table13 = new PdfPTable(2);
        table13.setWidthPercentage(100);
        table13.setWidths(columnWidths1);
        table13.setHorizontalAlignment(Element.ALIGN_LEFT);
        table13.addCell(addCell("Date"));
        table13.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport5().getDate()));
        table13.addCell(addCell("Print Name"));
        table13.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport5().getPrintName()));
        table13.addCell(addCell("Driver Signature"));
        if (observerFormFour.getFormfour().getResponse().getReport5().getDriverBitmap() != null)
            table13.addCell(getImageCell(observerFormFour.getFormfour().getResponse().getReport5().getDriverBitmap()));
        else
            table13.addCell("");

        table13.addCell(addCell("Comments"));
        table13.addCell(addCell(observerFormFour.getFormfour().getResponse().getReport5().getComment()));
        section.add(table13);

        /////////////////////-----------/////////////////////

        document.add(section);
        document.close();

        pDialog.dismiss();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"servicing@fire-defence.com",email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "FDS Vehicle Checklist");
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

    private PdfPTable addCommon() throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        float[] columnWidths2 = new float[]{4f, 2f, 8f, 3f};
        table.setWidths(columnWidths2);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(addCell("Item"));
        table.addCell(addCell("Checked?"));
        table.addCell(addCell("Comments"));
        table.addCell(addCell(""));

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
