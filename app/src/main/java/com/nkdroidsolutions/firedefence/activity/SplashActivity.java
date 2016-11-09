package com.nkdroidsolutions.firedefence.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.util.ConnectionChecker;
import com.nkdroidsolutions.firedefence.util.PrefUtils;

import java.io.IOException;


public class SplashActivity extends AppCompatActivity {
    private GoogleCloudMessaging gcm;
    private String regid;
    private String PROJECT_NUMBER = "997528212325";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
                //nothing
            }

            public void onFinish() {
//                Intent i=new Intent(SplashActivity.this,MainActivity.class);
//                startActivity(i);
//                finish();

                goToLogin();
            }
        }.start();
    }

    private void goToLogin() {

        if (ConnectionChecker.getConnectionInfo(SplashActivity.this) == AppConstant.TYPE_NOT_CONNECTED) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    SplashActivity.this);

            // set title
            alertDialogBuilder.setTitle("No Internet");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Not able to connect to Internet! ")
                    .setCancelable(false)
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            dialog.dismiss();
                            goToLogin();
                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.dismiss();
                            SplashActivity.this.finish();
                        }
                    })
                    .setNeutralButton("Go Offline", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Database con = new Database(SplashActivity.this);
                            if (con.getSessionId().equals("-1")) {
                                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();

                            } else {
                                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            con.close();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        } else {
            getRegId();
        }

    }


    public void getRegId() {

        new AsyncTask<Void, Void, Void>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(SplashActivity.this);
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                    Log.e("GCM ID :", regid);
                    if (regid == null || regid == "") {
                        AlertDialog.Builder alert = new AlertDialog.Builder(SplashActivity.this);
                        alert.setTitle("Error");
                        alert.setMessage("Error of registering your device to server!");
                        alert.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getRegId();
                                dialog.dismiss();
                            }
                        });
                        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                SplashActivity.this.finish();
                            }
                        });

                        alert.show();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // move forward
                                PrefUtils.setNotificationId(regid + "", SplashActivity.this);
                                new CountDownTimer(2000, 1000) {

                                    public void onTick(long millisUntilFinished) {

                                    }

                                    public void onFinish() {

                                        Database con = new Database(SplashActivity.this);
                                        if (con.getSessionId().equals("-1")) {
                                            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                                            startActivity(i);
                                            finish();

                                        } else {
                                            Intent i = new Intent(SplashActivity.this, MainActivity.class);
                                            startActivity(i);
                                            finish();

                                        }
                                        con.close();

                                    }
                                }.start();

                            }
                        });
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.e("error", ex.toString());

                    Database con = new Database(SplashActivity.this);
                    if (con.getSessionId().equals("-1")) {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();

                    } else {
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                    con.close();

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

            }
        }.execute();
    } // end of getRegId


}
