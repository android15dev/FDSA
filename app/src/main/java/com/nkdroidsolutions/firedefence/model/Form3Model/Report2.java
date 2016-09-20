
package com.nkdroidsolutions.firedefence.model.Form3Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report2 {

    @SerializedName("date")
    @Expose
    private String date = "";
    @SerializedName("time")
    @Expose
    private String time = "";
    @SerializedName("client_name")
    @Expose
    private String clientName = "";
    @SerializedName("client_sign")
    @Expose
    private String client_sign = "";
    private Bitmap signBitmap;

    public Bitmap getSignBitmap() {
        return signBitmap;
    }

    public void setSignBitmap(Bitmap signBitmap) {
        this.signBitmap = signBitmap;
    }

    public String getClient_sign() {
        return client_sign;
    }

    public void setClient_sign(String client_sign) {
        this.client_sign = client_sign;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return The clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName The client_name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

}
