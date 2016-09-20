package com.nkdroidsolutions.firedefence.web_api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.ServerFormProp;
import com.nkdroidsolutions.firedefence.storage.Database;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkStateReceiver extends BroadcastReceiver {
    public void onReceive(final Context context, Intent intent) {
        Log.d("app", "Network connectivity change");
        if (intent.getExtras() != null) {
            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                Log.i("app", "Network " + ni.getTypeName() + " connected");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        putOnServer(context);
                    }
                }, 5000);
            } else {
                Log.d("app", "There's no network connectivity");
            }
        }
        /*if (intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
            Log.d("app", "There's no network connectivity");
        }*/
    }

    public void putOnServer(final Context context) {
        final Database db = new Database(context);
        final ServerFormProp serverFormProp = db.getServerForm();
        if (serverFormProp != null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(false)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstant.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Fire_API fire_api = retrofit.create(Fire_API.class);
            Call<ResponseBody> call = null;

            if (serverFormProp.getForm_isnew().equals("true")) {
                call = fire_api.addForm(serverFormProp.getForm_action(), serverFormProp.getForm_user_id(), serverFormProp.getForm_data());
            } else {
                if (serverFormProp.getForm_type().equals("1")) {
                    call = fire_api.updateFormOne(serverFormProp.getForm_action(), serverFormProp.getForm_user_id(), serverFormProp.getForm_data());
                } else if (serverFormProp.getForm_type().equals("2")) {
                    call = fire_api.updateFormTwo(serverFormProp.getForm_action(), serverFormProp.getForm_user_id(), serverFormProp.getForm_data());
                } else if (serverFormProp.getForm_type().equals("3")) {
                    call = fire_api.updateFormThree(serverFormProp.getForm_action(), serverFormProp.getForm_user_id(), serverFormProp.getForm_data());
                } else if (serverFormProp.getForm_type().equals("4")) {
                    call = fire_api.updateFormFour(serverFormProp.getForm_action(), serverFormProp.getForm_user_id(), serverFormProp.getForm_data());
                }
            }

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                    try {
                        String res = new String(response.body().bytes());
                        if (new JSONObject(res).optJSONObject("response").optInt("form_id") > 0) {
                            if (!serverFormProp.getForm_isnew().equals("true")) {
                                db.removeLocalForm(serverFormProp.getForm_user_id());
                            }
                            db.removeServerForm(serverFormProp.getForm_user_id());
                        } else {
//                            FunctionUtils.getInstance().showToast("Please Try Again");
                        }

                        Log.d("sds", "sds:    " + new String(response.body().bytes()));

                        putOnServer(context);

                    } catch (Exception e) {
                        e.printStackTrace();
                        putOnServer(context);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    putOnServer(context);
                    t.printStackTrace();
                }
            });

        }
    }
}