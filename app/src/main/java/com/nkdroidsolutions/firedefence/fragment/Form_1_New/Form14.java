package com.nkdroidsolutions.firedefence.fragment.Form_1_New;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.Form1Activity;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sahil on 6/24/2016.
 */
public class Form14 extends Fragment implements AllObserver {

    View view;
    Context context;
    Activity activity;
    TextView back, next;
    EditText etNoteForm14_New, etSetNoForm14New, etStandingPressureBar, specialTestBar, specialTestHour, edit_comments;
    CheckBox cbTestedAsPerInstrucForm14New, cbAirTestForm14New, cbWetTestForm14New, cbStandingPressureForm14New, cbPhotoGageBeforeForm14New, cbPhotoGageUpomForm14New, reasonSpecialTest, specialTest;
    private ArrayList<String> checks_type = new ArrayList<String>();
    private ArrayList<String> checks_reson = new ArrayList<String>();

    public static Form14 newInstance() {
        return new Form14();
    }


    public Form14() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_form14_new, container, false);

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
            etNoteForm14_New.setText(observerFormOne.getFormone().getResponse().getReport4().getSysStateOnArrival());
            etSetNoForm14New.setText(observerFormOne.getFormone().getResponse().getReport4().getSetNo());
            etStandingPressureBar.setText(observerFormOne.getFormone().getResponse().getReport4().getBar1());
            specialTestBar.setText(observerFormOne.getFormone().getResponse().getReport4().getBar2());
            specialTestHour.setText(observerFormOne.getFormone().getResponse().getReport4().getHours());
            edit_comments.setText(observerFormOne.getFormone().getResponse().getReport4().getNotes());

            if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport4().getPressureTest())) {
                checks_type = new ArrayList<String>(Arrays.asList(observerFormOne.getFormone().getResponse().getReport4().getPressureTest().split(",")));

                for (String str : checks_type) {
                    if (str.equals("1")) {
                        cbTestedAsPerInstrucForm14New.setChecked(true);
                    } else if (str.equals("2")) {
                        cbAirTestForm14New.setChecked(true);
                    } else if (str.equals("3")) {
                        cbWetTestForm14New.setChecked(true);
                    } else if (str.equals("4")) {
                        cbStandingPressureForm14New.setChecked(true);
                    } else if (str.equals("5")) {
                        cbPhotoGageBeforeForm14New.setChecked(true);
                    } else if (str.equals("6")) {
                        cbPhotoGageUpomForm14New.setChecked(true);
                    }
                }
            }

            if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport4().getPtRes())) {
                checks_reson = new ArrayList<String>(Arrays.asList(observerFormOne.getFormone().getResponse().getReport4().getPtRes().split(",")));

                for (String str : checks_reson) {
                    if (str.equals("1")) {
                        reasonSpecialTest.setChecked(true);
                    } else if (str.equals("2")) {
                        specialTest.setChecked(true);
                    }
                }
            }
        }
        listners();
    }

    private void init() {

        cbTestedAsPerInstrucForm14New = (CheckBox) view.findViewById(R.id.cbTestedAsPerInstrucForm14New);
        cbAirTestForm14New = (CheckBox) view.findViewById(R.id.cbAirTestForm14New);
        cbWetTestForm14New = (CheckBox) view.findViewById(R.id.cbWetTestForm14New);
        cbStandingPressureForm14New = (CheckBox) view.findViewById(R.id.cbStandingPressureForm14New);
        cbPhotoGageBeforeForm14New = (CheckBox) view.findViewById(R.id.cbPhotoGageBeforeForm14New);
        specialTest = (CheckBox) view.findViewById(R.id.specialTest);
        reasonSpecialTest = (CheckBox) view.findViewById(R.id.reasonSpecialTest);
        cbPhotoGageUpomForm14New = (CheckBox) view.findViewById(R.id.cbPhotoGageUpomForm14New);
        etNoteForm14_New = (EditText) view.findViewById(R.id.etNoteForm14_New);
        etSetNoForm14New = (EditText) view.findViewById(R.id.etSetNoForm14New);
        etStandingPressureBar = (EditText) view.findViewById(R.id.etStandingPressureBar);
        specialTestBar = (EditText) view.findViewById(R.id.specialTestBar);
        specialTestHour = (EditText) view.findViewById(R.id.specialTestHour);
        edit_comments = (EditText) view.findViewById(R.id.edit_comments);
        back = (TextView) view.findViewById(R.id.back14);
        next = (TextView) view.findViewById(R.id.next14);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Form1Activity) getActivity()).viewPager.setCurrentItem(2);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("5")) {
                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(4);
                } else {
                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(5);
                }
            }
        });
    }

    private void listners() {
        etNoteForm14_New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport4().setSysStateOnArrival(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etSetNoForm14New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport4().setSetNo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etStandingPressureBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport4().setBar1(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        specialTestBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport4().setBar2(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        specialTestHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport4().setHours(s.toString());
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
                observerFormOne.getFormone().getResponse().getReport4().setNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cbTestedAsPerInstrucForm14New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("1", isChecked);
            }
        });

        cbAirTestForm14New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("2", isChecked);
            }
        });
        cbWetTestForm14New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("3", isChecked);
            }
        });
        cbStandingPressureForm14New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("4", isChecked);
            }
        });

        cbPhotoGageBeforeForm14New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("5", isChecked);
            }
        });
        cbPhotoGageUpomForm14New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("6", isChecked);
            }
        });

        reasonSpecialTest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Reason("1", isChecked);
            }
        });
        specialTest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Reason("2", isChecked);
            }
        });

    }

    private void setCheckChange_Type(String value, boolean isChecked) {
        if (isChecked) {
            checks_type.add(value);
        } else {
            checks_type.remove(value);
        }
        observerFormOne.getFormone().getResponse().getReport4().setPressureTest(TextUtils.join(",", checks_type));
    }

    private void setCheckChange_Reason(String value, boolean isChecked) {
        if (isChecked) {
            checks_reson.add(value);
        } else {
            checks_reson.remove(value);
        }
        observerFormOne.getFormone().getResponse().getReport4().setPtRes(TextUtils.join(",", checks_reson));
    }
}