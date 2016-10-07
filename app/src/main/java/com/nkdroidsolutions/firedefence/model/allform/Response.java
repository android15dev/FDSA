package com.nkdroidsolutions.firedefence.model.allform;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @SerializedName("form")
    @Expose
    private List<Form> form = new ArrayList<Form>();
    @SerializedName("notes")
    @Expose
    private List<Note> notes = new ArrayList<Note>();

    /**
     * @return The form
     */
    public List<Form> getForm() {
        return form;
    }

    /**
     * @param form The form
     */
    public void setForm(List<Form> form) {
        this.form = form;
    }

    /**
     * @return The notes
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * @param notes The notes
     */
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

}

