package com.nkdroidsolutions.firedefence.fragment.Form_3;


import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.DocumentException;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.FireForm3;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.LocalFormProp;
import com.nkdroidsolutions.firedefence.model.ServerFormProp;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.storage.Form3PDF;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.PrefUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Fire_API;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Form35 extends Fragment implements AllObserver, TextWatcher {

    private View convertView;
    private TextView txt_finish, txt_back;
    private EditText edit_date, edit_timeout, edit_mileage;
    private Database db;
    private EditText etEmailAddressForm17New;
    private Button btnSendForm17New;


    public static Form35 newInstance() {
        return new Form35();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form36, container, false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = new Database(getActivity());

        initUI();
        if (observerFormThree.getFormthree() != null) {
            edit_date.setText(observerFormThree.getFormthree().getResponse().getReport5().getDate2());
            edit_timeout.setText(observerFormThree.getFormthree().getResponse().getReport5().getTimeOut());
            edit_mileage.setText(observerFormThree.getFormthree().getResponse().getReport5().getMileage());

        }
        listners();

        if (TextUtils.isEmpty(observerFormThree.getFormthree().getResponse().getReport5().getDate2())) {
            edit_date.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            edit_date.setText(observerFormThree.getFormthree().getResponse().getReport5().getDate2());
        }
    }

    private void listners() {
        edit_date.addTextChangedListener(this);
        edit_timeout.addTextChangedListener(this);
        edit_mileage.addTextChangedListener(this);

    }

    private void initUI() {
        txt_finish = (TextView) convertView.findViewById(R.id.txt_finish);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);

        edit_date = (EditText) convertView.findViewById(R.id.edit_date);
        edit_timeout = (EditText) convertView.findViewById(R.id.edit_timeout);
        edit_mileage = (EditText) convertView.findViewById(R.id.edit_mileage);
        etEmailAddressForm17New = (EditText) convertView.findViewById(R.id.etEmailAddressForm17New);
        btnSendForm17New = (Button) convertView.findViewById(R.id.btnSendForm17New);
        clicks();
    }

    private void clicks() {

        edit_timeout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        setTime(selectedHour, selectedMinute, edit_timeout);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        txt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
                String json_full = gson.toJson(observerFormThree.getFormthree());
                String json = gson.toJson(observerFormThree.getFormthree().getResponse());

                FunctionUtils.getInstance().longLog(json);

                if (TextUtils.isEmpty(FireForm3.id)) {
                    update(json, true, json_full);
                } else {
                    update(json, false, json_full);
                }
            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FireForm3) getActivity()).viewPager.setCurrentItem(3);
            }
        });

        btnSendForm17New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etEmailAddressForm17New.getText().toString().trim())) {
                    Form3PDF form3PDF = new Form3PDF(getActivity());
                    try {
                        form3PDF.createForm(etEmailAddressForm17New.getText().toString());
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    FunctionUtils.getInstance().showToast("Enter Email Address");
                }
            }
        });
    }

    private void setTime(int selectedHour, int selectedMinute, EditText edit) {
        int hour;
        String hour_value, min_value, am_pm;
        if (selectedHour < 12) {
            hour = selectedHour;
            am_pm = "AM";
        } else {
            hour = selectedHour - 12;
            am_pm = "PM";
        }
        if (hour < 10) {
            hour_value = "0" + hour;
        } else {
            hour_value = hour + "";
        }
        if (selectedMinute < 10) {
            min_value = "0" + selectedMinute;
        } else {
            min_value = selectedMinute + "";
        }
        String time = hour_value + ":" + min_value + " " + am_pm;
        edit.setText(time);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s == edit_date.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport5().setDate2(s.toString());
        } else if (s == edit_timeout.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport5().setTimeOut(s.toString());
        } else if (s == edit_mileage.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport5().setMileage(s.toString());
        }
    }

    public void update(String json, final boolean isSave, String json_full) {
        if (!FunctionUtils.getInstance().isDeviceOnline()) {
            saveLocalForm(json_full, isSave);
            saveLocally(json, isSave);
        } else {

            final ProgressDialog pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving Details...");
            pDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Fire_API fire_api = retrofit.create(Fire_API.class);
            Call<ResponseBody> call;
            if (isSave) {
                call = fire_api.addForm(AppConstant.ACTION_SAVE_FORM_THREE, PrefUtils.getCurrentUser(getActivity()).userId, json);
            } else {
                call = fire_api.updateFormThree(AppConstant.ACTION_UPDATE_FORM_THREE, FireForm3.id, json);
            }

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                    try {

                        String res = new String(response.body().bytes());
                        if (new JSONObject(res).optJSONObject("response").optInt("form_id") > 0) {
                            if (!isSave) {
                                db.removeLocalForm(FireForm3.id);
                            }
                            getActivity().finish();

                        } else {
                            FunctionUtils.getInstance().showToast("Please Try Again");
                        }
                        Log.d("sds", "sds:    " + new String(response.body().bytes()));

                        //  getActivity().finish();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    pDialog.dismiss();


                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    FunctionUtils.getInstance().showToast(
                            "Please Try Again");
                    pDialog.dismiss();

                }
            });
        }
    }

    private void saveLocalForm(String json, boolean isSave) {
        if (!isSave) {
            LocalFormProp prop = new LocalFormProp();
            prop.setForm_data(json);
            prop.setForm_id(FireForm3.id);
            prop.setForm_type("3");
            db.addLocalForm(prop);
        }
    }

    private void saveLocally(String json, boolean isSave) {
        ServerFormProp prop = new ServerFormProp();
        prop.setForm_type("3");
        prop.setForm_data(json);

        if (isSave) {
            prop.setForm_action(AppConstant.ACTION_SAVE_FORM_THREE);
            prop.setForm_isnew("true");
            prop.setForm_user_id(PrefUtils.getCurrentUser(getActivity()).userId);
            //  call = fire_api.addForm(AppConstant.ACTION_SAVE_FORM_FOUR, PrefUtils.getCurrentUser(getActivity()).userId, json);
        } else {
            prop.setForm_action(AppConstant.ACTION_UPDATE_FORM_THREE);
            prop.setForm_isnew("false");
            prop.setForm_user_id(FireForm3.id);
            //  call = fire_api.updateFormFour(AppConstant.ACTION_UPDATE_FORM_FOUR, VehicleForm4.id, json);
        }

        int res = db.addServerForm(prop);
        if (res > 0) {
            FunctionUtils.getInstance().showToast("Form Saved Locally");
            getActivity().finish();
        } else {
            FunctionUtils.getInstance().showToast("Error");
        }

    }
}
