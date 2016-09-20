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
public class Form13 extends Fragment implements AllObserver {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    Context context;
    Activity activity;
    TextView back, next;
    CheckBox cbWaterForm13_New, cbAlarmForm13_New, cbGaseuousForm13_New, cbHalonsForm13_New, cbInstallationForm13_New, cbServiceForm13_New,
            cbEmergencyForm13_New, cbSiteInstructionForm13_New, cbPressureTestForm13_New;
    EditText etOtherTypeofSystemForm13New, etReasonForVisitForm13New;
    private ArrayList<String> checks_reson = new ArrayList<String>();
    private ArrayList<String> checks_type = new ArrayList<String>();

    public static Form13 newInstance() {
        return new Form13();
    }


    public Form13() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_form13_new, container, false);

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
            etOtherTypeofSystemForm13New.setText(observerFormOne.getFormone().getResponse().getReport3().getOtherSystemType());
            etReasonForVisitForm13New.setText(observerFormOne.getFormone().getResponse().getReport3().getOtherResonToVisit());

            if(!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport3().getTypesOfSystems())) {
                checks_type = new ArrayList<String>(Arrays.asList(observerFormOne.getFormone().getResponse().getReport3().getTypesOfSystems().split(",")));

                for (String str : checks_type) {
                    if (str.equals("1")) {
                        cbWaterForm13_New.setChecked(true);
                    } else if (str.equals("2")) {
                        cbAlarmForm13_New.setChecked(true);
                    } else if (str.equals("3")) {
                        cbGaseuousForm13_New.setChecked(true);
                    } else if (str.equals("4")) {
                        cbHalonsForm13_New.setChecked(true);
                    }
                }
            }
            if(!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit())) {
                checks_reson = new ArrayList<String>(Arrays.asList(observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().split(",")));

                for (String str : checks_reson) {
                    if (str.equals("1")) {
                        cbInstallationForm13_New.setChecked(true);
                    } else if (str.equals("2")) {
                        cbServiceForm13_New.setChecked(true);
                    } else if (str.equals("3")) {
                        cbEmergencyForm13_New.setChecked(true);
                    } else if (str.equals("4")) {
                        cbSiteInstructionForm13_New.setChecked(true);
                    } else if (str.equals("5")) {
                        cbPressureTestForm13_New.setChecked(true);
                    }
                }
            }

        }
        listners();

    }

    private void init() {

        etOtherTypeofSystemForm13New = (EditText) view.findViewById(R.id.etOtherTypeofSystemForm13New);
        etReasonForVisitForm13New = (EditText) view.findViewById(R.id.etReasonForVisitForm13New);
        back = (TextView) view.findViewById(R.id.back13);
        next = (TextView) view.findViewById(R.id.next13);
        cbWaterForm13_New = (CheckBox) view.findViewById(R.id.cbWaterForm13_New);
        cbAlarmForm13_New = (CheckBox) view.findViewById(R.id.cbAlarmForm13_New);
        cbGaseuousForm13_New = (CheckBox) view.findViewById(R.id.cbGaseuousForm13_New);
        cbHalonsForm13_New = (CheckBox) view.findViewById(R.id.HalonsForm13_New);
        cbInstallationForm13_New = (CheckBox) view.findViewById(R.id.cbInstallationForm13_New);
        cbServiceForm13_New = (CheckBox) view.findViewById(R.id.cbServiceForm13_New);
        cbEmergencyForm13_New = (CheckBox) view.findViewById(R.id.cbEmergencyForm13_New);
        cbSiteInstructionForm13_New = (CheckBox) view.findViewById(R.id.cbSiteInstructionForm13_New);
        cbPressureTestForm13_New = (CheckBox) view.findViewById(R.id.cbPressureTestForm13_New);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Form1Activity) getActivity()).viewPager.setCurrentItem(1);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("1") ||
                        observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("2") ||
                        observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("3") ||
                        observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("4")) {

                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(3);
                } else if (observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("5")) {
                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(4);
                } else {
                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(5);
                }

            }
        });


    }

    private void listners() {
        etOtherTypeofSystemForm13New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport3().setOtherSystemType(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etReasonForVisitForm13New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport3().setOtherResonToVisit(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cbWaterForm13_New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("1", isChecked);
            }
        });

        cbAlarmForm13_New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("2", isChecked);
            }
        });
        cbGaseuousForm13_New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("3", isChecked);
            }
        });
        cbHalonsForm13_New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Type("4", isChecked);
            }
        });

        cbInstallationForm13_New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Reason("1", isChecked);
            }
        });
        cbServiceForm13_New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Reason("2", isChecked);
            }
        });
        cbEmergencyForm13_New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Reason("3", isChecked);
            }
        });
        cbSiteInstructionForm13_New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Reason("4", isChecked);
            }
        });
        cbPressureTestForm13_New.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange_Reason("5", isChecked);
            }
        });

    }

    private void setCheckChange_Type(String value, boolean isChecked) {
        if (isChecked) {
            checks_type.add(value);
        } else {
            checks_type.remove(value);
        }
        observerFormOne.getFormone().getResponse().getReport3().setTypesOfSystems(TextUtils.join(",", checks_type));
    }

    private void setCheckChange_Reason(String value, boolean isChecked) {
        if (isChecked) {
            checks_reson.add(value);
        } else {
            checks_reson.remove(value);
        }
        observerFormOne.getFormone().getResponse().getReport3().setReasonForVisit(TextUtils.join(",", checks_reson));
    }

}

