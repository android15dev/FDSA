package com.nkdroidsolutions.firedefence.fragment.Form_3;


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
import com.nkdroidsolutions.firedefence.activity.Add_From33_Data;
import com.nkdroidsolutions.firedefence.activity.FireForm3;
import com.nkdroidsolutions.firedefence.activity.VerticalSpaceItemDecoration;
import com.nkdroidsolutions.firedefence.adapters.Form33Adapter;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form3Model.AddDatum;
import com.nkdroidsolutions.firedefence.model.Form3Model.ExtinCheckProp;
import com.nkdroidsolutions.firedefence.model.GetAssignedForm3;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;
import java.util.List;


public class Form33 extends Fragment implements AllObserver {

    private static Form33 form33;
    private View convertView;
    private TextView txt_next, txt_back;
    private TextView txt_add;
    private RecyclerView recyclerView;
    private List<GetAssignedForm3> li = new ArrayList<>();
    private Form33Adapter rcAdapter;

    public static Form33 newInstance() {
        return new Form33();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form33, container, false);

        form33 = this;

        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initUI();
        if (observerFormThree.getFormthree() != null) {
            Log.d("aa", observerFormThree.getFormthree().getResponse().getReport3().getAddData().replace("\\\\", ""));

            if (TextUtils.isEmpty(observerFormThree.getFormthree().getResponse().getReport3().getAddData()) ||
                    observerFormThree.getFormthree().getResponse().getReport3().getAddData().equals("null")) {
                ExtinCheckProp extinCheckProp = new ExtinCheckProp();
                extinCheckProp.setAddData(new ArrayList<AddDatum>());
                observerFormThree.getFormthree().getResponse().getReport3().setExtinCheckProp(extinCheckProp);
            } else {
                observerFormThree.getFormthree().getResponse().getReport3().setExtinCheckProp(new Gson().fromJson(observerFormThree.getFormthree().getResponse().getReport3().getAddData().replace("\\\\", ""), ExtinCheckProp.class));
            }

            if (observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp() != null) {
                rcAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initUI() {
        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);

        txt_add = (TextView) convertView.findViewById(R.id.txt_add_more);
        recyclerView = (RecyclerView) convertView.findViewById(R.id.list_form3);

        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcAdapter = new Form33Adapter(Form33.this);
        recyclerView.setAdapter(rcAdapter);

        clicks();
    }

    private void clicks() {

        txt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), Add_From33_Data.class).putExtra(AppConstant.IS_NEW, true));
            }
        });

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                ((FireForm3) getActivity()).viewPager.setCurrentItem(3);
            }
        });

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                ((FireForm3) getActivity()).viewPager.setCurrentItem(1);
            }
        });
    }

    private void update() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp());
        Log.d("list", json);
        observerFormThree.getFormthree().getResponse().getReport3().setAddData(json);
    }

    public static Form33 getInstance() {
        return form33;
    }

    public void updateList() {
        rcAdapter.notifyDataSetChanged();
    }

  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != getActivity().RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case AppConstant.REQUEST_CODE_ADD_FORM3:
                GetAssignedForm3 form3 = (GetAssignedForm3) data.getSerializableExtra("data");
                li.add(form3);
                rcAdapter.notifyDataSetChanged();
        }

    }*/


}
