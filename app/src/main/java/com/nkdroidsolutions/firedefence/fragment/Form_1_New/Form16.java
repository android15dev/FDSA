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

/**
 * Created by Sahil on 6/24/2016.
 */
public class Form16 extends Fragment implements AllObserver {

    View view;
    TextView back, next;
    Context context;
    Activity activity;
    SignaturePad spClientSignatureForm16New, spEngineerSignatureClearForm16New;
    TextView tvClientSignatureClearForm16New, tvEngineerSignatureClearForm16New;
    EditText tvNotesForm16New, etDateForm16New, etTimeForm16New, etClientNameForm16New, etWorkCompletedForm16New, etbackOnlineForm16New;
    private boolean isSignedEngineer = false;
    private boolean isSignedClient = false;

    public static Form16 newInstance() {
        return new Form16();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_form16_new, container, false);
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
            etDateForm16New.setText(observerFormOne.getFormone().getResponse().getReport6().getDate());
            etTimeForm16New.setText(observerFormOne.getFormone().getResponse().getReport6().getTime());
            etClientNameForm16New.setText(observerFormOne.getFormone().getResponse().getReport6().getClientName());
            etWorkCompletedForm16New.setText(observerFormOne.getFormone().getResponse().getReport6().getWorkCompleted());
            etbackOnlineForm16New.setText(observerFormOne.getFormone().getResponse().getReport6().getBackOnline());
            tvNotesForm16New.setText(observerFormOne.getFormone().getResponse().getReport6().getNotes());

            if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport6().getEngineerSign())) {

                if (observerFormOne.getFormone().getResponse().getReport6().getEngineerSign().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormOne.getFormone().getResponse().getReport6().getEngineerSign());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport6().setEngineerBitmap(bitmap);
                            spEngineerSignatureClearForm16New.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormOne.getFormone().getResponse().getReport6().getEngineerSign());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport6().setEngineerBitmap(bitmap);
                            spEngineerSignatureClearForm16New.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }

            if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport6().getClientSign())) {
                if (observerFormOne.getFormone().getResponse().getReport6().getClientSign().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormOne.getFormone().getResponse().getReport6().getClientSign());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport6().setClientBitmap(bitmap);
                            spClientSignatureForm16New.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormOne.getFormone().getResponse().getReport6().getClientSign());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport6().setClientBitmap(bitmap);
                            spClientSignatureForm16New.setSignatureBitmap(bitmap);
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

        if (TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport6().getDate())) {
            etDateForm16New.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            etDateForm16New.setText(observerFormOne.getFormone().getResponse().getReport6().getDate());
        }

        if (TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport6().getTime())) {
            etTimeForm16New.setText(FunctionUtils.getInstance().getCurrentTime());
        } else {
            etTimeForm16New.setText(observerFormOne.getFormone().getResponse().getReport6().getTime());
        }

    }

    private void init() {
        spClientSignatureForm16New = (SignaturePad) view.findViewById(R.id.spClientSignatureForm16New);
        spEngineerSignatureClearForm16New = (SignaturePad) view.findViewById(R.id.spEngineerSignatureClearForm16New);
        tvClientSignatureClearForm16New = (TextView) view.findViewById(R.id.tvClientSignatureClearForm16New);
        tvEngineerSignatureClearForm16New = (TextView) view.findViewById(R.id.tvEngineerSignatureClearForm16New);
        tvNotesForm16New = (EditText) view.findViewById(R.id.tvNotesForm16New);
        etDateForm16New = (EditText) view.findViewById(R.id.etDateForm16New);
        etTimeForm16New = (EditText) view.findViewById(R.id.etTimeForm16New);
        etClientNameForm16New = (EditText) view.findViewById(R.id.etClientNameForm16New);
        etWorkCompletedForm16New = (EditText) view.findViewById(R.id.etWorkCompletedForm16New);
        etbackOnlineForm16New = (EditText) view.findViewById(R.id.etbackOnlineForm16New);

        tvEngineerSignatureClearForm16New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spEngineerSignatureClearForm16New.clear();
            }
        });

        spEngineerSignatureClearForm16New.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onSigned() {
                isSignedEngineer = true;
                observerFormOne.getFormone().getResponse().getReport6().setEngineerSign("");
                Log.d("cjdncj", "Signed");
            }

            @Override
            public void onClear() {
                isSignedEngineer = false;
                observerFormOne.getFormone().getResponse().getReport6().setEngineerSign("");
                Log.d("cjdncj", "Cancelled");
            }
        });

        tvClientSignatureClearForm16New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spClientSignatureForm16New.clear();
            }
        });

        spClientSignatureForm16New.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onSigned() {
                isSignedClient = true;
                observerFormOne.getFormone().getResponse().getReport6().setClientSign("");
                Log.d("cjdncj", "Signed");
            }

            @Override
            public void onClear() {
                isSignedClient = false;
                observerFormOne.getFormone().getResponse().getReport6().setClientSign("");
                Log.d("cjdncj", "Cancelled");
            }
        });

        back = (TextView) view.findViewById(R.id.back16);
        next = (TextView) view.findViewById(R.id.next16);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                if (observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("5")) {
                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(4);
                } else if (observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("1") ||
                        observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("2") ||
                        observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("3") ||
                        observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("4")) {

                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(3);
                } else {
                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(2);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((Form1Activity) getActivity()).viewPager.setCurrentItem(6);
            }
        });
    }

    private void listners() {
        etDateForm16New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport6().setDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etTimeForm16New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport6().setTime(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etClientNameForm16New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport6().setClientName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etWorkCompletedForm16New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport6().setWorkCompleted(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etbackOnlineForm16New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport6().setBackOnline(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvNotesForm16New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport6().setNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void putSign() {
        if (isSignedEngineer) {
            observerFormOne.getFormone().getResponse().getReport6().setEngineerBitmap(spEngineerSignatureClearForm16New.getSignatureBitmap());
            observerFormOne.getFormone().getResponse().getReport6().setEngineerSign(
                    FunctionUtils.getInstance().encodeToBase64(spEngineerSignatureClearForm16New.getSignatureBitmap()).trim());
            isSignedEngineer = false;
        }
        if (isSignedClient) {
            observerFormOne.getFormone().getResponse().getReport6().setClientBitmap(spClientSignatureForm16New.getSignatureBitmap());
            observerFormOne.getFormone().getResponse().getReport6().setClientSign(
                    FunctionUtils.getInstance().encodeToBase64(spClientSignatureForm16New.getSignatureBitmap()).trim());
            isSignedClient = false;
        }
    }

}
