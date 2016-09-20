
package com.nkdroidsolutions.firedefence.model.Form2Model.pumps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PumpDatum {

    @SerializedName("diesel")
    @Expose
    private String diesel = "0";
    @SerializedName("electric")
    @Expose
    private String electric = "0";
    @SerializedName("pump_no")
    @Expose
    private String pumpNo = "";
    @SerializedName("pump_start_reading")
    @Expose
    private String pumpStartReading = "";
    @SerializedName("pump_stop_reading")
    @Expose
    private String pumpStopReading = "";
    @SerializedName("pump_make")
    @Expose
    private String pumpMake = "";
    @SerializedName("pump_Model")
    @Expose
    private String pumpModel = "";
    @SerializedName("pump_Dia")
    @Expose
    private String pumpDia = "";
    @SerializedName("pump_age")
    @Expose
    private String pumpAge = "";
    @SerializedName("motor_make")
    @Expose
    private String motorMake = "";
    @SerializedName("motor_Model")
    @Expose
    private String motorModel = "";
    @SerializedName("motor_Dia")
    @Expose
    private String motorDia = "";
    @SerializedName("motor_age")
    @Expose
    private String motorAge = "";
    @SerializedName("motor_notes")
    @Expose
    private String motorNotes = "";
    @SerializedName("point1_flow")
    @Expose
    private String point1Flow = "";
    @SerializedName("point1_pressure")
    @Expose
    private String point1Pressure = "";
    @SerializedName("point1_diesel")
    @Expose
    private String point1Diesel = "";
    @SerializedName("point1_electric")
    @Expose
    private String point1Electric = "";
    @SerializedName("point2_flow")
    @Expose
    private String point2Flow = "";
    @SerializedName("point2_pressure")
    @Expose
    private String point2Pressure = "";
    @SerializedName("point2_diesel")
    @Expose
    private String point2Diesel = "";
    @SerializedName("point2_electric")
    @Expose
    private String point2Electric = "";
    @SerializedName("point3_flow")
    @Expose
    private String point3Flow = "";
    @SerializedName("point3_pressure")
    @Expose
    private String point3Pressure = "";
    @SerializedName("point3_diesel")
    @Expose
    private String point3Diesel = "";
    @SerializedName("point3_electric")
    @Expose
    private String point3Electric = "";
    @SerializedName("has_pump")
    @Expose
    private String hasPump = "0";
    @SerializedName("low_make_pump")
    @Expose
    private String lowMakePump = "";
    @SerializedName("low_model_pump")
    @Expose
    private String lowModelPump = "";
    @SerializedName("low_dateinstalled_pump")
    @Expose
    private String lowDateinstalledPump = "";

    /**
     * @return The diesel
     */
    public String getDiesel() {
        return diesel;
    }

    /**
     * @param diesel The diesel
     */
    public void setDiesel(String diesel) {
        this.diesel = diesel;
    }

    /**
     * @return The electric
     */
    public String getElectric() {
        return electric;
    }

    /**
     * @param electric The electric
     */
    public void setElectric(String electric) {
        this.electric = electric;
    }

    /**
     * @return The pumpNo
     */
    public String getPumpNo() {
        return pumpNo;
    }

    /**
     * @param pumpNo The pump_no
     */
    public void setPumpNo(String pumpNo) {
        this.pumpNo = pumpNo;
    }

    /**
     * @return The pumpStartReading
     */
    public String getPumpStartReading() {
        return pumpStartReading;
    }

    /**
     * @param pumpStartReading The pump_start_reading
     */
    public void setPumpStartReading(String pumpStartReading) {
        this.pumpStartReading = pumpStartReading;
    }

    /**
     * @return The pumpStopReading
     */
    public String getPumpStopReading() {
        return pumpStopReading;
    }

    /**
     * @param pumpStopReading The pump_stop_reading
     */
    public void setPumpStopReading(String pumpStopReading) {
        this.pumpStopReading = pumpStopReading;
    }

    /**
     * @return The pumpMake
     */
    public String getPumpMake() {
        return pumpMake;
    }

    /**
     * @param pumpMake The pump_make
     */
    public void setPumpMake(String pumpMake) {
        this.pumpMake = pumpMake;
    }

    /**
     * @return The pumpModel
     */
    public String getPumpModel() {
        return pumpModel;
    }

    /**
     * @param pumpModel The pump_Model
     */
    public void setPumpModel(String pumpModel) {
        this.pumpModel = pumpModel;
    }

    /**
     * @return The pumpDia
     */
    public String getPumpDia() {
        return pumpDia;
    }

    /**
     * @param pumpDia The pump_Dia
     */
    public void setPumpDia(String pumpDia) {
        this.pumpDia = pumpDia;
    }

    /**
     * @return The pumpAge
     */
    public String getPumpAge() {
        return pumpAge;
    }

    /**
     * @param pumpAge The pump_age
     */
    public void setPumpAge(String pumpAge) {
        this.pumpAge = pumpAge;
    }

    /**
     * @return The motorMake
     */
    public String getMotorMake() {
        return motorMake;
    }

    /**
     * @param motorMake The motor_make
     */
    public void setMotorMake(String motorMake) {
        this.motorMake = motorMake;
    }

    /**
     * @return The motorModel
     */
    public String getMotorModel() {
        return motorModel;
    }

    /**
     * @param motorModel The motor_Model
     */
    public void setMotorModel(String motorModel) {
        this.motorModel = motorModel;
    }

    /**
     * @return The motorDia
     */
    public String getMotorDia() {
        return motorDia;
    }

    /**
     * @param motorDia The motor_Dia
     */
    public void setMotorDia(String motorDia) {
        this.motorDia = motorDia;
    }

    /**
     * @return The motorAge
     */
    public String getMotorAge() {
        return motorAge;
    }

    /**
     * @param motorAge The motor_age
     */
    public void setMotorAge(String motorAge) {
        this.motorAge = motorAge;
    }

    /**
     * @return The motorNotes
     */
    public String getMotorNotes() {
        return motorNotes;
    }

    /**
     * @param motorNotes The motor_notes
     */
    public void setMotorNotes(String motorNotes) {
        this.motorNotes = motorNotes;
    }

    /**
     * @return The point1Flow
     */
    public String getPoint1Flow() {
        return point1Flow;
    }

    /**
     * @param point1Flow The point1_flow
     */
    public void setPoint1Flow(String point1Flow) {
        this.point1Flow = point1Flow;
    }

    /**
     * @return The point1Pressure
     */
    public String getPoint1Pressure() {
        return point1Pressure;
    }

    /**
     * @param point1Pressure The point1_pressure
     */
    public void setPoint1Pressure(String point1Pressure) {
        this.point1Pressure = point1Pressure;
    }

    /**
     * @return The point1Diesel
     */
    public String getPoint1Diesel() {
        return point1Diesel;
    }

    /**
     * @param point1Diesel The point1_diesel
     */
    public void setPoint1Diesel(String point1Diesel) {
        this.point1Diesel = point1Diesel;
    }

    /**
     * @return The point1Electric
     */
    public String getPoint1Electric() {
        return point1Electric;
    }

    /**
     * @param point1Electric The point1_electric
     */
    public void setPoint1Electric(String point1Electric) {
        this.point1Electric = point1Electric;
    }

    /**
     * @return The point2Flow
     */
    public String getPoint2Flow() {
        return point2Flow;
    }

    /**
     * @param point2Flow The point2_flow
     */
    public void setPoint2Flow(String point2Flow) {
        this.point2Flow = point2Flow;
    }

    /**
     * @return The point2Pressure
     */
    public String getPoint2Pressure() {
        return point2Pressure;
    }

    /**
     * @param point2Pressure The point2_pressure
     */
    public void setPoint2Pressure(String point2Pressure) {
        this.point2Pressure = point2Pressure;
    }

    /**
     * @return The point2Diesel
     */
    public String getPoint2Diesel() {
        return point2Diesel;
    }

    /**
     * @param point2Diesel The point2_diesel
     */
    public void setPoint2Diesel(String point2Diesel) {
        this.point2Diesel = point2Diesel;
    }

    /**
     * @return The point2Electric
     */
    public String getPoint2Electric() {
        return point2Electric;
    }

    /**
     * @param point2Electric The point2_electric
     */
    public void setPoint2Electric(String point2Electric) {
        this.point2Electric = point2Electric;
    }

    /**
     * @return The point3Flow
     */
    public String getPoint3Flow() {
        return point3Flow;
    }

    /**
     * @param point3Flow The point3_flow
     */
    public void setPoint3Flow(String point3Flow) {
        this.point3Flow = point3Flow;
    }

    /**
     * @return The point3Pressure
     */
    public String getPoint3Pressure() {
        return point3Pressure;
    }

    /**
     * @param point3Pressure The point3_pressure
     */
    public void setPoint3Pressure(String point3Pressure) {
        this.point3Pressure = point3Pressure;
    }

    /**
     * @return The point3Diesel
     */
    public String getPoint3Diesel() {
        return point3Diesel;
    }

    /**
     * @param point3Diesel The point3_diesel
     */
    public void setPoint3Diesel(String point3Diesel) {
        this.point3Diesel = point3Diesel;
    }

    /**
     * @return The point3Electric
     */
    public String getPoint3Electric() {
        return point3Electric;
    }

    /**
     * @param point3Electric The point3_electric
     */
    public void setPoint3Electric(String point3Electric) {
        this.point3Electric = point3Electric;
    }

    /**
     * @return The hasPump
     */
    public String getHasPump() {
        return hasPump;
    }

    /**
     * @param hasPump The has_pump
     */
    public void setHasPump(String hasPump) {
        this.hasPump = hasPump;
    }

    /**
     * @return The lowMakePump
     */
    public String getLowMakePump() {
        return lowMakePump;
    }

    /**
     * @param lowMakePump The low_make_pump
     */
    public void setLowMakePump(String lowMakePump) {
        this.lowMakePump = lowMakePump;
    }

    /**
     * @return The lowModelPump
     */
    public String getLowModelPump() {
        return lowModelPump;
    }

    /**
     * @param lowModelPump The low_model_pump
     */
    public void setLowModelPump(String lowModelPump) {
        this.lowModelPump = lowModelPump;
    }

    /**
     * @return The lowDateinstalledPump
     */
    public String getLowDateinstalledPump() {
        return lowDateinstalledPump;
    }

    /**
     * @param lowDateinstalledPump The low_dateinstalled_pump
     */
    public void setLowDateinstalledPump(String lowDateinstalledPump) {
        this.lowDateinstalledPump = lowDateinstalledPump;
    }

}
