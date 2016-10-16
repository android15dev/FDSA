package com.nkdroidsolutions.firedefence.web_api;

import com.nkdroidsolutions.firedefence.model.Form1Model.FormOneProp;
import com.nkdroidsolutions.firedefence.model.Form2Model.FormTwoProp;
import com.nkdroidsolutions.firedefence.model.Form3Model.FormThreeProp;
import com.nkdroidsolutions.firedefence.model.Form4Model.FormFourProp;
import com.nkdroidsolutions.firedefence.model.allform.AllForm;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Sahil on 12-07-2016.
 */
public interface Fire_API {

    @FormUrlEncoded
    @POST("api.php")
    Call<AllForm> getAllForm(@Field("action") String action, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("api.php")
    Call<AllForm> getAllFormWithDate(@Field("action") String action, @Field("user_id") String user_id, @Field("date") String date);

    @FormUrlEncoded
    @POST("api.php")
    Call<FormOneProp> getFormOne(@Field("action") String action, @Field("form_id") String user_id, @Field("form") String form);

    @FormUrlEncoded
    @POST("api.php")
    Call<FormTwoProp> getFormTwo(@Field("action") String action, @Field("form_id") String user_id, @Field("form") String form);

    @FormUrlEncoded
    @POST("api.php")
    Call<FormThreeProp> getFormThree(@Field("action") String action, @Field("form_id") String user_id, @Field("form") String form);

    @FormUrlEncoded
    @POST("api.php")
    Call<FormFourProp> getFormFour(@Field("action") String action, @Field("form_id") String user_id, @Field("form") String form);


    @FormUrlEncoded
    @POST("api.php")
    Call<ResponseBody> updateFormOne(@Field("action") String action, @Field("formone_id") String formone_id, @Field("final_records") String final_records);

    @FormUrlEncoded
    @POST("api.php")
    Call<ResponseBody> updateFormTwo(@Field("action") String action, @Field("form_id") String formtwo_id, @Field("final_records") String final_records);

    @FormUrlEncoded
    @POST("api.php")
    Call<ResponseBody> updateFormThree(@Field("action") String action, @Field("form_id") String formthree_id, @Field("final_records") String final_records);

    @FormUrlEncoded
    @POST("api.php")
    Call<ResponseBody> updateFormFour(@Field("action") String action, @Field("formfour_id") String formfour_id, @Field("final_records") String final_records);


    @FormUrlEncoded
    @POST("api.php")
    Call<ResponseBody> addForm(@Field("action") String action, @Field("user_id") String user_id, @Field("final_records") String final_records);

}
