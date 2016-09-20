package com.nkdroidsolutions.firedefence.model.Form4Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class General {

    @SerializedName("general_mechanical")
    @Expose
    private String generalMechanical = "0";
    @SerializedName("general_mechanical_comment")
    @Expose
    private String generalMechanicalComment = "";
    @SerializedName("general_body_work")
    @Expose
    private String generalBodyWork = "0";
    @SerializedName("general_body_work_comment")
    @Expose
    private String generalBodyWorkComment = "";
    @SerializedName("general_body_work_images")
    @Expose
    private String generalBodyWorkImages = "";

    private Bitmap generalBodyWorkBitmap;

    @SerializedName("condition_interior_work")
    @Expose
    private String conditionInteriorWork = "0";
    @SerializedName("condition_interior_comment")
    @Expose
    private String conditionInteriorComment = "";
    @SerializedName("condition_interior_images")
    @Expose
    private String conditionInteriorImages = "";

    private Bitmap conditionInteriorBitmap;

    public Bitmap getGeneralBodyWorkBitmap() {
        return generalBodyWorkBitmap;
    }

    public void setGeneralBodyWorkBitmap(Bitmap generalBodyWorkBitmap) {
        this.generalBodyWorkBitmap = generalBodyWorkBitmap;
    }

    public Bitmap getConditionInteriorBitmap() {
        return conditionInteriorBitmap;
    }

    public void setConditionInteriorBitmap(Bitmap conditionInteriorBitmap) {
        this.conditionInteriorBitmap = conditionInteriorBitmap;
    }

    /**
     * @return The generalMechanical
     */
    public String getGeneralMechanical() {
        return generalMechanical;
    }

    /**
     * @param generalMechanical The general_mechanical
     */
    public void setGeneralMechanical(String generalMechanical) {
        this.generalMechanical = generalMechanical;
    }

    /**
     * @return The generalMechanicalComment
     */
    public String getGeneralMechanicalComment() {
        return generalMechanicalComment;
    }

    /**
     * @param generalMechanicalComment The general_mechanical_comment
     */
    public void setGeneralMechanicalComment(String generalMechanicalComment) {
        this.generalMechanicalComment = generalMechanicalComment;
    }

    /**
     * @return The generalBodyWork
     */
    public String getGeneralBodyWork() {
        return generalBodyWork;
    }

    /**
     * @param generalBodyWork The general_body_work
     */
    public void setGeneralBodyWork(String generalBodyWork) {
        this.generalBodyWork = generalBodyWork;
    }

    /**
     * @return The generalBodyWorkComment
     */
    public String getGeneralBodyWorkComment() {
        return generalBodyWorkComment;
    }

    /**
     * @param generalBodyWorkComment The general_body_work_comment
     */
    public void setGeneralBodyWorkComment(String generalBodyWorkComment) {
        this.generalBodyWorkComment = generalBodyWorkComment;
    }

    /**
     * @return The generalBodyWorkImages
     */
    public String getGeneralBodyWorkImages() {
        return generalBodyWorkImages;
    }

    /**
     * @param generalBodyWorkImages The general_body_work_images
     */
    public void setGeneralBodyWorkImages(String generalBodyWorkImages) {
        this.generalBodyWorkImages = generalBodyWorkImages;
    }

    /**
     * @return The conditionInteriorWork
     */
    public String getConditionInteriorWork() {
        return conditionInteriorWork;
    }

    /**
     * @param conditionInteriorWork The condition_interior_work
     */
    public void setConditionInteriorWork(String conditionInteriorWork) {
        this.conditionInteriorWork = conditionInteriorWork;
    }

    /**
     * @return The conditionInteriorComment
     */
    public String getConditionInteriorComment() {
        return conditionInteriorComment;
    }

    /**
     * @param conditionInteriorComment The condition_interior_comment
     */
    public void setConditionInteriorComment(String conditionInteriorComment) {
        this.conditionInteriorComment = conditionInteriorComment;
    }

    /**
     * @return The conditionInteriorImages
     */
    public String getConditionInteriorImages() {
        return conditionInteriorImages;
    }

    /**
     * @param conditionInteriorImages The condition_interior_images
     */
    public void setConditionInteriorImages(String conditionInteriorImages) {
        this.conditionInteriorImages = conditionInteriorImages;
    }

}