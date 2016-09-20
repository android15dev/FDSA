package com.nkdroidsolutions.firedefence.fragment.Form_3;


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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.FireForm3;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;


public class Form31 extends Fragment implements AllObserver, TextWatcher {


    private View convertView;
    private TextView txt_next;
    private RadioGroup rgroup;
    private RadioButton rb_service, rb_callout, rb_install, rb_survey;
    private EditText edit_job_refrence_no, edit_month_due, edit_last_serviced, edit_timeonsite, edit_travel_time_onsite, edit_companyname,
            edit_contactperson, edit_telnum, edit_mobnum, edit_siteaddrss, edit_additional_notes;

    public static Form31 newInstance() {
        return new Form31();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form31, container, false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();

        if (observerFormThree.getFormthree() != null) {
            edit_job_refrence_no.setText(observerFormThree.getFormthree().getResponse().getReport1().getJobNo());
            edit_month_due.setText(observerFormThree.getFormthree().getResponse().getReport1().getMonthDue());
            edit_last_serviced.setText(observerFormThree.getFormthree().getResponse().getReport1().getLastServiced());
            edit_timeonsite.setText(observerFormThree.getFormthree().getResponse().getReport1().getTimeSite());
            edit_travel_time_onsite.setText(observerFormThree.getFormthree().getResponse().getReport1().getTravelTimeSite());
            edit_companyname.setText(observerFormThree.getFormthree().getResponse().getReport1().getCompanyName());
            edit_contactperson.setText(observerFormThree.getFormthree().getResponse().getReport1().getContactPerson());
            edit_telnum.setText(observerFormThree.getFormthree().getResponse().getReport1().getTelNo());
            edit_mobnum.setText(observerFormThree.getFormthree().getResponse().getReport1().getMobNo());
            edit_siteaddrss.setText(observerFormThree.getFormthree().getResponse().getReport1().getSiteAddress());
            edit_additional_notes.setText(observerFormThree.getFormthree().getResponse().getReport1().getNotes());

            if (observerFormThree.getFormthree().getResponse().getReport1().getService().equals("1")) {
                rgroup.check(rb_service.getId());
            } else if (observerFormThree.getFormthree().getResponse().getReport1().getService().equals("2")) {
                rgroup.check(rb_callout.getId());
            } else if (observerFormThree.getFormthree().getResponse().getReport1().getService().equals("3")) {
                rgroup.check(rb_install.getId());
            } else if (observerFormThree.getFormthree().getResponse().getReport1().getService().equals("4")) {
                rgroup.check(rb_survey.getId());
            }

        }
        listners();

        if (TextUtils.isEmpty(observerFormThree.getFormthree().getResponse().getReport1().getService())) {
            rgroup.check(rb_service.getId());
        }

    }

    private void listners() {
        edit_job_refrence_no.addTextChangedListener(this);
        edit_month_due.addTextChangedListener(this);
        edit_last_serviced.addTextChangedListener(this);
        edit_timeonsite.addTextChangedListener(this);
        edit_travel_time_onsite.addTextChangedListener(this);
        edit_companyname.addTextChangedListener(this);
        edit_contactperson.addTextChangedListener(this);
        edit_telnum.addTextChangedListener(this);
        edit_mobnum.addTextChangedListener(this);
        edit_siteaddrss.addTextChangedListener(this);
        edit_additional_notes.addTextChangedListener(this);

        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb_service.getId()) {
                    observerFormThree.getFormthree().getResponse().getReport1().setService("1");
                } else if (checkedId == rb_callout.getId()) {
                    observerFormThree.getFormthree().getResponse().getReport1().setService("2");
                } else if (checkedId == rb_install.getId()) {
                    observerFormThree.getFormthree().getResponse().getReport1().setService("3");
                } else if (checkedId == rb_survey.getId()) {
                    observerFormThree.getFormthree().getResponse().getReport1().setService("4");
                }
            }
        });

    }


    private void initUI() {
        edit_job_refrence_no = (EditText) convertView.findViewById(R.id.edit_job_refrence_no);
        edit_month_due = (EditText) convertView.findViewById(R.id.edit_month_due);
        edit_last_serviced = (EditText) convertView.findViewById(R.id.edit_last_serviced);
        edit_timeonsite = (EditText) convertView.findViewById(R.id.edit_timeonsite);
        edit_travel_time_onsite = (EditText) convertView.findViewById(R.id.edit_travel_time_onsite);
        edit_companyname = (EditText) convertView.findViewById(R.id.edit_companyname);
        edit_contactperson = (EditText) convertView.findViewById(R.id.edit_contactperson);
        edit_telnum = (EditText) convertView.findViewById(R.id.edit_telnum);
        edit_mobnum = (EditText) convertView.findViewById(R.id.edit_mobnum);
        edit_siteaddrss = (EditText) convertView.findViewById(R.id.edit_siteaddrss);
        edit_additional_notes = (EditText) convertView.findViewById(R.id.edit_additional_notes);

        rgroup = (RadioGroup) convertView.findViewById(R.id.rgroup);
        rb_service = (RadioButton) convertView.findViewById(R.id.rb_service);
        rb_callout = (RadioButton) convertView.findViewById(R.id.rb_callout);
        rb_install = (RadioButton) convertView.findViewById(R.id.rb_install);
        rb_survey = (RadioButton) convertView.findViewById(R.id.rb_survey);


        txt_next = (TextView) convertView.findViewById(R.id.txt_next);

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FireForm3) getActivity()).viewPager.setCurrentItem(1);
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
            observerFormThree.getFormthree().getResponse().getReport1().setJobNo(s.toString());
        } else if (s == edit_month_due.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setMonthDue(s.toString());
        } else if (s == edit_last_serviced.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setLastServiced(s.toString());
        } else if (s == edit_timeonsite.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setTimeSite(s.toString());
        } else if (s == edit_travel_time_onsite.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setTravelTimeSite(s.toString());
        } else if (s == edit_companyname.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setCompanyName(s.toString());
        } else if (s == edit_contactperson.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setContactPerson(s.toString());
        } else if (s == edit_telnum.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setTelNo(s.toString());
        } else if (s == edit_mobnum.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setMobNo(s.toString());
        } else if (s == edit_siteaddrss.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setSiteAddress(s.toString());
        } else if (s == edit_additional_notes.getEditableText()) {
            observerFormThree.getFormthree().getResponse().getReport1().setNotes(s.toString());
        }
    }
}
