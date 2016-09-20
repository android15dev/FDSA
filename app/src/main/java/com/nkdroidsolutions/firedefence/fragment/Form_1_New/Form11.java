package com.nkdroidsolutions.firedefence.fragment.Form_1_New;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.Form1Activity;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

/**
 * Created by Sahil on 6/24/2016.
 */
public class Form11 extends Fragment implements AllObserver {

    View view;
    Activity activity;
    EditText jobReferenceNo, etDateForm11, etArrivedForm11, etClientName, etNotesForm11New;

    public static Form11 newInstance() {
        return new Form11();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_form11_new, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initUI();
        listners();
        if (observerFormOne.getFormone() != null) {
            jobReferenceNo.setText(observerFormOne.getFormone().getResponse().getReport1().getJobNo());
            if (TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport1().getDate())) {
                etDateForm11.setText(FunctionUtils.getInstance().getCurrentDate());
            } else {
                etDateForm11.setText(observerFormOne.getFormone().getResponse().getReport1().getDate());
            }
            if (TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport1().getArrived())) {
                etArrivedForm11.setText(FunctionUtils.getInstance().getCurrentTime());
            } else {
                etArrivedForm11.setText(observerFormOne.getFormone().getResponse().getReport1().getArrived());
            }
            etClientName.setText(observerFormOne.getFormone().getResponse().getReport1().getClientName());
            etNotesForm11New.setText(observerFormOne.getFormone().getResponse().getReport1().getNotes());
        }


    }

    private void initUI() {

        jobReferenceNo = (EditText) view.findViewById(R.id.jobReferenceNo);
        etDateForm11 = (EditText) view.findViewById(R.id.etDateForm11);
        etArrivedForm11 = (EditText) view.findViewById(R.id.etArrivedForm11);
        etClientName = (EditText) view.findViewById(R.id.etClientName);
        etNotesForm11New = (EditText) view.findViewById(R.id.etNotesForm11New);


        getActivity().findViewById(R.id.tvNextForm11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport1().getArrived())
                        &&!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport1().getClientName())
                        &&!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport1().getDate())
                        &&!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport1().getJobNo())
                        &&!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport1().getNotes())){
                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(1);
                }else{
                    FunctionUtils.getInstance().showToast(getString(R.string.fillallfields));
                }

            }
        });
    }

    private void listners() {
        jobReferenceNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport1().setJobNo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etDateForm11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport1().setDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etArrivedForm11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport1().setArrived(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etClientName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport1().setClientName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etNotesForm11New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport1().setNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
