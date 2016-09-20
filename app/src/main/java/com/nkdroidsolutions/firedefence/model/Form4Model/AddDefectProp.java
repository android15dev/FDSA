package com.nkdroidsolutions.firedefence.model.Form4Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AddDefectProp {

    @SerializedName("add_defect")
    @Expose
    private List<AddDefect> addDefect = new ArrayList<AddDefect>();

    /**
     * @return The addDefect
     */
    public List<AddDefect> getAddDefect() {
        return addDefect;
    }

    /**
     * @param addDefect The add_defect
     */
    public void setAddDefect(List<AddDefect> addDefect) {
        this.addDefect = addDefect;
    }

}