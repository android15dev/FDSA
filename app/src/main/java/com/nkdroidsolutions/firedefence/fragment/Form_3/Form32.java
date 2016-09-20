package com.nkdroidsolutions.firedefence.fragment.Form_3;


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
import android.widget.EditText;
import android.widget.TextView;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.FireForm3;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nkdroidsolutions.firedefence.web_api.UrlToBitmap;


public class Form32 extends Fragment implements AllObserver, TextWatcher {

    private View convertView;
    private TextView txt_next, txt_back;
    private TextView txt_clear;
    private SignaturePad sign_client;
    private EditText edit_date, edit_time, edit_client_name;
    private boolean isSigned = false;

    public static Form32 newInstance() {
        return new Form32();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form32, container, false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();

        if (observerFormThree.getFormthree() != null) {
            edit_date.setText(observerFormThree.getFormthree().getResponse().getReport2().getDate());
            edit_time.setText(observerFormThree.getFormthree().getResponse().getReport2().getTime());
            edit_client_name.setText(observerFormThree.getFormthree().getResponse().getReport2().getClientName());

            if (!TextUtils.isEmpty(observerFormThree.getFormthree().getResponse().getReport2().getClient_sign())) {
                if (observerFormThree.getFormthree().getResponse().getReport2().getClient_sign().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormThree.getFormthree().getResponse().getReport2().getClient_sign());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormThree.getFormthree().getResponse().getReport2().setSignBitmap(bitmap);
                            sign_client.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormThree.getFormthree().getResponse().getReport2().getClient_sign());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormThree.getFormthree().getResponse().getReport2().setSignBitmap(bitmap);
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

        if (TextUtils.isEmpty(observerFormThree.getFormthree().getResponse().getReport2().getDate())) {
            edit_date.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            edit_date.setText(observerFormThree.getFormthree().getResponse().getReport2().getDate());
        }

        if (TextUtils.isEmpty(observerFormThree.getFormthree().getResponse().getReport2().getTime())) {
            edit_time.setText(FunctionUtils.getInstance().getCurrentTime());
        } else {
            edit_time.setText(observerFormThree.getFormthree().getResponse().getReport2().getTime());
        }

    }

    private void listners() {
        edit_date.addTextChangedListener(this);
        edit_time.addTextChangedListener(this);
        edit_client_name.addTextChangedListener(this);
    }

    private void initUI() {
        edit_date = (EditText) convertView.findViewById(R.id.edit_date);
        edit_time = (EditText) convertView.findViewById(R.id.edit_time);
        edit_client_name = (EditText) convertView.findViewById(R.id.edit_client_name);

        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);

        txt_clear = (TextView) convertView.findViewById(R.id.txt_clear);
        sign_client = (SignaturePad) convertView.findViewById(R.id.sign_client);

        clicks();
    }

    private void clicks() {

        txt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_client.clear();
            }
        });

        sign_client.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onSigned() {
                isSigned = true;
                observerFormThree.getFormthree().getResponse().getReport2().setClient_sign("");
                Log.d("cjdncj", "Signed");
            }

            @Override
            public void onClear() {
                isSigned = false;
                observerFormThree.getFormthree().getResponse().getReport2().setClient_sign("");
                Log.d("cjdncj", "Cancelled");
            }
        });

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((FireForm3) getActivity()).viewPager.setCurrentItem(2);
            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((FireForm3) getActivity()).viewPager.setCurrentItem(0);
            }
        });
    }

    private void putSign() {
        if (isSigned) {
            observerFormThree.getFormthree().getResponse().getReport2().setSignBitmap(sign_client.getSignatureBitmap());
            observerFormThree.getFormthree().getResponse().getReport2().setClient_sign(
                    FunctionUtils.getInstance().encodeToBase64(sign_client.getSignatureBitmap()).trim());
            isSigned = false;
        }

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
            observerFormThree.getFormthree().getResponse().getReport2().setDate(s.toString());
        } else if (s == edit_time.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport2().setTime(s.toString());
        } else if (s == edit_client_name.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport2().setClientName(s.toString());
        }
    }
}
