
package com.nkdroidsolutions.firedefence.model.Form4Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report2 {

    @SerializedName("vehicle_checklist")
    @Expose
    private String vehicleChecklist = "";

    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * @return The vehicleChecklist
     */
    public String getVehicleChecklist() {
        return vehicleChecklist;
    }

    /**
     * @param vehicleChecklist The vehicle_checklist
     */
    public void setVehicleChecklist(String vehicleChecklist) {
        this.vehicleChecklist = vehicleChecklist;
    }

}
