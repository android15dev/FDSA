
package com.nkdroidsolutions.firedefence.model.Form2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nkdroidsolutions.firedefence.model.Form2Model.pumps.PumpDataProp;
import com.nkdroidsolutions.firedefence.model.Form2Model.valves.ValveDataProp;

public class Report5 {

    @SerializedName("pump_data")
    @Expose
    private String pumpData = "";
    @SerializedName("valve_data")
    @Expose
    private String valveData = "";

    private PumpDataProp pumpDataProp;
    private ValveDataProp valveDataProp;

    public PumpDataProp getPumpDataProp() {
        return pumpDataProp;
    }

    public void setPumpDataProp(PumpDataProp pumpDataProp) {
        this.pumpDataProp = pumpDataProp;
    }

    public ValveDataProp getValveDataProp() {
        return valveDataProp;
    }

    public void setValveDataProp(ValveDataProp valveDataProp) {
        this.valveDataProp = valveDataProp;
    }

    /**
     * @return The pumpData
     */
    public String getPumpData() {
        return pumpData;
    }

    /**
     * @param pumpData The pump_data
     */
    public void setPumpData(String pumpData) {
        this.pumpData = pumpData;
    }

    /**
     * @return The valveData
     */
    public String getValveData() {
        return valveData;
    }

    /**
     * @param valveData The valve_data
     */
    public void setValveData(String valveData) {
        this.valveData = valveData;
    }

}
