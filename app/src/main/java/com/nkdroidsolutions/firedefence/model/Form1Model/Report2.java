
package com.nkdroidsolutions.firedefence.model.Form1Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Report2 implements Serializable {

    @SerializedName("reportdate2")
    @Expose
    private String reportdate2 = "";
    @SerializedName("authorised_sign_name")
    @Expose
    private String authorisedSignName = "";
    @SerializedName("authorised_by")
    @Expose
    private String authorisedBy = "";
    @SerializedName("authorised_sign")
    @Expose
    private String authorisedSign = "";

    private Bitmap signBitmap;

    public Bitmap getSignBitmap() {
        return signBitmap;
    }

    public void setSignBitmap(Bitmap signBitmap) {
        this.signBitmap = signBitmap;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return reportdate2;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.reportdate2 = date;
    }

    /**
     * @return The authorisedSignName
     */
    public String getAuthorisedSignName() {
        return authorisedSignName;
    }

    /**
     * @param authorisedSignName The authorised_sign_name
     */
    public void setAuthorisedSignName(String authorisedSignName) {
        this.authorisedSignName = authorisedSignName;
    }

    /**
     * @return The authorisedBy
     */
    public String getAuthorisedBy() {
        return authorisedBy;
    }

    /**
     * @param authorisedBy The authorised_by
     */
    public void setAuthorisedBy(String authorisedBy) {
        this.authorisedBy = authorisedBy;
    }

    /**
     * @return The authorisedSign
     */
    public String getAuthorisedSign() {
        return authorisedSign;
    }

    /**
     * @param authorisedSign The authorised_sign
     */
    public void setAuthorisedSign(String authorisedSign) {
        this.authorisedSign = authorisedSign;
    }

}
