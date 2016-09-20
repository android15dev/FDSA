package com.nkdroidsolutions.firedefence.fragment.Form_1_New;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.DocumentException;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.Form1Activity;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.LocalFormProp;
import com.nkdroidsolutions.firedefence.model.ServerFormProp;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.storage.Form1PDF;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.PrefUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Fire_API;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sahil on 6/24/2016.
 */
public class Form17 extends Fragment implements AllObserver {

    View view;
    Context context;
    Activity activity;
    TextView txt_back, txt_finish;
    EditText etTimeCompletedForm17New, etTravelTimeForm17New, etMieageForm17New, etWorkCompletedForm17New, etEmailAddressForm17New;
    private Button btnSendForm17New;
    private Database db;

    public static Form17 newInstance() {
        return new Form17();
    }

    public Form17() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_form17_new, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        this.activity = activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = new Database(getActivity());

        init();

        if (observerFormOne.getFormone() != null) {
            etTimeCompletedForm17New.setText(observerFormOne.getFormone().getResponse().getReport7().getTimeCompleted());
            etTravelTimeForm17New.setText(observerFormOne.getFormone().getResponse().getReport7().getTraveltime());
            etMieageForm17New.setText(observerFormOne.getFormone().getResponse().getReport7().getMileage());
            etWorkCompletedForm17New.setText(observerFormOne.getFormone().getResponse().getReport7().getWorkCompletedLast());
            etEmailAddressForm17New.setText(observerFormOne.getFormone().getResponse().getReport7().getEmailAddress());
        }
        listners();
        if (TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport7().getTimeCompleted())) {
            etTimeCompletedForm17New.setText(FunctionUtils.getInstance().getCurrentTime());
        } else {
            etTimeCompletedForm17New.setText(observerFormOne.getFormone().getResponse().getReport7().getTimeCompleted());
        }

    }

    private void init() {
        etTimeCompletedForm17New = (EditText) view.findViewById(R.id.etTimeCompletedForm17New);
        etTravelTimeForm17New = (EditText) view.findViewById(R.id.etTravelTimeForm17New);
        etMieageForm17New = (EditText) view.findViewById(R.id.etMieageForm17New);
        etWorkCompletedForm17New = (EditText) view.findViewById(R.id.etWorkCompletedForm17New);
        etEmailAddressForm17New = (EditText) view.findViewById(R.id.etEmailAddressForm17New);
        btnSendForm17New = (Button) view.findViewById(R.id.btnSendForm17New);
        txt_back = (TextView) view.findViewById(R.id.back);
        txt_finish = (TextView) view.findViewById(R.id.finish);

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Form1Activity) getActivity()).viewPager.setCurrentItem(5);
            }
        });

        txt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
                String json_full = gson.toJson(observerFormOne.getFormone());
                String json = gson.toJson(observerFormOne.getFormone().getResponse());
                // json.replace("\\\\", "\\");
                FunctionUtils.getInstance().longLog(json);

                if (TextUtils.isEmpty(Form1Activity.id)) {
                    update(json, true, json_full);
                } else {
                    update(json, false, json_full);
                }
            }
        });

        btnSendForm17New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Form1PDF form1PDF = new Form1PDF(getActivity());
                try {
                    form1PDF.createForm();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void listners() {
        etTimeCompletedForm17New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport7().setTimeCompleted(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etTravelTimeForm17New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport7().setTraveltime(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etMieageForm17New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport7().setMileage(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etWorkCompletedForm17New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport7().setWorkCompletedLast(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etEmailAddressForm17New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport7().setEmailAddress(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                call = fire_api.addForm(AppConstant.ACTION_SAVE_FORM_ONE, PrefUtils.getCurrentUser(getActivity()).userId, json);
            } else {
                call = fire_api.updateFormOne(AppConstant.ACTION_UPDATE_FORM_ONE, Form1Activity.id, json);
            }


            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                    try {

                        String res = new String(response.body().bytes());
                        if (new JSONObject(res).optJSONObject("response").optInt("form_id") > 0) {
                            if (!isSave) {
                                db.removeLocalForm(Form1Activity.id);
                            }
                            getActivity().finish();
                        } else {
                            FunctionUtils.getInstance().showToast("Please Try Again");
                        }

                        Log.d("sds", "sds:    " + new String(response.body().bytes()));


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
            prop.setForm_id(Form1Activity.id);
            prop.setForm_type("1");
            db.addLocalForm(prop);
        }
    }

    private void saveLocally(String json, boolean isSave) {
        ServerFormProp prop = new ServerFormProp();
        prop.setForm_type("1");
        prop.setForm_data(json);

        if (isSave) {
            prop.setForm_action(AppConstant.ACTION_SAVE_FORM_ONE);
            prop.setForm_isnew("true");
            prop.setForm_user_id(PrefUtils.getCurrentUser(getActivity()).userId);
        } else {
            prop.setForm_action(AppConstant.ACTION_UPDATE_FORM_ONE);
            prop.setForm_isnew("false");
            prop.setForm_user_id(Form1Activity.id);
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