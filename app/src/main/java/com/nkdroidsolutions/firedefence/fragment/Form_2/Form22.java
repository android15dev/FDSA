package com.nkdroidsolutions.firedefence.fragment.Form_2;


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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.SprinklerForm2;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nkdroidsolutions.firedefence.web_api.UrlToBitmap;

import java.util.ArrayList;
import java.util.Arrays;


public class Form22 extends Fragment implements AllObserver {

    private EditText edit_date, edit_print_name;
    private View convertView;
    private TextView txt_next, txt_back;
    private TextView txt_clear1;
    private SignaturePad sign_client;
    private ArrayList<String> checks = new ArrayList<String>();
    private boolean isSigned = false;
    private CheckBox chk1, chk2;

    public static Form22 newInstance() {
        return new Form22();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form22, container, false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();

        if (observerFormTwo.getFormTwo() != null) {
            edit_date.setText(observerFormTwo.getFormTwo().getResponse().getReport2().getReportdate2());
            edit_print_name.setText(observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedSignName());

            if (!TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedBy())) {
                checks = new ArrayList<String>(Arrays.asList(observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedBy().split(",")));

                for (String str : checks) {
                    if (str.equals("1")) {
                        chk1.setChecked(true);
                    } else if (str.equals("2")) {
                        chk2.setChecked(true);
                    }
                }
            }
            if (!TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedSign())) {

                if (observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedSign().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedSign());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormTwo.getFormTwo().getResponse().getReport2().setSignBitmap(bitmap);
                            sign_client.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormTwo.getFormTwo().getResponse().getReport2().getAuthorisedSign());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormTwo.getFormTwo().getResponse().getReport2().setSignBitmap(bitmap);
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

        if (TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport2().getReportdate2())) {
            edit_date.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            edit_date.setText(observerFormTwo.getFormTwo().getResponse().getReport2().getReportdate2());
        }
    }

    private void initUI() {
        edit_date = (EditText) convertView.findViewById(R.id.edit_date);
        edit_print_name = (EditText) convertView.findViewById(R.id.edit_print_name);
        chk1 = (CheckBox) convertView.findViewById(R.id.chk1);
        chk2 = (CheckBox) convertView.findViewById(R.id.chk2);
        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);
        txt_clear1 = (TextView) convertView.findViewById(R.id.txt_clear1);
        sign_client = (SignaturePad) convertView.findViewById(R.id.sign_client);

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
                observerFormTwo.getFormTwo().getResponse().getReport2().setAuthorisedSign("");
                Log.d("cjdncj", "Signed");
            }

            @Override
            public void onClear() {
                isSigned = false;
                observerFormTwo.getFormTwo().getResponse().getReport2().setAuthorisedSign("");
                Log.d("cjdncj", "Cancelled");
            }
        });

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(2);
            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(0);
            }
        });

    }

    private void listners() {
        edit_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormTwo.getFormTwo().getResponse().getReport2().setReportdate2(s.toString());
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
                observerFormTwo.getFormTwo().getResponse().getReport2().setAuthorisedSignName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange("1", isChecked);
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange("2", isChecked);
            }
        });

    }

    private void setCheckChange(String value, boolean isChecked) {
        if (isChecked) {
            checks.add(value);
        } else {
            checks.remove(value);
        }
        observerFormTwo.getFormTwo().getResponse().getReport2().setAuthorisedBy(TextUtils.join(",", checks));
    }

    private void putSign() {
        if (isSigned) {
            observerFormTwo.getFormTwo().getResponse().getReport2().setSignBitmap(sign_client.getSignatureBitmap());
            observerFormTwo.getFormTwo().getResponse().getReport2().setAuthorisedSign(
                    FunctionUtils.getInstance().encodeToBase64(sign_client.getSignatureBitmap()).trim());
            isSigned = false;
        }
    }


}
