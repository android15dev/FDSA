
package com.nkdroidsolutions.firedefence.model.Form4Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report1 {

    @SerializedName("vehicle_reg")
    @Expose
    private String vehicleReg = "";
    @SerializedName("date")
    @Expose
    private String date = "";
    @SerializedName("driver_name")
    @Expose
    private String driverName = "";
    @SerializedName("odometer")
    @Expose
    private String odometer = "";
    @SerializedName("comments")
    @Expose
    private String comments = "";

    /**
     * @return The vehicleReg
     */
    public String getVehicleReg() {
        return vehicleReg;
    }

    /**
     * @param vehicleReg The vehicle_reg
     */
    public void setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
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
     * @return The driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * @param driverName The driver_name
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /**
     * @return The odometer
     */
    public String getOdometer() {
        return odometer;
    }

    /**
     * @param odometer The odometer
     */
    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    /**
     * @return The comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments The comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

}
