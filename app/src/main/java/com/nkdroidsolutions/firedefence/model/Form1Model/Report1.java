
package com.nkdroidsolutions.firedefence.model.Form1Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Report1 implements Serializable {

    @SerializedName("job_no")
    @Expose
    private String jobNo = "";
    @SerializedName("date")
    @Expose
    private String date = "";
    @SerializedName("arrived")
    @Expose
    private String arrived = "";
    @SerializedName("client_name1")
    @Expose
    private String client_name1 = "";
    @SerializedName("notes1")
    @Expose
    private String notes1 = "";

    /**
     * @return The jobNo
     */
    public String getJobNo() {
        return jobNo;
    }

    /**
     * @param jobNo The job_no
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
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
     * @return The arrived
     */
    public String getArrived() {
        return arrived;
    }

    /**
     * @param arrived The arrived
     */
    public void setArrived(String arrived) {
        this.arrived = arrived;
    }

    /**
     * @return The clientName
     */
    public String getClientName() {
        return client_name1;
    }

    /**
     * @param clientName The client_name
     */
    public void setClientName(String clientName) {
        this.client_name1 = clientName;
    }

    /**
     * @return The notes
     */
    public String getNotes() {
        return notes1;
    }

    /**
     * @param notes The notes
     */
    public void setNotes(String notes) {
        this.notes1 = notes;
    }

}
