
package com.nkdroidsolutions.firedefence.model.Form4Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report4 {

    @SerializedName("add_defect")
    @Expose
    private String addDefect = "";

    private AddDefectProp addDefectProp;

    public AddDefectProp getAddDefectProp() {
        return addDefectProp;
    }

    public void setAddDefectProp(AddDefectProp addDefectProp) {
        this.addDefectProp = addDefectProp;
    }

    /**
     * @return The addDefect
     */
    public String getAddDefect() {
        return addDefect;
    }

    /**
     * @param addDefect The add_defect
     */
    public void setAddDefect(String addDefect) {
        this.addDefect = addDefect;
    }

}
