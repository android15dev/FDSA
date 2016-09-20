
package com.nkdroidsolutions.firedefence.model.Form2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report3 {

    @SerializedName("types_of_systems")
    @Expose
    private String typesOfSystems = "";
    @SerializedName("system_notes")
    @Expose
    private String systemNotes = "";

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
     * @return The systemNotes
     */
    public String getSystemNotes() {
        return systemNotes;
    }

    /**
     * @param systemNotes The system_notes
     */
    public void setSystemNotes(String systemNotes) {
        this.systemNotes = systemNotes;
    }

}
