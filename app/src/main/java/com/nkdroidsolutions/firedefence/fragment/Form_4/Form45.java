package com.nkdroidsolutions.firedefence.fragment.Form_4;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
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

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.DocumentException;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.VehicleForm4;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.LocalFormProp;
import com.nkdroidsolutions.firedefence.model.ServerFormProp;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.storage.Form3PDF;
import com.nkdroidsolutions.firedefence.storage.Form4PDF;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.PrefUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.Fire_API;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nkdroidsolutions.firedefence.web_api.UrlToBitmap;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Form45 extends Fragment implements AllObserver {


    private TextView txt_clear1;
    private SignaturePad sign_client;
    private EditText edit_date, edit_comments, edit_print_name;
    private View convertView;
    private TextView txt_back;
    private TextView txt_finish;
    private boolean isSignedDriver = false;
    private Database db;
    private EditText etEmailAddressForm17New;
    private Button btnSendForm17New;

    public static Form45 newInstance() {
        return new Form45();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form46, container, false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = new Database(getActivity());

        initUI();
        if (observerFormFour.getFormfour() != null) {
            edit_print_name.setText(observerFormFour.getFormfour().getResponse().getReport5().getPrintName());
            edit_comments.setText(observerFormFour.getFormfour().getResponse().getReport5().getComment());
            edit_date.setText(observerFormFour.getFormfour().getResponse().getReport5().getDate());

            if (!TextUtils.isEmpty(observerFormFour.getFormfour().getResponse().getReport5().getDriver_sign())) {
                if (observerFormFour.getFormfour().getResponse().getReport5().getDriver_sign().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormFour.getFormfour().getResponse().getReport5().getDriver_sign());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormFour.getFormfour().getResponse().getReport5().setDriverBitmap(bitmap);
                            sign_client.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormFour.getFormfour().getResponse().getReport5().getDriver_sign());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormFour.getFormfour().getResponse().getReport5().setDriverBitmap(bitmap);
                            sign_client.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }

        listners();

        if (TextUtils.isEmpty(observerFormFour.getFormfour().getResponse().getReport5().getDate())) {
            edit_date.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            edit_date.setText(observerFormFour.getFormfour().getResponse().getReport5().getDate());
        }

    }

    private void initUI() {
        txt_finish = (TextView) convertView.findViewById(R.id.txt_finish);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);

        edit_date = (EditText) convertView.findViewById(R.id.edit_date);
        edit_print_name = (EditText) convertView.findViewById(R.id.edit_print_name);
        edit_comments = (EditText) convertView.findViewById(R.id.edit_comments);
        txt_clear1 = (TextView) convertView.findViewById(R.id.txt_clear1);
        sign_client = (SignaturePad) convertView.findViewById(R.id.sign_client);
        etEmailAddressForm17New = (EditText) convertView.findViewById(R.id.etEmailAddressForm17New);
        btnSendForm17New = (Button) convertView.findViewById(R.id.btnSendForm17New);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy");
        String formattedDate = df.format(c.getTime());
        edit_date.setText(formattedDate);

        txt_clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_client.clear();
            }
        });

        sign_client.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onSigned() {
                isSignedDriver = true;
                observerFormFour.getFormfour().getResponse().getReport5().setDriver_sign("");
                Log.d("cjdncj", "Signed");
            }

            @Override
            public void onClear() {
                isSignedDriver = false;
                observerFormFour.getFormfour().getResponse().getReport5().setDriver_sign("");
                Log.d("cjdncj", "Cancelled");
            }
        });

        txt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();

                Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
                String json_full= gson.toJson(observerFormFour.getFormfour());
                String json = gson.toJson(observerFormFour.getFormfour().getResponse());
                // json.replace("\\\\", "\\");

               /* try {
                    JSONObject jsonObject = new JSONObject(json);
                    jsonObject.getJSONObject("report4").remove("addDefectProp");
                    json = jsonObject.toString();
                    json.replace("\"/", "/");
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                FunctionUtils.getInstance().longLog(json);

                if (TextUtils.isEmpty(VehicleForm4.id)) {
                    update(json, true,json_full);
                } else {
                    update(json, false,json_full);
                }
            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((VehicleForm4) getActivity()).viewPager.setCurrentItem(3);
            }
        });

        btnSendForm17New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etEmailAddressForm17New.getText().toString().trim())) {
                    Form4PDF form4PDF = new Form4PDF(getActivity());
                    try {
                        form4PDF.createForm(etEmailAddressForm17New.getText().toString());
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

    private void putSign() {
        if (isSignedDriver) {
            observerFormFour.getFormfour().getResponse().getReport5().setDriverBitmap(sign_client.getSignatureBitmap());
            observerFormFour.getFormfour().getResponse().getReport5().setDriver_sign(
                    FunctionUtils.getInstance().encodeToBase64(sign_client.getSignatureBitmap()).trim());
            isSignedDriver = false;
        }

    }

    private void listners() {
        edit_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormFour.getFormfour().getResponse().getReport5().setDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_print_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormFour.getFormfour().getResponse().getReport5().setPrintName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_comments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormFour.getFormfour().getResponse().getReport5().setComment(s.toString());
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
                call = fire_api.addForm(AppConstant.ACTION_SAVE_FORM_FOUR, PrefUtils.getCurrentUser(getActivity()).userId, json);
            } else {
                call = fire_api.updateFormFour(AppConstant.ACTION_UPDATE_FORM_FOUR, VehicleForm4.id, json);
            }

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                    try {
                        String res = new String(response.body().bytes());
                        if (new JSONObject(res).optJSONObject("response").optInt("form_id") > 0) {
                            if (!isSave) {
                                db.removeLocalForm(VehicleForm4.id);
                            }
                            getActivity().finish();
                        } else {
                            FunctionUtils.getInstance().showToast("Please Try Again");
                        }
                        Log.d("sds", "sds:    " + new String(response.body().bytes()));

                        //   getActivity().finish();

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
            prop.setForm_id(VehicleForm4.id);
            prop.setForm_type("4");
            db.addLocalForm(prop);
        }
    }

    private void saveLocally(String json, boolean isSave) {
        ServerFormProp prop = new ServerFormProp();
        prop.setForm_type("4");
        prop.setForm_data(json);

        if (isSave) {
            prop.setForm_action(AppConstant.ACTION_SAVE_FORM_FOUR);
            prop.setForm_isnew("true");
            prop.setForm_user_id(PrefUtils.getCurrentUser(getActivity()).userId);
            //  call = fire_api.addForm(AppConstant.ACTION_SAVE_FORM_FOUR, PrefUtils.getCurrentUser(getActivity()).userId, json);
        } else {
            prop.setForm_action(AppConstant.ACTION_UPDATE_FORM_FOUR);
            prop.setForm_isnew("false");
            prop.setForm_user_id(VehicleForm4.id);
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
