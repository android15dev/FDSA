package com.nkdroidsolutions.firedefence.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.PumpActivity;
import com.nkdroidsolutions.firedefence.activity.ValveActivity;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form25;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form2Model.valves.ValveDatum;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

/**
 * Created by Sahil on 5/11/2016.
 */
public class ValveAdapter extends RecyclerView.Adapter<ValveAdapter.PumpListViewHolder> implements AllObserver {

    Form25 context;

    public ValveAdapter(Form25 context) {
        this.context = context;
    }

    @Override
    public PumpListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_addpump, null);
        PumpListViewHolder rcv = new PumpListViewHolder(layoutView);
        return rcv;
    }


    @Override
    public void onBindViewHolder(PumpListViewHolder holder, final int position) {

        ValveDatum valveDatum = observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp().getValveData().get(position);
        holder.txt.setText(valveDatum.getValveNumber());

        if (!TextUtils.isEmpty(valveDatum.getValveNumber()) && !TextUtils.isEmpty(valveDatum.getFlowValve())
                && !TextUtils.isEmpty(valveDatum.getPressureValve()) && !TextUtils.isEmpty(valveDatum.getHasPumpValve())
                && !TextUtils.isEmpty(valveDatum.getCBefore()) && !TextUtils.isEmpty(valveDatum.getValveImage1())
                && !TextUtils.isEmpty(valveDatum.getCAfter()) && !TextUtils.isEmpty(valveDatum.getValveImage2())
                && !TextUtils.isEmpty(valveDatum.getMakeValvePump()) && !TextUtils.isEmpty(valveDatum.getValveImage3())
                && !TextUtils.isEmpty(valveDatum.getDiaValve()) && !TextUtils.isEmpty(valveDatum.getMakePumpValve())
                && !TextUtils.isEmpty(valveDatum.getModelValve()) && !TextUtils.isEmpty(valveDatum.getModelPumpValve())
                && !TextUtils.isEmpty(valveDatum.getAgeValve()) && !TextUtils.isEmpty(valveDatum.getDateinstalledPumpValve())
                && !TextUtils.isEmpty(valveDatum.getNotesValve())) {
            holder.chk.setChecked(true);
        } else {
            holder.chk.setChecked(false);
        }

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getActivity(), ValveActivity.class).putExtra(AppConstant.IS_NEW, false).putExtra("position", position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp().getValveData().size();
    }


    public class PumpListViewHolder extends RecyclerView.ViewHolder {

        private TextView txt;
        private CheckBox chk;
        private Button btn_edit;


        public PumpListViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt);
            chk = (CheckBox) itemView.findViewById(R.id.chk);
            btn_edit = (Button) itemView.findViewById(R.id.btn_edit);
        }
    }
}
