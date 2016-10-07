package com.nkdroidsolutions.firedefence.model.allform;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Form {

    @SerializedName("form_id")
    @Expose
    private String formId;
    @SerializedName("form_type")
    @Expose
    private String formType;
    @SerializedName("job_no")
    @Expose
    private String jobNo;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("notes")
    @Expose
    private String notes;

    /**
     * @return The formId
     */
    public String getFormId() {
        return formId;
    }

    /**
     * @param formId The form_id
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     * @return The formType
     */
    public String getFormType() {
        return formType;
    }

    /**
     * @param formType The form_type
     */
    public void setFormType(String formType) {
        this.formType = formType;
    }

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

}