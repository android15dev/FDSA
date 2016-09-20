
package com.nkdroidsolutions.firedefence.model.Form3Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report4 {

    @SerializedName("date1")
    @Expose
    private String date1 = "";
    @SerializedName("print_name")
    @Expose
    private String printName = "";
    @SerializedName("position")
    @Expose
    private String position = "";
    @SerializedName("engineer_notes")
    @Expose
    private String engineerNotes = "";
    @SerializedName("customer_sign")
    @Expose
    private String customer_sign = "";
    private Bitmap signBitmap;

    public Bitmap getSignBitmap() {
        return signBitmap;
    }

    public void setSignBitmap(Bitmap signBitmap) {
        this.signBitmap = signBitmap;
    }

    public String getCustomer_sign() {
        return customer_sign;
    }

    public void setCustomer_sign(String customer_sign) {
        this.customer_sign = customer_sign;
    }

    /**
     * @return The date1
     */
    public String getDate1() {
        return date1;
    }

    /**
     * @param date1 The date1
     */
    public void setDate1(String date1) {
        this.date1 = date1;
    }

    /**
     * @return The printName
     */
    public String getPrintName() {
        return printName;
    }

    /**
     * @param printName The print_name
     */
    public void setPrintName(String printName) {
        this.printName = printName;
    }

    /**
     * @return The position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position The position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return The engineerNotes
     */
    public String getEngineerNotes() {
        return engineerNotes;
    }

    /**
     * @param engineerNotes The engineer_notes
     */
    public void setEngineerNotes(String engineerNotes) {
        this.engineerNotes = engineerNotes;
    }

}
