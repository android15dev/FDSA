package com.nkdroidsolutions.firedefence.fragment.Form_1_New;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.nkdroidsolutions.firedefence.activity.Form1Activity;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nkdroidsolutions.firedefence.web_api.UrlToBitmap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sahil on 6/24/2016.
 */
public class Form12 extends Fragment implements AllObserver {

    View view;
    Context context;
    Activity activity;
    EditText etDateForm12New, etPrintNameForm12New;
    CheckBox cbClientUnavailableForm12New, cbForFDSOnlyForm12New;
    TextView tvClearForm12New;
    SignaturePad signClientForm12New;
    private ArrayList<String> checks = new ArrayList<String>();
    private boolean isSigned = false;


    public static Form12 newInstance() {
        return new Form12();
    }

    public Form12() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_form12_new, container, false);

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

        init();

        if (observerFormOne.getFormone() != null) {
            etDateForm12New.setText(observerFormOne.getFormone().getResponse().getReport2().getDate());
            etPrintNameForm12New.setText(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedSignName());

            if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedBy())) {
                checks = new ArrayList<String>(Arrays.asList(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedBy().split(",")));

                for (String str : checks) {
                    if (str.equals("1")) {
                        cbClientUnavailableForm12New.setChecked(true);
                    } else if (str.equals("2")) {
                        cbForFDSOnlyForm12New.setChecked(true);
                    }
                }
            }
            if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedSign())) {

                if (observerFormOne.getFormone().getResponse().getReport2().getAuthorisedSign().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedSign());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport2().setSignBitmap(bitmap);
                            signClientForm12New.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormOne.getFormone().getResponse().getReport2().getAuthorisedSign());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport2().setSignBitmap(bitmap);
                            signClientForm12New.setSignatureBitmap(bitmap);
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

        if (TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport2().getDate())) {
            etDateForm12New.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            etDateForm12New.setText(observerFormOne.getFormone().getResponse().getReport2().getDate());
        }

    }

    private void init() {

        etDateForm12New = (EditText) view.findViewById(R.id.etDateForm12New);
        etPrintNameForm12New = (EditText) view.findViewById(R.id.etPrintNameForm12New);
        tvClearForm12New = (TextView) view.findViewById(R.id.tvClearForm12New);
        cbClientUnavailableForm12New = (CheckBox) view.findViewById(R.id.cbClientUnavailableForm12New);
        cbForFDSOnlyForm12New = (CheckBox) view.findViewById(R.id.cbForFDSOnlyForm12New);
        signClientForm12New = (SignaturePad) view.findViewById(R.id.signClientForm12New);
        tvClearForm12New = (TextView) view.findViewById(R.id.tvClearForm12New);
        tvClearForm12New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signClientForm12New.clear();
            }
        });

        signClientForm12New.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onSigned() {
                isSigned = true;
                observerFormOne.getFormone().getResponse().getReport2().setAuthorisedSign("");
                Log.d("cjdncj", "Signed");
            }

            @Override
            public void onClear() {
                isSigned = false;
                observerFormOne.getFormone().getResponse().getReport2().setAuthorisedSign("");
                Log.d("cjdncj", "Cancelled");
            }
        });

        getActivity().findViewById(R.id.back12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((Form1Activity) getActivity()).viewPager.setCurrentItem(0);
            }
        });

        getActivity().findViewById(R.id.next12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();

                if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport2().getDate())
                        && !TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedBy())
                        && !TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedSign())
                        && !TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport2().getAuthorisedSignName())) {
                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(2);
                } else {
                    FunctionUtils.getInstance().showToast(getString(R.string.fillallfields));
                }

            }
        });
    }

    private void listners() {
        etDateForm12New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport2().setDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPrintNameForm12New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport2().setAuthorisedSignName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cbClientUnavailableForm12New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange("1", isChecked);
            }
        });

        cbForFDSOnlyForm12New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        observerFormOne.getFormone().getResponse().getReport2().setAuthorisedBy(TextUtils.join(",", checks));
    }

    private void putSign() {
        if (isSigned) {
            observerFormOne.getFormone().getResponse().getReport2().setSignBitmap(signClientForm12New.getSignatureBitmap());
            observerFormOne.getFormone().getResponse().getReport2().setAuthorisedSign(
                    FunctionUtils.getInstance().encodeToBase64(signClientForm12New.getSignatureBitmap()).trim());
            isSigned = false;
        }
    }

}
