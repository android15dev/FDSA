
package com.nkdroidsolutions.firedefence.model.Form3Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report3 {

    @SerializedName("add_data")
    @Expose
    private String addData = "";

    private ExtinCheckProp extinCheckProp;


    public ExtinCheckProp getExtinCheckProp() {
        return extinCheckProp;
    }

    public void setExtinCheckProp(ExtinCheckProp extinCheckProp) {
        this.extinCheckProp = extinCheckProp;
    }

    /**
     * @return The addData
     */
    public String getAddData() {
        return addData;
    }

    /**
     * @param addData The add_data
     */
    public void setAddData(String addData) {
        this.addData = addData;
    }

}
