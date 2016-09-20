
package com.nkdroidsolutions.firedefence.model.Form2Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report6 {

    @SerializedName("date")
    @Expose
    private String date = "";
    @SerializedName("time")
    @Expose
    private String time = "";
    @SerializedName("client_name_work")
    @Expose
    private String clientNameWork = "";
    @SerializedName("work_completed")
    @Expose
    private String workCompleted = "";
    @SerializedName("back_online")
    @Expose
    private String backOnline = "";
    @SerializedName("notes")
    @Expose
    private String notes = "";
    @SerializedName("client_sign")
    @Expose
    private String clientSign = "";
    @SerializedName("engineer_sign")
    @Expose
    private String engineerSign = "";

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
     * @return The clientNameWork
     */
    public String getClientNameWork() {
        return clientNameWork;
    }

    /**
     * @param clientNameWork The client_name_work
     */
    public void setClientNameWork(String clientNameWork) {
        this.clientNameWork = clientNameWork;
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
        return notes;
    }

    /**
     * @param notes The notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
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
