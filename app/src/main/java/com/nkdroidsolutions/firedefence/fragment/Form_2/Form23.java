package com.nkdroidsolutions.firedefence.fragment.Form_2;


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

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.SprinklerForm2;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;
import java.util.Arrays;


public class Form23 extends Fragment implements AllObserver {


    private CheckBox rbtn_quarterly, rbtn_half_year, rbtn_year, rbtn_alternate;
    public static String valuesAr;

    private View convertView;
    private TextView txt_next, txt_back;
    private EditText edit_comments;
    private ArrayList<String> checks = new ArrayList<String>();

    public static Form23 newInstance() {
        return new Form23();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form23, container, false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();

        if (observerFormTwo.getFormTwo() != null) {
            edit_comments.setText(observerFormTwo.getFormTwo().getResponse().getReport3().getSystemNotes());

            if (!TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems())) {
                checks = new ArrayList<String>(Arrays.asList(observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems().split(",")));

                for (String str : checks) {
                    if (str.equals("1")) {
                        rbtn_quarterly.setChecked(true);
                    } else if (str.equals("2")) {
                        rbtn_half_year.setChecked(true);
                    } else if (str.equals("3")) {
                        rbtn_year.setChecked(true);
                    } else if (str.equals("4")) {
                        rbtn_alternate.setChecked(true);
                    }
                }
            }
        }

        listners();

    }

    private void initUI() {
        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);
        rbtn_quarterly = (CheckBox) convertView.findViewById(R.id.rbtn_quarterly);
        rbtn_half_year = (CheckBox) convertView.findViewById(R.id.rbtn_half_year);
        rbtn_year = (CheckBox) convertView.findViewById(R.id.rbtn_year);
        rbtn_alternate = (CheckBox) convertView.findViewById(R.id.rbtn_alternate);
        edit_comments = (EditText) convertView.findViewById(R.id.edit_comments);
        clicks();
    }

    private void clicks() {

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*ArrayList<String> al = new ArrayList<String>();

                if(rbtn_year.isChecked())
                {
                    al.add("yearly");
                }

                if(rbtn_quarterly.isChecked())
                {
                    al.add("quater");
                }

                if(rbtn_half_year.isChecked())
                {
                    al.add("halfyear");
                }

                if(rbtn_alternate.isChecked())
                {
                    al.add("alternate");
                }

                StringBuilder sb = new StringBuilder();
                for(int i=0;i<al.size();i++)
                {
                    sb.append(al.get(i) + ",");
                }

                Log.e("finalvalue",sb.toString());
                valuesAr = sb.toString();

                Intent in = new Intent();
                in.putExtra("value",valuesAr);
                in.setAction("value");
                getActivity().sendBroadcast(in);*/

               // Log.e("sending broadcast", "sending broadcast");
                Form24.getInstance().updateList();
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(3);


            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(1);
            }
        });
    }

    private void listners() {
        edit_comments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormTwo.getFormTwo().getResponse().getReport3().setSystemNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rbtn_quarterly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange("1", isChecked);
            }
        });

        rbtn_half_year.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange("2", isChecked);
            }
        });
        rbtn_year.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange("3", isChecked);
            }
        });
        rbtn_alternate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckChange("4", isChecked);
            }
        });

    }

    private void setCheckChange(String value, boolean isChecked) {
        if (isChecked) {
            checks.add(value);
        } else {
            checks.remove(value);
        }
        observerFormTwo.getFormTwo().getResponse().getReport3().setTypesOfSystems(TextUtils.join(",", checks));
    }


}
