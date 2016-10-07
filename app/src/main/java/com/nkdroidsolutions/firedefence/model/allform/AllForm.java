package com.nkdroidsolutions.firedefence.model.allform;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllForm {

    @SerializedName("response")
    @Expose
    private Response response= new Response();

    /**
     * @return The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }


}