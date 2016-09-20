package com.nkdroidsolutions.firedefence.fragment.Form_2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.SprinklerForm2;
import com.nkdroidsolutions.firedefence.adapters.Form24_1_Adapter;
import com.nkdroidsolutions.firedefence.adapters.Form24_2_Adapter;
import com.nkdroidsolutions.firedefence.adapters.Form24_3_Adapter;
import com.nkdroidsolutions.firedefence.adapters.Form24_4_Adapter;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.ChecksDoneProp;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.HalfYearly;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.Quarterly;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.TripTest;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.Yearly;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;


public class Form24 extends Fragment implements AllObserver {


    private static Form24 act;

    private View convertView;
    private TextView txt_next, txt_back;
    private TextView txt_heading;

    public static int count = 0;
    private String[] arr_quaterly, arr_half_yearly, arr_yearly, arr_alternate_trip;
    private RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4;
    private EditText edit_comments;
    private Form24_1_Adapter rcAdapter1;
    private Form24_2_Adapter rcAdapter2;
    private Form24_3_Adapter rcAdapter3;
    private Form24_4_Adapter rcAdapter4;

    public static Form24 newInstance() {
        return new Form24();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form24, container, false);

        act = this;

        arr_quaterly = getActivity().getResources().getStringArray(R.array.quarterly);
        arr_half_yearly = getActivity().getResources().getStringArray(R.array.half_yearly);
        arr_yearly = getActivity().getResources().getStringArray(R.array.yearly);
        arr_alternate_trip = getActivity().getResources().getStringArray(R.array.alternate_trip);

        return convertView;
    }

    public static Form24 getInstance() {
        return act;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();

        if (observerFormTwo.getFormTwo() != null) {
            edit_comments.setText(observerFormTwo.getFormTwo().getResponse().getReport4().getNotes2());

            String data = observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDone().replace("\\\\", "");
            Log.d("aa", observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDone().replace("\\\\", ""));

            if (TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDone()) ||
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDone().equals("null")) {
                ChecksDoneProp checksDoneProp = new ChecksDoneProp();
                ArrayList<Quarterly> li_quar = new ArrayList<>();
                for (int i = 0; i < 21; i++) {
                    li_quar.add(new Quarterly());
                }
                checksDoneProp.setQuarterly(li_quar);
                ArrayList<HalfYearly> li_half = new ArrayList<>();
                for (int i = 0; i < 21; i++) {
                    li_half.add(new HalfYearly());
                }
                checksDoneProp.setHalfYearly(li_half);
                ArrayList<Yearly> li_year = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    li_year.add(new Yearly());
                }
                checksDoneProp.setYearly(li_year);
                ArrayList<TripTest> li_trip = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    li_trip.add(new TripTest());
                }
                checksDoneProp.setTripTest(li_trip);
                observerFormTwo.getFormTwo().getResponse().getReport4().setChecksDoneProp(checksDoneProp);
            } else {
                observerFormTwo.getFormTwo().getResponse().getReport4().setChecksDoneProp(new Gson().fromJson(observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDone().replace("\\\\", ""), ChecksDoneProp.class));
            }

            if (observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp() != null) {
                rcAdapter1 = new Form24_1_Adapter(Form24.this);
                recyclerView1.setAdapter(rcAdapter1);

                rcAdapter2 = new Form24_2_Adapter(Form24.this);
                recyclerView2.setAdapter(rcAdapter2);

                rcAdapter3 = new Form24_3_Adapter(Form24.this);
                recyclerView3.setAdapter(rcAdapter3);

                rcAdapter4 = new Form24_4_Adapter(Form24.this);
                recyclerView4.setAdapter(rcAdapter4);
            }

        }

        listners();

    }

    private void listners() {
        edit_comments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormTwo.getFormTwo().getResponse().getReport4().setNotes2(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initUI() {
        edit_comments = (EditText) convertView.findViewById(R.id.edit_comments);

        txt_heading = (TextView) convertView.findViewById(R.id.txt_heading);
        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);
        recyclerView1 = (RecyclerView) convertView.findViewById(R.id.list_form2_1);
        recyclerView2 = (RecyclerView) convertView.findViewById(R.id.list_form2_2);
        recyclerView3 = (RecyclerView) convertView.findViewById(R.id.list_form2_3);
        recyclerView4 = (RecyclerView) convertView.findViewById(R.id.list_form2_4);

        txt_heading.setText("Checks Done");
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView4.setLayoutManager(new LinearLayoutManager(getActivity()));


        clicks();
    }

    protected void updateList() {
        if (observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems().contains("1")) {
            recyclerView1.setVisibility(View.VISIBLE);
        } else {
            recyclerView1.setVisibility(View.GONE);
        }
        if (observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems().contains("2")) {
            recyclerView2.setVisibility(View.VISIBLE);
        } else {
            recyclerView2.setVisibility(View.GONE);
        }
        if (observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems().contains("3")) {
            recyclerView3.setVisibility(View.VISIBLE);
        } else {
            recyclerView3.setVisibility(View.GONE);
        }
        if (observerFormTwo.getFormTwo().getResponse().getReport3().getTypesOfSystems().contains("4")) {
            recyclerView4.setVisibility(View.VISIBLE);
        } else {
            recyclerView4.setVisibility(View.GONE);
        }
    }

    private void clicks() {
        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(4);
            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(2);
            }
        });
    }

    private void update() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp());
        Log.d("list", json);
        observerFormTwo.getFormTwo().getResponse().getReport4().setChecksDone(json);
    }

}