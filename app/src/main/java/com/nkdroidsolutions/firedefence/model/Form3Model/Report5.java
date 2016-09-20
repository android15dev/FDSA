
package com.nkdroidsolutions.firedefence.model.Form3Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report5 {

    @SerializedName("date2")
    @Expose
    private String date2 = "";
    @SerializedName("time_out")
    @Expose
    private String timeOut = "";
    @SerializedName("mileage")
    @Expose
    private String mileage = "";

    /**
     * @return The date2
     */
    public String getDate2() {
        return date2;
    }

    /**
     * @param date2 The date2
     */
    public void setDate2(String date2) {
        this.date2 = date2;
    }

    /**
     * @return The timeOut
     */
    public String getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut The time_out
     */
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * @return The mileage
     */
    public String getMileage() {
        return mileage;
    }

    /**
     * @param mileage The mileage
     */
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

}
