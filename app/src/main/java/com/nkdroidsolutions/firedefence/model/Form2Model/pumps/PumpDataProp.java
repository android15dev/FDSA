
package com.nkdroidsolutions.firedefence.model.Form2Model.pumps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PumpDataProp {

    @SerializedName("pump_data")
    @Expose
    private List<PumpDatum> pumpData = new ArrayList<PumpDatum>();

    /**
     * @return The pumpData
     */
    public List<PumpDatum> getPumpData() {
        return pumpData;
    }

    /**
     * @param pumpData The pump_data
     */
    public void setPumpData(List<PumpDatum> pumpData) {
        this.pumpData = pumpData;
    }

}
