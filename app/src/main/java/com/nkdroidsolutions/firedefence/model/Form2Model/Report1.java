
package com.nkdroidsolutions.firedefence.model.Form2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report1 {

    @SerializedName("job_no")
    @Expose
    private String jobNo = "";
    @SerializedName("visit_no")
    @Expose
    private String visitNo = "";
    @SerializedName("reportdate1")
    @Expose
    private String reportdate1 = "";
    @SerializedName("arrived")
    @Expose
    private String arrived = "";
    @SerializedName("client_name")
    @Expose
    private String clientName = "";
    @SerializedName("service_report_no")
    @Expose
    private String serviceReportNo = "";
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
     * @return The visitNo
     */
    public String getVisitNo() {
        return visitNo;
    }

    /**
     * @param visitNo The visit_no
     */
    public void setVisitNo(String visitNo) {
        this.visitNo = visitNo;
    }

    /**
     * @return The reportdate1
     */
    public String getReportdate1() {
        return reportdate1;
    }

    /**
     * @param reportdate1 The reportdate1
     */
    public void setReportdate1(String reportdate1) {
        this.reportdate1 = reportdate1;
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
        return clientName;
    }

    /**
     * @param clientName The client_name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return The serviceReportNo
     */
    public String getServiceReportNo() {
        return serviceReportNo;
    }

    /**
     * @param serviceReportNo The service_report_no
     */
    public void setServiceReportNo(String serviceReportNo) {
        this.serviceReportNo = serviceReportNo;
    }

    /**
     * @return The notes1
     */
    public String getNotes1() {
        return notes1;
    }

    /**
     * @param notes1 The notes1
     */
    public void setNotes1(String notes1) {
        this.notes1 = notes1;
    }

}
