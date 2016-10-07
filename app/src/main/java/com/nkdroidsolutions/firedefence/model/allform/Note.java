package com.nkdroidsolutions.firedefence.model.allform;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("notes")
    @Expose
    private String notes;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes The notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

}