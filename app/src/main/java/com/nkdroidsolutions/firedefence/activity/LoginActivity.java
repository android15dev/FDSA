package com.nkdroidsolutions.firedefence.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.User;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.util.ApiPost;
import com.nkdroidsolutions.firedefence.util.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    private TextView hello, bypressing, tandc, forgotPassword;
    private LinearLayout continuebtn;
    private Typeface face;
    private LinearLayout rootLayout;
    ArrayList<String> countryCodeList;
    private ProgressDialog progressDialog;
    private EditText etEmail, etPassword;
    private Reader reader = null;
    private String responseObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hello = (TextView) findViewById(R.id.hello);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassord);
        continuebtn = (LinearLayout) findViewById(R.id.continuebtn);
        bypressing = (TextView) findViewById(R.id.bypressing);
        tandc = (TextView) findViewById(R.id.tandc);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        face = Typeface.createFromAsset(getAssets(), "helvetica.otf");
        hello.setTypeface(face);
        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);

        bypressing.setTypeface(face);
        tandc.setTypeface(face);
        forgotPassword.setTypeface(face);
        SpannableString content = new SpannableString(tandc.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, tandc.getText().toString().length(), 0);
        tandc.setText(content);

        SpannableString content1 = new SpannableString(forgotPassword.getText().toString());
        content1.setSpan(new UnderlineSpan(), 0, forgotPassword.getText().toString().length(), 0);
        forgotPassword.setText(content1);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmptyField(etEmail)) {
                    Snackbar.make(rootLayout, "Please enter email", Snackbar.LENGTH_LONG).show();
                } else if (isEmptyField(etPassword)) {
                    Snackbar.make(rootLayout, "Please enter password", Snackbar.LENGTH_LONG).show();
                } else {
                    callLoginService();

                }

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });


    }


    public boolean isEmptyField(EditText param1) {

        boolean isEmpty = false;
        if (param1.getText() == null || param1.getText().toString().equalsIgnoreCase("")) {
            isEmpty = true;
        }
        return isEmpty;
    }


    public boolean isEmailMatch(EditText param1) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(param1.getText().toString()).matches();
    }

    private void callLoginService() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("username", etEmail.getText().toString().trim() + "");
                    jsonObject.put("password", etPassword.getText().toString().trim() + "");
                    jsonObject.put("notification_id", PrefUtils.getNotificationId(LoginActivity.this));
                    Log.e("request object", jsonObject + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                reader= ApiPost.callWebservicePost(AppConstant.LOGIN, jsonObject.toString());
//                Log.e("reader", reader + "");
//
//
//                // Get Response in String
//                StringBuffer response=new StringBuffer();
//                int i = 0;
//                do {
//                    try {
//                        i = reader.read();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    char character = (char) i;
//                    response.append(character);
//
//                } while (i != -1);
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.e("response",response+" ");


                responseObject = ApiPost.callPostAPI(AppConstant.LOGIN, jsonObject);
                Log.e("response", responseObject + " ");


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();

                Log.d("cnjd", responseObject);
                try {
                    User user = new GsonBuilder().create().fromJson(responseObject, User.class);
                    if (user.responseId.equalsIgnoreCase("1")) {
                        PrefUtils.setCurrentUser(user, LoginActivity.this);
                        Database con = new Database(LoginActivity.this);
                        con.insertSessionId("1");
                        con.close();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Snackbar.make(rootLayout, user.responseMessage + "", Snackbar.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.execute();
    }


}
