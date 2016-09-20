package com.nkdroidsolutions.firedefence.fragment.Form_4;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.VehicleForm4;
import com.nkdroidsolutions.firedefence.model.GetAssignedForm1;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;


public class Form41 extends Fragment implements AllObserver {

    private TextView next;
    private LinearLayout rootLayout;
    public GetAssignedForm1 form1;

    private EditText edit_vehicle_reg, edit_date, edit_driver_name, edit_odometer, edit_comments;
    private View convertView;
    private TextView txt_next;
    private Activity activity;

    public static Form41 newInstance() {
        return new Form41();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form41, container, false);
        return convertView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();

        if (observerFormFour.getFormfour() != null) {
            edit_vehicle_reg.setText(observerFormFour.getFormfour().getResponse().getReport1().getVehicleReg());
            edit_date.setText(observerFormFour.getFormfour().getResponse().getReport1().getDate());
            edit_driver_name.setText(observerFormFour.getFormfour().getResponse().getReport1().getDriverName());
            edit_odometer.setText(observerFormFour.getFormfour().getResponse().getReport1().getOdometer());
            edit_comments.setText(observerFormFour.getFormfour().getResponse().getReport1().getComments());
        }

        listners();
        if (TextUtils.isEmpty(observerFormFour.getFormfour().getResponse().getReport1().getDate())) {
            edit_date.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            edit_date.setText(observerFormFour.getFormfour().getResponse().getReport1().getDate());
        }
    }

    private void initUI() {
        edit_vehicle_reg = (EditText) convertView.findViewById(R.id.edit_vehicle_reg);
        edit_date = (EditText) convertView.findViewById(R.id.edit_date);
        edit_driver_name = (EditText) convertView.findViewById(R.id.edit_driver_name);
        edit_odometer = (EditText) convertView.findViewById(R.id.edit_odometer);
        edit_comments = (EditText) convertView.findViewById(R.id.edit_comments);

        txt_next = (TextView) convertView.findViewById(R.id.txt_next);

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((VehicleForm4) getActivity()).viewPager.setCurrentItem(1);
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
                observerFormFour.getFormfour().getResponse().getReport1().setDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_vehicle_reg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormFour.getFormfour().getResponse().getReport1().setVehicleReg(s.toString());
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
                observerFormFour.getFormfour().getResponse().getReport1().setComments(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_odometer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormFour.getFormfour().getResponse().getReport1().setOdometer(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edit_driver_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormFour.getFormfour().getResponse().getReport1().setDriverName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
