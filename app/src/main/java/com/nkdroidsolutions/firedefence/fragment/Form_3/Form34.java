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


public class Form34 extends Fragment implements AllObserver, TextWatcher {

    private View convertView;
    private TextView txt_next, txt_back;
    private TextView txt_clear1;
    private SignaturePad sign_client;
    private EditText edit_date, edit_print_name, edit_position, edit_additional_notes;
    private boolean isSigned = false;

    public static Form34 newInstance() {
        return new Form34();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form35, container, false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initUI();

        if (observerFormThree.getFormthree() != null) {
            edit_date.setText(observerFormThree.getFormthree().getResponse().getReport4().getDate1());
            edit_print_name.setText(observerFormThree.getFormthree().getResponse().getReport4().getPrintName());
            edit_position.setText(observerFormThree.getFormthree().getResponse().getReport4().getPosition());
            edit_additional_notes.setText(observerFormThree.getFormthree().getResponse().getReport4().getEngineerNotes());

            if (!TextUtils.isEmpty(observerFormThree.getFormthree().getResponse().getReport4().getCustomer_sign())) {
                if (observerFormThree.getFormthree().getResponse().getReport4().getCustomer_sign().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormThree.getFormthree().getResponse().getReport4().getCustomer_sign());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormThree.getFormthree().getResponse().getReport4().setSignBitmap(bitmap);
                            sign_client.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormThree.getFormthree().getResponse().getReport4().getCustomer_sign());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormThree.getFormthree().getResponse().getReport4().setSignBitmap(bitmap);
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

        if (TextUtils.isEmpty(observerFormThree.getFormthree().getResponse().getReport4().getDate1())) {
            edit_date.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            edit_date.setText(observerFormThree.getFormthree().getResponse().getReport4().getDate1());
        }


    }

    private void listners() {
        edit_date.addTextChangedListener(this);
        edit_print_name.addTextChangedListener(this);
        edit_position.addTextChangedListener(this);
        edit_additional_notes.addTextChangedListener(this);
    }

    private void initUI() {
        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);

        edit_date = (EditText) convertView.findViewById(R.id.edit_date);
        edit_print_name = (EditText) convertView.findViewById(R.id.edit_print_name);
        edit_position = (EditText) convertView.findViewById(R.id.edit_position);
        edit_additional_notes = (EditText) convertView.findViewById(R.id.edit_additional_notes);
        txt_clear1 = (TextView) convertView.findViewById(R.id.txt_clear1);
        sign_client = (SignaturePad) convertView.findViewById(R.id.sign_customer);


        clicks();

    }

    private void clicks() {
        txt_clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_client.clear();
            }
        });

        sign_client.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onSigned() {
                isSigned = true;
                observerFormThree.getFormthree().getResponse().getReport4().setCustomer_sign("");
                Log.d("cjdncj", "Signed");
            }

            @Override
            public void onClear() {
                isSigned = false;
                observerFormThree.getFormthree().getResponse().getReport4().setCustomer_sign("");
                Log.d("cjdncj", "Cancelled");
            }
        });

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((FireForm3) getActivity()).viewPager.setCurrentItem(4);
            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((FireForm3) getActivity()).viewPager.setCurrentItem(2);
            }
        });
    }

    private void putSign() {
        if (isSigned) {
            observerFormThree.getFormthree().getResponse().getReport4().setSignBitmap(sign_client.getSignatureBitmap());
            observerFormThree.getFormthree().getResponse().getReport4().setCustomer_sign(
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
            observerFormThree.getFormthree().getResponse().getReport4().setDate1(s.toString());
        } else if (s == edit_print_name.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport4().setPrintName(s.toString());
        } else if (s == edit_position.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport4().setPosition(s.toString());
        } else if (s == edit_additional_notes.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport4().setEngineerNotes(s.toString());
        }
    }
}
