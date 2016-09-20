package com.nkdroidsolutions.firedefence.web_api;

import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form1Model.FormOneProp;
import com.nkdroidsolutions.firedefence.model.Form2Model.FormTwoProp;
import com.nkdroidsolutions.firedefence.model.Form3Model.FormThreeProp;
import com.nkdroidsolutions.firedefence.model.Form4Model.FormFourProp;
import com.nkdroidsolutions.firedefence.util.handlers.FormFourHandler;
import com.nkdroidsolutions.firedefence.util.handlers.FormOneHandler;
import com.nkdroidsolutions.firedefence.util.handlers.FormThreeHandler;
import com.nkdroidsolutions.firedefence.util.handlers.FormTwoHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sahil on 19-07-2016.
 */
public class WebHandling {

    private static Fire_API api;
    private static WebHandling webHandling;

    public WebHandling() {
        webHandling = this;
        api = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Fire_API.class);
    }

    public static WebHandling getInstance() {
        return webHandling;
    }

    public void getFormOneDetail(String actionGetformdetail, String formId, String formType, final FormOneHandler formOneHandler) {
        Call<FormOneProp> call = api.getFormOne(actionGetformdetail, formId, formType);

        call.enqueue(new Callback<FormOneProp>() {
            @Override
            public void onResponse(Call<FormOneProp> call, Response<FormOneProp> response) {
                formOneHandler.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<FormOneProp> call, Throwable t) {
                formOneHandler.onSuccess(null);
                t.printStackTrace();
            }
        });
    }

    public void getFormTwoDetail(String actionGetformdetail, String formId, String formType, final FormTwoHandler formTwoHandler) {
        Call<FormTwoProp> call = api.getFormTwo(actionGetformdetail, formId, formType);

        call.enqueue(new Callback<FormTwoProp>() {
            @Override
            public void onResponse(Call<FormTwoProp> call, Response<FormTwoProp> response) {
                formTwoHandler.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<FormTwoProp> call, Throwable t) {
                formTwoHandler.onSuccess(null);
                t.printStackTrace();
            }
        });
    }

    public void getFormThreeDetail(String actionGetformdetail, String formId, String formType, final FormThreeHandler formThreeHandler) {
        Call<FormThreeProp> call = api.getFormThree(actionGetformdetail, formId, formType);

        call.enqueue(new Callback<FormThreeProp>() {
            @Override
            public void onResponse(Call<FormThreeProp> call, Response<FormThreeProp> response) {
                formThreeHandler.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<FormThreeProp> call, Throwable t) {
                formThreeHandler.onSuccess(null);
                t.printStackTrace();
            }
        });
    }

    public void getFormFourDetail(String actionGetformdetail, String formId, String formType, final FormFourHandler formFourHandler) {
        Call<FormFourProp> call = api.getFormFour(actionGetformdetail, formId, formType);

        call.enqueue(new Callback<FormFourProp>() {
            @Override
            public void onResponse(Call<FormFourProp> call, Response<FormFourProp> response) {
                formFourHandler.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<FormFourProp> call, Throwable t) {
                formFourHandler.onSuccess(null);
                t.printStackTrace();
            }
        });
    }

}
