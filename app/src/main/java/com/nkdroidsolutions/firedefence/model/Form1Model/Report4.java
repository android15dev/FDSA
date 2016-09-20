
package com.nkdroidsolutions.firedefence.model.Form1Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Report4 implements Serializable {

    @SerializedName("sys_state_on_arrival")
    @Expose
    private String sysStateOnArrival="";
    @SerializedName("set_no")
    @Expose
    private String setNo="";
    @SerializedName("pressure_test")
    @Expose
    private String pressureTest="";
    @SerializedName("bar1")
    @Expose
    private String bar1="";
    @SerializedName("pt_res")
    @Expose
    private String ptRes="";
    @SerializedName("bar2")
    @Expose
    private String bar2="";
    @SerializedName("hours")
    @Expose
    private String hours="";
    @SerializedName("notes2")
    @Expose
    private String notes2="";

    /**
     * @return The sysStateOnArrival
     */
    public String getSysStateOnArrival() {
        return sysStateOnArrival;
    }

    /**
     * @param sysStateOnArrival The sys_state_on_arrival
     */
    public void setSysStateOnArrival(String sysStateOnArrival) {
        this.sysStateOnArrival = sysStateOnArrival;
    }

    /**
     * @return The setNo
     */
    public String getSetNo() {
        return setNo;
    }

    /**
     * @param setNo The set_no
     */
    public void setSetNo(String setNo) {
        this.setNo = setNo;
    }

    /**
     * @return The pressureTest
     */
    public String getPressureTest() {
        return pressureTest;
    }

    /**
     * @param pressureTest The pressure_test
     */
    public void setPressureTest(String pressureTest) {
        this.pressureTest = pressureTest;
    }

    /**
     * @return The bar1
     */
    public String getBar1() {
        return bar1;
    }

    /**
     * @param bar1 The bar1
     */
    public void setBar1(String bar1) {
        this.bar1 = bar1;
    }

    /**
     * @return The ptRes
     */
    public String getPtRes() {
        return ptRes;
    }

    /**
     * @param ptRes The pt_res
     */
    public void setPtRes(String ptRes) {
        this.ptRes = ptRes;
    }

    /**
     * @return The bar2
     */
    public String getBar2() {
        return bar2;
    }

    /**
     * @param bar2 The bar2
     */
    public void setBar2(String bar2) {
        this.bar2 = bar2;
    }

    /**
     * @return The hours
     */
    public String getHours() {
        return hours;
    }

    /**
     * @param hours The hours
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * @return The notes
     */
    public String getNotes() {
        return notes2;
    }

    /**
     * @param notes The notes
     */
    public void setNotes(String notes) {
        this.notes2 = notes;
    }

}
