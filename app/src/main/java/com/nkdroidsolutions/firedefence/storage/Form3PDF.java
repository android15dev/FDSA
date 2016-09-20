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
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Sahil on 17-09-2016.
 */
public class Form3PDF implements AllObserver {

    private Activity context;

    public Form3PDF(Activity context) {
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
        PdfPCell c1 = new PdfPCell(new Phrase("Fire Extinguisher Check List", font_title));
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

        table2.addCell(addCell("Job No."));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getJobNo()));
        table2.addCell(addCell("Month Due"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getMonthDue()));
        table2.addCell(addCell("Last Serviced"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getLastServiced()));
        table2.addCell(addCell("Time on Site"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getTimeSite()));
        table2.addCell(addCell("Travel Time to Site"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getTravelTimeSite()));
        table2.addCell(addCell("Company Name"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getCompanyName()));
        table2.addCell(addCell("Contact Person"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getContactPerson()));
        table2.addCell(addCell("Telephone Number"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getTelNo()));
        table2.addCell(addCell("Mobile Number"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getMobNo()));
        table2.addCell(addCell("Site Address"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getSiteAddress()));
        table2.addCell(addCell("Additional Notes to Service Engineer"));
        table2.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport1().getNotes()));
        table2.addCell(addCell("Type"));
        if (observerFormThree.getFormthree().getResponse().getReport1().getService().contains("1")) {
            table2.addCell(addCell("Service"));
        } else if (observerFormThree.getFormthree().getResponse().getReport1().getService().contains("2")) {
            table2.addCell(addCell("Call Out"));
        } else if (observerFormThree.getFormthree().getResponse().getReport1().getService().contains("3")) {
            table2.addCell(addCell("Install"));
        } else if (observerFormThree.getFormthree().getResponse().getReport1().getService().contains("4")) {
            table2.addCell(addCell("Survey"));
        }

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
        table4.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport2().getDate()));
        table4.addCell(addCell("Time"));
        table4.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport2().getTime()));
        table4.addCell(addCell("Client Name"));
        table4.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport2().getClientName()));
        table4.addCell(addCell("Client Signature"));
        if (observerFormThree.getFormthree().getResponse().getReport2().getSignBitmap() != null)
            table4.addCell(getImageCell(observerFormThree.getFormthree().getResponse().getReport2().getSignBitmap()));
        else
            table4.addCell("");
        section.add(table4);
//////////////////////////-----///////////////////////////

//////////////////////////Three///////////////////////////

        section.add(addHeading("Page 3"));
        PdfPTable table9 = new PdfPTable(10);
        table9.setSpacingBefore(10f);
        table9.setWidthPercentage(100);
/*
        float[] columnWidths3 = new float[]{1f, 1f, 1f};
        table9.setWidths(columnWidths3);
*/
        table9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table9.addCell(addCell("Manufacturer"));
        table9.addCell(addCell("Type"));
        table9.addCell(addCell("Capacity (KG or L)"));
        table9.addCell(addCell("Location"));
        table9.addCell(addCell("Weight (KG)"));
        table9.addCell(addCell("Date of manuf"));
        table9.addCell(addCell("Update Label"));
        table9.addCell(addCell("New Tag"));
        table9.addCell(addCell("Services"));
        table9.addCell(addCell("Comments"));

        for (int i = 0; i < observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().size(); i++) {
            table9.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getManufacturer()));
            table9.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getType()));
            table9.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getCapacity()));
            table9.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getLocation()));
            table9.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getWeight()));
            table9.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getManuDate()));
            table9.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getUpdateLabel()));
            table9.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getNewTag()));
            if (observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getServices().contains("1")) {
                table9.addCell(addCell("Extended Service required"));
            } else if (observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getServices().contains("2")) {
                table9.addCell(addCell("Condemned"));
            } else if (observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getServices().contains("3")) {
                table9.addCell(addCell("New required"));
            } else if (observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getServices().contains("4")) {
                table9.addCell(addCell("Wall Bracket required"));
            }
            table9.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(i).getComments()));
        }
        section.add(table9);

        //////////////////////////-----///////////////////////////

        //////////////////////////Four///////////////////////////

        section.add(addHeading("Page 4"));
        section.add(addSubHeading("Work Completion Authorisation"));

        PdfPTable table13 = new PdfPTable(2);
        table13.setWidthPercentage(100);
        table13.setWidths(columnWidths2);
        table13.setHorizontalAlignment(Element.ALIGN_LEFT);
        table13.addCell(addCell("Date"));
        table13.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport4().getDate1()));
        table13.addCell(addCell("Print Name"));
        table13.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport4().getPrintName()));
        table13.addCell(addCell("Position"));
        table13.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport4().getPosition()));
        table13.addCell(addCell("Customer Signature"));
        if (observerFormThree.getFormthree().getResponse().getReport4().getSignBitmap() != null)
            table13.addCell(getImageCell(observerFormThree.getFormthree().getResponse().getReport4().getSignBitmap()));
        else
            table13.addCell("");

        table13.addCell(addCell("Engineers Notes"));
        table13.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport4().getEngineerNotes()));
        section.add(table13);

        /////////////////////-----------/////////////////////

        //////////////////////////Five///////////////////////////

        section.add(addHeading("Page 5"));

        PdfPTable table14 = new PdfPTable(2);
        table14.setWidthPercentage(100);
        table14.setWidths(columnWidths2);
        table14.setHorizontalAlignment(Element.ALIGN_LEFT);
        table14.addCell(addCell("Date"));
        table14.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport5().getDate2()));
        table14.addCell(addCell("Time Out"));
        table14.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport5().getTimeOut()));
        table14.addCell(addCell("Mileage"));
        table14.addCell(addCell(observerFormThree.getFormthree().getResponse().getReport5().getMileage()));
        section.add(table14);

        /////////////////////-----------/////////////////////


        document.add(section);
        document.close();

        pDialog.dismiss();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"servicing@fire-defence.com",email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "FDS Fire Extinguisher Check List");
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
}
