package com.nkdroidsolutions.firedefence.fragment.Form_2;


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
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.SprinklerForm2;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;


public class Form21 extends Fragment implements AllObserver, TextWatcher {

    private EditText edit_job_refrence_no, edit_visit_no, edit_date, edit_arrived, edit_ser_report_no, edit_comments, edit_client_name;
    private View convertView;
    private TextView txt_next;

    public static Form21 newInstance() {
        return new Form21();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form21, container, false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();

        if (observerFormTwo.getFormTwo() != null) {
            edit_job_refrence_no.setText(observerFormTwo.getFormTwo().getResponse().getReport1().getJobNo());
            edit_visit_no.setText(observerFormTwo.getFormTwo().getResponse().getReport1().getVisitNo());
            edit_date.setText(observerFormTwo.getFormTwo().getResponse().getReport1().getReportdate1());
            edit_arrived.setText(observerFormTwo.getFormTwo().getResponse().getReport1().getArrived());
            edit_ser_report_no.setText(observerFormTwo.getFormTwo().getResponse().getReport1().getServiceReportNo());
            edit_client_name.setText(observerFormTwo.getFormTwo().getResponse().getReport1().getClientName());
            edit_comments.setText(observerFormTwo.getFormTwo().getResponse().getReport1().getNotes1());

        }
        listners();

        if (TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport1().getReportdate1())) {
            edit_date.setText(FunctionUtils.getInstance().getCurrentDate());
        } else {
            edit_date.setText(observerFormTwo.getFormTwo().getResponse().getReport1().getReportdate1());
        }

        if (TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport1().getArrived())) {
            edit_arrived.setText(FunctionUtils.getInstance().getCurrentTime());
        } else {
            edit_arrived.setText(observerFormTwo.getFormTwo().getResponse().getReport1().getArrived());
        }

    }

    private void listners() {
        edit_job_refrence_no.addTextChangedListener(this);
        edit_visit_no.addTextChangedListener(this);
        edit_date.addTextChangedListener(this);
        edit_arrived.addTextChangedListener(this);
        edit_ser_report_no.addTextChangedListener(this);
        edit_client_name.addTextChangedListener(this);
        edit_comments.addTextChangedListener(this);

    }

    private void initUI() {
        edit_job_refrence_no = (EditText) convertView.findViewById(R.id.edit_job_refrence_no);
        edit_visit_no = (EditText) convertView.findViewById(R.id.edit_visit_no);
        edit_date = (EditText) convertView.findViewById(R.id.edit_date);
        edit_arrived = (EditText) convertView.findViewById(R.id.edit_arrived);

        edit_ser_report_no = (EditText) convertView.findViewById(R.id.edit_ser_report_no);
        edit_client_name = (EditText) convertView.findViewById(R.id.edit_client_name);
        edit_comments = (EditText) convertView.findViewById(R.id.edit_comments);
        txt_next = (TextView) convertView.findViewById(R.id.txt_next);

        clicks();
    }


    private void clicks() {

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(1);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s == edit_job_refrence_no.getEditableText()) {
            observerFormTwo.getFormTwo().getResponse().getReport1().setJobNo(s.toString());
        } else if (s == edit_visit_no.getEditableText()) {
            observerFormTwo.getFormTwo().getResponse().getReport1().setVisitNo(s.toString());
        } else if (s == edit_comments.getEditableText()) {
            observerFormTwo.getFormTwo().getResponse().getReport1().setNotes1(s.toString());
        } else if (s == edit_client_name.getEditableText()) {
            observerFormTwo.getFormTwo().getResponse().getReport1().setClientName(s.toString());
        } else if (s == edit_ser_report_no.getEditableText()) {
            observerFormTwo.getFormTwo().getResponse().getReport1().setServiceReportNo(s.toString());
        } else if (s == edit_date.getEditableText()) {
            observerFormTwo.getFormTwo().getResponse().getReport1().setReportdate1(s.toString());
        } else if (s == edit_arrived.getEditableText()) {
            observerFormTwo.getFormTwo().getResponse().getReport1().setArrived(s.toString());
        }
    }
}
