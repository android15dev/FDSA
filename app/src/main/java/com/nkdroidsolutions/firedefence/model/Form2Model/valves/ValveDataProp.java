
package com.nkdroidsolutions.firedefence.model.Form2Model.valves;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ValveDataProp {

    @SerializedName("valve_data")
    @Expose
    private List<ValveDatum> valveData = new ArrayList<ValveDatum>();

    /**
     * @return The valveData
     */
    public List<ValveDatum> getValveData() {
        return valveData;
    }

    /**
     * @param valveData The valve_data
     */
    public void setValveData(List<ValveDatum> valveData) {
        this.valveData = valveData;
    }

}
