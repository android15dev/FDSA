package com.nkdroidsolutions.firedefence.model;

/**
 * Created by Sahil on 03-09-2016.
 */
public class LocalFormProp {

    int id;
    String form_id = "";
    String form_type = "";
    String form_data = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getForm_type() {
        return form_type;
    }

    public void setForm_type(String form_type) {
        this.form_type = form_type;
    }

    public String getForm_data() {
        return form_data;
    }

    public void setForm_data(String form_data) {
        this.form_data = form_data;
    }
}
