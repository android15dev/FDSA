package com.nkdroidsolutions.firedefence.fragment.Form_4;


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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.AddDefect;
import com.nkdroidsolutions.firedefence.activity.VehicleForm4;
import com.nkdroidsolutions.firedefence.activity.VerticalSpaceItemDecoration;
import com.nkdroidsolutions.firedefence.adapters.Form45Adapter;
import com.nkdroidsolutions.firedefence.model.Form4Model.AddDefectProp;
import com.nkdroidsolutions.firedefence.model.GetAssignedForm4;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;
import java.util.List;


public class Form44 extends Fragment implements AllObserver {

    private View convertView;
    private TextView txt_next;
    private TextView txt_back;
    private TextView txt_add;
    private RecyclerView recyclerView;
    private Form45Adapter rcAdapter;
    private static Form44 form44;

    public static Form44 newInstance() {
        return new Form44();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form45, container, false);

        form44 = this;

        return convertView;
    }

    public static Form44 getInstance() {
        return form44;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initUI();
        clicks();

        if (observerFormFour.getFormfour() != null) {
            Log.d("aa", observerFormFour.getFormfour().getResponse().getReport4().getAddDefect().replace("\\\\", ""));

            if (TextUtils.isEmpty(observerFormFour.getFormfour().getResponse().getReport4().getAddDefect()) ||
                    observerFormFour.getFormfour().getResponse().getReport4().getAddDefect().equals("null")) {
                AddDefectProp addd = new AddDefectProp();
                addd.setAddDefect(new ArrayList<com.nkdroidsolutions.firedefence.model.Form4Model.AddDefect>());
                observerFormFour.getFormfour().getResponse().getReport4().setAddDefectProp(addd);
            } else {
                observerFormFour.getFormfour().getResponse().getReport4().setAddDefectProp(new Gson().fromJson(observerFormFour.getFormfour().getResponse().getReport4().getAddDefect().replace("\\\\", ""), AddDefectProp.class));
            }

            if (observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp() != null) {
                rcAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initUI() {

        txt_add = (TextView) convertView.findViewById(R.id.txt_add_more);
        recyclerView = (RecyclerView) convertView.findViewById(R.id.list_form4);

        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);

        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcAdapter = new Form45Adapter(Form44.this);
        recyclerView.setAdapter(rcAdapter);

    }


    private void clicks() {
        txt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), AddDefect.class));
            }
        });

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                ((VehicleForm4) getActivity()).viewPager.setCurrentItem(4);
            }
        });
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                ((VehicleForm4) getActivity()).viewPager.setCurrentItem(2);
            }
        });
    }

    private void update() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp());
        Log.d("list", json);
        observerFormFour.getFormfour().getResponse().getReport4().setAddDefect(json);
    }

    public void updateList() {
        rcAdapter.notifyDataSetChanged();
    }

}
