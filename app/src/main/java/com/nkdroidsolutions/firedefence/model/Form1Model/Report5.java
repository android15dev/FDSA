
package com.nkdroidsolutions.firedefence.model.Form1Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Report5 implements Serializable {

    @SerializedName("engineer_note")
    @Expose
    private String engineerNote="";
    @SerializedName("parts_used")
    @Expose
    private String partsUsed="";
    @SerializedName("image")
    @Expose
    private Image image;

    /**
     * @return The engineerNote
     */
    public String getEngineerNote() {
        return engineerNote;
    }

    /**
     * @param engineerNote The engineer_note
     */
    public void setEngineerNote(String engineerNote) {
        this.engineerNote = engineerNote;
    }

    /**
     * @return The partsUsed
     */
    public String getPartsUsed() {
        return partsUsed;
    }

    /**
     * @param partsUsed The parts_used
     */
    public void setPartsUsed(String partsUsed) {
        this.partsUsed = partsUsed;
    }

    /**
     * @return The image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(Image image) {
        this.image = image;
    }

}
