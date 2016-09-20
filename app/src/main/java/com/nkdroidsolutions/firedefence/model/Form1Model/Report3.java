
package com.nkdroidsolutions.firedefence.model.Form1Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Report3 implements Serializable {

    @SerializedName("types_of_systems")
    @Expose
    private String typesOfSystems = "";
    @SerializedName("other_system_type")
    @Expose
    private String otherSystemType = "";
    @SerializedName("reason_for_visit")
    @Expose
    private String reasonForVisit = "";
    @SerializedName("other_reson_to_visit")
    @Expose
    private String otherResonToVisit = "";

    /**
     * @return The typesOfSystems
     */
    public String getTypesOfSystems() {
        return typesOfSystems;
    }

    /**
     * @param typesOfSystems The types_of_systems
     */
    public void setTypesOfSystems(String typesOfSystems) {
        this.typesOfSystems = typesOfSystems;
    }

    /**
     * @return The otherSystemType
     */
    public String getOtherSystemType() {
        return otherSystemType;
    }

    /**
     * @param otherSystemType The other_system_type
     */
    public void setOtherSystemType(String otherSystemType) {
        this.otherSystemType = otherSystemType;
    }

    /**
     * @return The reasonForVisit
     */
    public String getReasonForVisit() {
        return reasonForVisit;
    }

    /**
     * @param reasonForVisit The reason_for_visit
     */
    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    /**
     * @return The otherResonToVisit
     */
    public String getOtherResonToVisit() {
        return otherResonToVisit;
    }

    /**
     * @param otherResonToVisit The other_reson_to_visit
     */
    public void setOtherResonToVisit(String otherResonToVisit) {
        this.otherResonToVisit = otherResonToVisit;
    }

}
