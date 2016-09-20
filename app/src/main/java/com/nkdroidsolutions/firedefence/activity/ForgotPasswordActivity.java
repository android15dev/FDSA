package com.nkdroidsolutions.firedefence.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.GsonBuilder;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.User;
import com.nkdroidsolutions.firedefence.util.ApiPost;
import com.nkdroidsolutions.firedefence.util.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordActivity extends AppCompatActivity {

    private LinearLayout forgotSubmit;
    private EditText forgotEmail;
    private Toolbar toolbar;
    private String responseObject;
    private ProgressDialog progressDialog;
    private LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setToolbar();
        rootLayout= (LinearLayout) findViewById(R.id.rootLayout);
        forgotEmail= (EditText) findViewById(R.id.forgotEmail);
        forgotSubmit= (LinearLayout) findViewById(R.id.forgotSubmit);
        forgotSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callForgotService();
            }
        });
    }

    private void setToolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Forgot Password");
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    private void callForgotService() {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog=new ProgressDialog(ForgotPasswordActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                JSONObject jsonObject=new JSONObject();

                try {
                    jsonObject.put("email",forgotEmail.getText().toString().trim()+"");

                    Log.e("request object", jsonObject + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                responseObject= ApiPost.callPostAPI(AppConstant.FORGOT_PASSWORD, jsonObject);
                Log.e("response", responseObject + " ");


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
//                User user=new GsonBuilder().create().fromJson(responseObject, User.class);
//                if(user.responseId.equalsIgnoreCase("1")){
//                    PrefUtils.setCurrentUser(user, ForgotPasswordActivity.this);
//                    Intent intent=new Intent(ForgotPasswordActivity.this,MainActivity.class);
//                    startActivity(intent);
//
//                } else {
//                    Snackbar.make(rootLayout, user.responseMessage + "", Snackbar.LENGTH_LONG).show();
//                }

            }
        }.execute();
    }
}
