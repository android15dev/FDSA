package com.nkdroidsolutions.firedefence.model.Form4Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDefect {

    @SerializedName("defects")
    @Expose
    private String defects = "";
    @SerializedName("preffer_date")
    @Expose
    private String prefferDate = "";
    @SerializedName("repair_deadline")
    @Expose
    private String repairDeadline = "";
    @SerializedName("importance")
    @Expose
    private String importance = "";
    @SerializedName("defect_image")
    @Expose
    private String defectImage = "";

    private Bitmap defectBitmap;

    public Bitmap getDefectBitmap() {
        return defectBitmap;
    }

    public void setDefectBitmap(Bitmap defectBitmap) {
        this.defectBitmap = defectBitmap;
    }

    /**
     * @return The defects
     */
    public String getDefects() {
        return defects;
    }

    /**
     * @param defects The defects
     */
    public void setDefects(String defects) {
        this.defects = defects;
    }

    /**
     * @return The prefferDate
     */
    public String getPrefferDate() {
        return prefferDate;
    }

    /**
     * @param prefferDate The preffer_date
     */
    public void setPrefferDate(String prefferDate) {
        this.prefferDate = prefferDate;
    }

    /**
     * @return The repairDeadline
     */
    public String getRepairDeadline() {
        return repairDeadline;
    }

    /**
     * @param repairDeadline The repair_deadline
     */
    public void setRepairDeadline(String repairDeadline) {
        this.repairDeadline = repairDeadline;
    }

    /**
     * @return The importance
     */
    public String getImportance() {
        return importance;
    }

    /**
     * @param importance The importance
     */
    public void setImportance(String importance) {
        this.importance = importance;
    }

    /**
     * @return The defectImage
     */
    public String getDefectImage() {
        return defectImage;
    }

    /**
     * @param defectImage The defect_image
     */
    public void setDefectImage(String defectImage) {
        this.defectImage = defectImage;
    }

}