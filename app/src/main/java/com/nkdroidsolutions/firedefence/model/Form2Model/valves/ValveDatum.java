
package com.nkdroidsolutions.firedefence.model.Form2Model.valves;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValveDatum {

    @SerializedName("valve_number")
    @Expose
    private String valveNumber = "";
    @SerializedName("flow_valve")
    @Expose
    private String flowValve = "";
    @SerializedName("pressure_valve")
    @Expose
    private String pressureValve = "";
    @SerializedName("c_before")
    @Expose
    private String cBefore = "";
    @SerializedName("c_after")
    @Expose
    private String cAfter = "";
    @SerializedName("make_valve_pump")
    @Expose
    private String makeValvePump = "";
    @SerializedName("dia_valve")
    @Expose
    private String diaValve = "";
    @SerializedName("model_valve")
    @Expose
    private String modelValve = "";
    @SerializedName("age_valve")
    @Expose
    private String ageValve = "";
    @SerializedName("notes_valve")
    @Expose
    private String notesValve = "";
    @SerializedName("has_pump_valve")
    @Expose
    private String hasPumpValve = "0";
    @SerializedName("valve_image1")
    @Expose
    private String valveImage1 = "";
    @SerializedName("valve_image2")
    @Expose
    private String valveImage2 = "";
    @SerializedName("valve_image3")
    @Expose
    private String valveImage3 = "";
    @SerializedName("make_pump_valve")
    @Expose
    private String makePumpValve = "";
    @SerializedName("model_pump_valve")
    @Expose
    private String modelPumpValve = "";
    @SerializedName("dateinstalled_pump_valve")
    @Expose
    private String dateinstalledPumpValve = "";

    /**
     * @return The valveNumber
     */
    public String getValveNumber() {
        return valveNumber;
    }

    /**
     * @param valveNumber The valve_number
     */
    public void setValveNumber(String valveNumber) {
        this.valveNumber = valveNumber;
    }

    /**
     * @return The flowValve
     */
    public String getFlowValve() {
        return flowValve;
    }

    /**
     * @param flowValve The flow_valve
     */
    public void setFlowValve(String flowValve) {
        this.flowValve = flowValve;
    }

    /**
     * @return The pressureValve
     */
    public String getPressureValve() {
        return pressureValve;
    }

    /**
     * @param pressureValve The pressure_valve
     */
    public void setPressureValve(String pressureValve) {
        this.pressureValve = pressureValve;
    }

    /**
     * @return The cBefore
     */
    public String getCBefore() {
        return cBefore;
    }

    /**
     * @param cBefore The c_before
     */
    public void setCBefore(String cBefore) {
        this.cBefore = cBefore;
    }

    /**
     * @return The cAfter
     */
    public String getCAfter() {
        return cAfter;
    }

    /**
     * @param cAfter The c_after
     */
    public void setCAfter(String cAfter) {
        this.cAfter = cAfter;
    }

    /**
     * @return The makeValvePump
     */
    public String getMakeValvePump() {
        return makeValvePump;
    }

    /**
     * @param makeValvePump The make_valve_pump
     */
    public void setMakeValvePump(String makeValvePump) {
        this.makeValvePump = makeValvePump;
    }

    /**
     * @return The diaValve
     */
    public String getDiaValve() {
        return diaValve;
    }

    /**
     * @param diaValve The dia_valve
     */
    public void setDiaValve(String diaValve) {
        this.diaValve = diaValve;
    }

    /**
     * @return The modelValve
     */
    public String getModelValve() {
        return modelValve;
    }

    /**
     * @param modelValve The model_valve
     */
    public void setModelValve(String modelValve) {
        this.modelValve = modelValve;
    }

    /**
     * @return The ageValve
     */
    public String getAgeValve() {
        return ageValve;
    }

    /**
     * @param ageValve The age_valve
     */
    public void setAgeValve(String ageValve) {
        this.ageValve = ageValve;
    }

    /**
     * @return The notesValve
     */
    public String getNotesValve() {
        return notesValve;
    }

    /**
     * @param notesValve The notes_valve
     */
    public void setNotesValve(String notesValve) {
        this.notesValve = notesValve;
    }

    /**
     * @return The hasPumpValve
     */
    public String getHasPumpValve() {
        return hasPumpValve;
    }

    /**
     * @param hasPumpValve The has_pump_valve
     */
    public void setHasPumpValve(String hasPumpValve) {
        this.hasPumpValve = hasPumpValve;
    }

    /**
     * @return The valveImage1
     */
    public String getValveImage1() {
        return valveImage1;
    }

    /**
     * @param valveImage1 The valve_image1
     */
    public void setValveImage1(String valveImage1) {
        this.valveImage1 = valveImage1;
    }

    /**
     * @return The valveImage2
     */
    public String getValveImage2() {
        return valveImage2;
    }

    /**
     * @param valveImage2 The valve_image2
     */
    public void setValveImage2(String valveImage2) {
        this.valveImage2 = valveImage2;
    }

    /**
     * @return The valveImage3
     */
    public String getValveImage3() {
        return valveImage3;
    }

    /**
     * @param valveImage3 The valve_image3
     */
    public void setValveImage3(String valveImage3) {
        this.valveImage3 = valveImage3;
    }

    /**
     * @return The makePumpValve
     */
    public String getMakePumpValve() {
        return makePumpValve;
    }

    /**
     * @param makePumpValve The make_pump_valve
     */
    public void setMakePumpValve(String makePumpValve) {
        this.makePumpValve = makePumpValve;
    }

    /**
     * @return The modelPumpValve
     */
    public String getModelPumpValve() {
        return modelPumpValve;
    }

    /**
     * @param modelPumpValve The model_pump_valve
     */
    public void setModelPumpValve(String modelPumpValve) {
        this.modelPumpValve = modelPumpValve;
    }

    /**
     * @return The dateinstalledPumpValve
     */
    public String getDateinstalledPumpValve() {
        return dateinstalledPumpValve;
    }

    /**
     * @param dateinstalledPumpValve The dateinstalled_pump_valve
     */
    public void setDateinstalledPumpValve(String dateinstalledPumpValve) {
        this.dateinstalledPumpValve = dateinstalledPumpValve;
    }

}
