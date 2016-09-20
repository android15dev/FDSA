package com.nkdroidsolutions.firedefence.model.allform;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllForm {

    @SerializedName("response")
    @Expose
    private List<Response> response = new ArrayList<Response>();

    /**
     * @return The response
     */
    public List<Response> getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(List<Response> response) {
        this.response = response;
    }

}