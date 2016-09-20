
package com.nkdroidsolutions.firedefence.model.Form4Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report3 {

    @SerializedName("vehicle_checklist2")
    @Expose
    private String vehicle_checklist2="";

    private Bitmap bitmap1;
    private Bitmap bitmap2;

    public Bitmap getBitmap1() {
        return bitmap1;
    }

    public void setBitmap1(Bitmap bitmap1) {
        this.bitmap1 = bitmap1;
    }

    public Bitmap getBitmap2() {
        return bitmap2;
    }

    public void setBitmap2(Bitmap bitmap2) {
        this.bitmap2 = bitmap2;
    }

    /**
     * @return The vehicleChecklist
     */
    public String getVehicleChecklist() {
        return vehicle_checklist2;
    }

    /**
     * @param vehicleChecklist The vehicle_checklist
     */
    public void setVehicleChecklist(String vehicleChecklist) {
        this.vehicle_checklist2 = vehicleChecklist;
    }

}
