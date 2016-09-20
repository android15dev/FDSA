package com.nkdroidsolutions.firedefence.fragment.Form_2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.PumpActivity;
import com.nkdroidsolutions.firedefence.activity.ValveActivity;
import com.nkdroidsolutions.firedefence.activity.SprinklerForm2;
import com.nkdroidsolutions.firedefence.adapters.PumpAdapter;
import com.nkdroidsolutions.firedefence.adapters.ValveAdapter;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form2Model.pumps.PumpDataProp;
import com.nkdroidsolutions.firedefence.model.Form2Model.pumps.PumpDatum;
import com.nkdroidsolutions.firedefence.model.Form2Model.valves.ValveDataProp;
import com.nkdroidsolutions.firedefence.model.Form2Model.valves.ValveDatum;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;


public class Form25 extends Fragment implements AllObserver {

    private static Form25 form25;
    private View convertView;
    private TextView txt_next, txt_back;
    private Button btn_addpump, btn_addvalve;

    private RecyclerView recycler_pump, recycler_valve;
    private PumpAdapter pumpAdapter;
    private ValveAdapter valveAdapter;

    public static Form25 newInstance() {
        return new Form25();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form25, container, false);
        form25 = this;
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();

        if (observerFormTwo.getFormTwo() != null) {
            Log.d("getPumpData", observerFormTwo.getFormTwo().getResponse().getReport5().getPumpData().replace("\\\\", ""));
            Log.d("getValveData", observerFormTwo.getFormTwo().getResponse().getReport5().getValveData().replace("\\\\", ""));

            if (TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport5().getPumpData()) ||
                    observerFormTwo.getFormTwo().getResponse().getReport5().getPumpData().equals("null")) {
                PumpDataProp pumpDataProp = new PumpDataProp();
                pumpDataProp.setPumpData(new ArrayList<PumpDatum>());
                observerFormTwo.getFormTwo().getResponse().getReport5().setPumpDataProp(pumpDataProp);
            } else {
                observerFormTwo.getFormTwo().getResponse().getReport5().setPumpDataProp(new Gson().fromJson(observerFormTwo.getFormTwo().getResponse().getReport5().getPumpData().replace("\\\\", ""), PumpDataProp.class));
            }

            if (observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp() != null) {
                pumpAdapter.notifyDataSetChanged();
            }

            if (TextUtils.isEmpty(observerFormTwo.getFormTwo().getResponse().getReport5().getValveData()) ||
                    observerFormTwo.getFormTwo().getResponse().getReport5().getValveData().equals("null")) {
                ValveDataProp valveDataProp = new ValveDataProp();
                valveDataProp.setValveData(new ArrayList<ValveDatum>());
                observerFormTwo.getFormTwo().getResponse().getReport5().setValveDataProp(valveDataProp);
            } else {
                observerFormTwo.getFormTwo().getResponse().getReport5().setValveDataProp(new Gson().fromJson(observerFormTwo.getFormTwo().getResponse().getReport5().getValveData().replace("\\\\", ""), ValveDataProp.class));
            }

            if (observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp() != null) {
                valveAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initUI() {
        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);
        recycler_pump = (RecyclerView) convertView.findViewById(R.id.recycler_pump);
        recycler_valve = (RecyclerView) convertView.findViewById(R.id.recycler_valve);

        recycler_pump.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_valve.setLayoutManager(new LinearLayoutManager(getActivity()));

        pumpAdapter = new PumpAdapter(Form25.this);
        recycler_pump.setAdapter(pumpAdapter);

        valveAdapter = new ValveAdapter(Form25.this);
        recycler_valve.setAdapter(valveAdapter);

        btn_addpump = (Button) convertView.findViewById(R.id.btn_addpump);
        btn_addvalve = (Button) convertView.findViewById(R.id.btn_addvalve);

        clicks();
    }

    private void clicks() {

        btn_addpump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PumpActivity.class).putExtra(AppConstant.IS_NEW, true));
            }
        });

        btn_addvalve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ValveActivity.class).putExtra(AppConstant.IS_NEW, true));
            }
        });

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(5);
            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                ((SprinklerForm2) getActivity()).viewPager.setCurrentItem(3);
            }
        });
    }

    private void update() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json_pump = gson.toJson(observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp());
        Log.d("json_pump", json_pump);
        observerFormTwo.getFormTwo().getResponse().getReport5().setPumpData(json_pump);

        String json_valve = gson.toJson(observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp());
        Log.d("json_valve", json_valve);
        observerFormTwo.getFormTwo().getResponse().getReport5().setValveData(json_valve);
    }

    public static Form25 getInstance() {
        return form25;
    }

    public void updatePumpList() {
        pumpAdapter.notifyDataSetChanged();
    }

    public void updateValveList() {
        valveAdapter.notifyDataSetChanged();
    }

}
