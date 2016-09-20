
package com.nkdroidsolutions.firedefence.model.Form1Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Report6 implements Serializable {

    @SerializedName("reportdate3")
    @Expose
    private String reportdate3="";
    @SerializedName("reporttime")
    @Expose
    private String reporttime="";
    @SerializedName("client_name")
    @Expose
    private String clientName="";
    @SerializedName("work_completed")
    @Expose
    private String workCompleted="";
    @SerializedName("back_online")
    @Expose
    private String backOnline="";
    @SerializedName("notes_work")
    @Expose
    private String notes_work="";
    @SerializedName("client_sign")
    @Expose
    private String clientSign="";
    @SerializedName("engineer_sign")
    @Expose
    private String engineerSign="";

    private Bitmap clientBitmap;
    private Bitmap engineerBitmap;

    public Bitmap getClientBitmap() {
        return clientBitmap;
    }

    public void setClientBitmap(Bitmap clientBitmap) {
        this.clientBitmap = clientBitmap;
    }

    public Bitmap getEngineerBitmap() {
        return engineerBitmap;
    }

    public void setEngineerBitmap(Bitmap engineerBitmap) {
        this.engineerBitmap = engineerBitmap;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return reportdate3;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.reportdate3 = date;
    }

    /**
     * @return The time
     */
    public String getTime() {
        return reporttime;
    }

    /**
     * @param time The time
     */
    public void setTime(String time) {
        this.reporttime = time;
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

    /**
     * @return The workCompleted
     */
    public String getWorkCompleted() {
        return workCompleted;
    }

    /**
     * @param workCompleted The work_completed
     */
    public void setWorkCompleted(String workCompleted) {
        this.workCompleted = workCompleted;
    }

    /**
     * @return The backOnline
     */
    public String getBackOnline() {
        return backOnline;
    }

    /**
     * @param backOnline The back_online
     */
    public void setBackOnline(String backOnline) {
        this.backOnline = backOnline;
    }

    /**
     * @return The notes
     */
    public String getNotes() {
        return notes_work;
    }

    /**
     * @param notes The notes
     */
    public void setNotes(String notes) {
        this.notes_work = notes;
    }

    /**
     * @return The clientSign
     */
    public String getClientSign() {
        return clientSign;
    }

    /**
     * @param clientSign The client_sign
     */
    public void setClientSign(String clientSign) {
        this.clientSign = clientSign;
    }

    /**
     * @return The engineerSign
     */
    public String getEngineerSign() {
        return engineerSign;
    }

    /**
     * @param engineerSign The engineer_sign
     */
    public void setEngineerSign(String engineerSign) {
        this.engineerSign = engineerSign;
    }

}
