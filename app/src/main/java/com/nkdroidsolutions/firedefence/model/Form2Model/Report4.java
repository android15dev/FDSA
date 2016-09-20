
package com.nkdroidsolutions.firedefence.model.Form2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.ChecksDoneProp;

public class Report4 {

    @SerializedName("checks_done")
    @Expose
    private String checksDone = "";
    @SerializedName("notes2")
    @Expose
    private String notes2 = "";

    private ChecksDoneProp checksDoneProp;

    public ChecksDoneProp getChecksDoneProp() {
        return checksDoneProp;
    }

    public void setChecksDoneProp(ChecksDoneProp checksDoneProp) {
        this.checksDoneProp = checksDoneProp;
    }

    /**
     * @return The checksDone
     */
    public String getChecksDone() {
        return checksDone;
    }

    /**
     * @param checksDone The checks_done
     */
    public void setChecksDone(String checksDone) {
        this.checksDone = checksDone;
    }

    /**
     * @return The notes2
     */
    public String getNotes2() {
        return notes2;
    }

    /**
     * @param notes2 The notes2
     */
    public void setNotes2(String notes2) {
        this.notes2 = notes2;
    }

}
