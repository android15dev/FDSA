
package com.nkdroidsolutions.firedefence.model.Form3Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ExtinCheckProp {

    @SerializedName("add_data")
    @Expose
    private List<AddDatum> addData = new ArrayList<AddDatum>();

    /**
     * @return The addData
     */
    public List<AddDatum> getAddData() {
        return addData;
    }

    /**
     * @param addData The add_data
     */
    public void setAddData(List<AddDatum> addData) {
        this.addData = addData;
    }

}
