
package com.nkdroidsolutions.firedefence.model.Form4Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Report3General {

    @SerializedName("general")
    @Expose
    private List<General> general = new ArrayList<General>(3);

    /**
     * @return The general
     */
    public List<General> getGeneral() {
        return general;
    }

    /**
     * @param general The general
     */
    public void setGeneral(List<General> general) {
        this.general = general;
    }

}
