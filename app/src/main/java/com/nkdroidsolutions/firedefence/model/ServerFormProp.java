package com.nkdroidsolutions.firedefence.model;

/**
 * Created by Sahil on 03-09-2016.
 */
public class ServerFormProp {

    int id;
    String form_type = "";
    String form_user_id = "";
    String form_isnew = "";
    String form_action = "";
    String form_data = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForm_type() {
        return form_type;
    }

    public void setForm_type(String form_type) {
        this.form_type = form_type;
    }

    public String getForm_user_id() {
        return form_user_id;
    }

    public void setForm_user_id(String form_user_id) {
        this.form_user_id = form_user_id;
    }

    public String getForm_isnew() {
        return form_isnew;
    }

    public void setForm_isnew(String form_isnew) {
        this.form_isnew = form_isnew;
    }

    public String getForm_action() {
        return form_action;
    }

    public void setForm_action(String form_action) {
        this.form_action = form_action;
    }

    public String getForm_data() {
        return form_data;
    }

    public void setForm_data(String form_data) {
        this.form_data = form_data;
    }
}
