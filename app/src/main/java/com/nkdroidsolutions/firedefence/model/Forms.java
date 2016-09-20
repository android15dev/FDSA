package com.nkdroidsolutions.firedefence.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nirav on 17/08/15.
 */
public class Forms {

    @SerializedName("user_id")
    public String user_id;
    @SerializedName("response_id")
    public String response;
    @SerializedName("formone")
    public ArrayList<GetAssignedForm1> form1ArrayList;
}
