package com.nkdroidsolutions.firedefence.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nirav on 11/08/15.
 */
public class User {

    @SerializedName("response_id")
    public String responseId;
    @SerializedName("response_message")
    public String responseMessage;
    @SerializedName("user_id")
    public String userId;
    @SerializedName("first_name")
    public String firstname;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("email")
    public String email;


}
