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


public class Form26 extends Fragment implements AllObserver {

    private EditText edit_date, edit_full_name, edit_time, edit_work_completed, edit_backonline, edit_comments;
    private View convertView;
    private TextView txt_finish, txt_back;
    private TextView txt_clear2, txt_clear1;
    private SignaturePad sign_client, sign_engineer;
    private boolean isSignedEngineer = false;
    private boolean isSignedClient = false;

    public static Form26 newInstance() {
        return new Form26();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form28, container, false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();

        if (observerFormTwo.getFormTwo() != null) {
            edit_date.setText(observerFormTwo.getFormTwo().getResponse().getReport6().getDate());
            edit_time.setText(observerFormTwo.getFormTwo().getResponse().getReport6().getTime());
            edit_full_name.setText(observerFormTwo.getFormTwo().getResponse().getReport6().getClientNameWork());
            edit_work_completed.setText(observerFormTwo.getFormTwo().getResponse().getReport6().getWorkCompleted());
            edit_backonline.setText(observerFormTwo.getFormTwo().getResponse().getReport6().getBackOnline());
            edit_comments.setText(observerFormTwo.getFormTwo().getResponse().getReport6().getNotes());

            if (!TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport6().getEngineerSign())) {

                if (observerFormTwo.getFormTwo().getResponse().getReport6().getEngineerSign().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormTwo.getFormTwo().getResponse().getReport6().getEngineerSign());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormTwo.getFormTwo().getResponse().getReport6().setEngineerBitmap(bitmap);
                            sign_engineer.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormTwo.getFormTwo().getResponse().getReport6().getEngineerSign());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormTwo.getFormTwo().getResponse().getReport6().setEngineerBitmap(bitmap);
                            sign_engineer.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }

            if (!TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport6().getClientSign())) {
                if (observerFormTwo.getFormTwo().getResponse().getReport6().getClientSign().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormTwo.getFormTwo().getResponse().getReport6().getClientSign());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormTwo.getFormTwo().getResponse().getReport6().setClientBitmap(bitmap);
                            sign_client.setSignatureBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormTwo.getFormTwo().getResponse().getReport6().getClientSign());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormTwo.getFormTwo().getResponse().getReport6().setClientBitmap(bitmap);
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

        if (TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport6().getDate())) {
            edit_date.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            edit_date.setText(observerFormTwo.getFormTwo().getResponse().getReport6().getDate());
        }

        if (TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport6().getTime())) {
            edit_time.setText(FunctionUtils.getInstance().getCurrentTime());
        } else {
            edit_time.setText(observerFormTwo.getFormTwo().getResponse().getReport6().getTime());
        }

    }

    private void initUI() {
        edit_time = (EditText) convertView.findViewById(R.id.edit_time);
        edit_date = (EditText) convertView.findViewById(R.id.edit_date);
        edit_full_name = (EditText) convertView.findViewById(R.id.edit_full_name);
        edit_work_completed = (EditText) convertView.findViewById(R.id.edit_work_completed);
        edit_backonline = (EditText) convertView.findViewById(R.id.edit_backonline);
        edit_comments = (EditText) convertView.findViewById(R.id.edit_comments);
        txt_finish = (TextView) convertView.findViewById(R.id.txt_finish);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);
        txt_clear1 = (TextView) convertView.findViewById(R.id.txt_clear1);
        txt_clear2 = (TextView) convertView.findViewById(R.id.txt_clear2);
        sign_engineer = (SignaturePad) convertView.findViewById(R.id.sign_engineer1);
        sign_client = (SignaturePad) convertView.findViewById(R.id.sign_client1);


        clicks();
    }

    private void clicks() {
        txt_clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_client.clear();
            }
        });
        txt_clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_engineer.clear();
            }
        });

        sign_engineer.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onSigned() {
                isSignedEngineer = true;
                observerFormTwo.getFormTwo().getResponse().getReport6().setEngineerSign("");
                Log.d("cjdncj", "Signed");
            }

            @Override
            public void onClear() {
                isSignedEngineer = false;
                observerFormTwo.getFormTwo().getResponse().getReport6().setEngineerSign("");
                Log.d("cjdncj", "Cancelled");
            }
        });


        sign_client.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onSigned() {
                isSignedClient = true;
                observerFormTwo.getFormTwo().getResponse().getReport6().setClientSign("");
                Log.d("cjdncj", "Signed");
            }

            @Override
            public void onClear() {
                isSignedClient = false;
                observerFormTwo.getFormTwo().getResponse().getReport6().setClientSign("");
                Log.d("cjdncj", "Cancelled");
            }
        });

        txt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(6);
            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSign();
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(4);
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
                observerFormTwo.getFormTwo().getResponse().getReport6().setDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormTwo.getFormTwo().getResponse().getReport6().setTime(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_full_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormTwo.getFormTwo().getResponse().getReport6().setClientNameWork(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_work_completed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormTwo.getFormTwo().getResponse().getReport6().setWorkCompleted(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_backonline.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormTwo.getFormTwo().getResponse().getReport6().setBackOnline(s.toString());
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
                observerFormTwo.getFormTwo().getResponse().getReport6().setNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void putSign() {
        if (isSignedEngineer) {
            observerFormTwo.getFormTwo().getResponse().getReport6().setEngineerBitmap(sign_engineer.getSignatureBitmap());
            observerFormTwo.getFormTwo().getResponse().getReport6().setEngineerSign(
                    FunctionUtils.getInstance().encodeToBase64(sign_engineer.getSignatureBitmap()).trim());
            isSignedEngineer = false;
        }
        if (isSignedClient) {
            observerFormTwo.getFormTwo().getResponse().getReport6().setClientBitmap(sign_client.getSignatureBitmap());
            observerFormTwo.getFormTwo().getResponse().getReport6().setClientSign(
                    FunctionUtils.getInstance().encodeToBase64(sign_client.getSignatureBitmap()).trim());
            isSignedClient = false;
        }
    }
}
