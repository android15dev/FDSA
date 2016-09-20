
package com.nkdroidsolutions.firedefence.model.Form4Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report5 {

    @SerializedName("date2")
    @Expose
    private String date2 = "";
    @SerializedName("print_name")
    @Expose
    private String printName = "";
    @SerializedName("comment")
    @Expose
    private String comment = "";

    @SerializedName("driver_sign")
    @Expose
    private String driver_sign = "";

    private Bitmap driverBitmap;

    public Bitmap getDriverBitmap() {
        return driverBitmap;
    }

    public void setDriverBitmap(Bitmap driverBitmap) {
        this.driverBitmap = driverBitmap;
    }

    public String getDriver_sign() {
        return driver_sign;
    }

    public void setDriver_sign(String driver_sign) {
        this.driver_sign = driver_sign;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date2;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date2 = date;
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
     * @return The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

}
