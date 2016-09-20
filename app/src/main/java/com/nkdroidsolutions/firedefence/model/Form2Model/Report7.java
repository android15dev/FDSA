
package com.nkdroidsolutions.firedefence.model.Form2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report7 {

    @SerializedName("time_completed")
    @Expose
    private String timeCompleted = "";
    @SerializedName("traveltime")
    @Expose
    private String traveltime = "";
    @SerializedName("mileage")
    @Expose
    private String mileage = "";
    @SerializedName("work_completed_last")
    @Expose
    private String workCompletedLast = "";
    @SerializedName("email_address")
    @Expose
    private String emailAddress = "";

    /**
     * @return The timeCompleted
     */
    public String getTimeCompleted() {
        return timeCompleted;
    }

    /**
     * @param timeCompleted The time_completed
     */
    public void setTimeCompleted(String timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    /**
     * @return The traveltime
     */
    public String getTraveltime() {
        return traveltime;
    }

    /**
     * @param traveltime The traveltime
     */
    public void setTraveltime(String traveltime) {
        this.traveltime = traveltime;
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

    /**
     * @return The workCompletedLast
     */
    public String getWorkCompletedLast() {
        return workCompletedLast;
    }

    /**
     * @param workCompletedLast The work_completed_last
     */
    public void setWorkCompletedLast(String workCompletedLast) {
        this.workCompletedLast = workCompletedLast;
    }

    /**
     * @return The emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress The email_address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
