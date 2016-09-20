
package com.nkdroidsolutions.firedefence.model.Form4Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("report1")
    @Expose
    private Report1 report1;
    @SerializedName("report2")
    @Expose
    private Report2 report2;
    @SerializedName("report3")
    @Expose
    private Report3 report3;
    @SerializedName("report4")
    @Expose
    private Report4 report4;
    @SerializedName("report5")
    @Expose
    private Report5 report5;

    /**
     * @return The report1
     */
    public Report1 getReport1() {
        return report1;
    }

    /**
     * @param report1 The report1
     */
    public void setReport1(Report1 report1) {
        this.report1 = report1;
    }

    /**
     * @return The report2
     */
    public Report2 getReport2() {
        return report2;
    }

    /**
     * @param report2 The report2
     */
    public void setReport2(Report2 report2) {
        this.report2 = report2;
    }

    /**
     * @return The report3
     */
    public Report3 getReport3() {
        return report3;
    }

    /**
     * @param report3 The report3
     */
    public void setReport3(Report3 report3) {
        this.report3 = report3;
    }

    /**
     * @return The report4
     */
    public Report4 getReport4() {
        return report4;
    }

    /**
     * @param report4 The report4
     */
    public void setReport4(Report4 report4) {
        this.report4 = report4;
    }

    /**
     * @return The report5
     */
    public Report5 getReport5() {
        return report5;
    }

    /**
     * @param report5 The report5
     */
    public void setReport5(Report5 report5) {
        this.report5 = report5;
    }

}
