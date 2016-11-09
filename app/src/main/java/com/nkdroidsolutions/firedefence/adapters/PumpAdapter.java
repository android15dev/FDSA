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
import com.nkdroidsolutions.firedefence.activity.Add_From33_Data;
import com.nkdroidsolutions.firedefence.activity.PumpActivity;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form25;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form2Model.pumps.PumpDatum;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

/**
 * Created by Sahil on 5/11/2016.
 */
public class PumpAdapter extends RecyclerView.Adapter<PumpAdapter.PumpListViewHolder> implements AllObserver {

    Form25 context;

    public PumpAdapter(Form25 context) {
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

        PumpDatum pumpDatum = observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp().getPumpData().get(position);
        holder.txt.setText(pumpDatum.getPumpNo());

        if (!TextUtils.isEmpty(pumpDatum.getDiesel()) && !TextUtils.isEmpty(pumpDatum.getElectric())
                && !TextUtils.isEmpty(pumpDatum.getPumpNo()) && !TextUtils.isEmpty(pumpDatum.getPumpStartReading())
                && !TextUtils.isEmpty(pumpDatum.getPumpStopReading()) && !TextUtils.isEmpty(pumpDatum.getMotorAge())
                && !TextUtils.isEmpty(pumpDatum.getPumpMake()) && !TextUtils.isEmpty(pumpDatum.getMotorNotes())
                && !TextUtils.isEmpty(pumpDatum.getPumpModel()) && !TextUtils.isEmpty(pumpDatum.getPoint1Flow())
                && !TextUtils.isEmpty(pumpDatum.getPumpDia()) && !TextUtils.isEmpty(pumpDatum.getPoint1Pressure())
                && !TextUtils.isEmpty(pumpDatum.getPumpAge()) /*&& !TextUtils.isEmpty(pumpDatum.getPoint1Diesel())*/
                && !TextUtils.isEmpty(pumpDatum.getMotorMake()) /*&& !TextUtils.isEmpty(pumpDatum.getPoint1Electric())*/
                && !TextUtils.isEmpty(pumpDatum.getMotorModel()) && !TextUtils.isEmpty(pumpDatum.getPoint2Diesel())
                && !TextUtils.isEmpty(pumpDatum.getMotorDia()) && !TextUtils.isEmpty(pumpDatum.getPoint2Electric())
                && !TextUtils.isEmpty(pumpDatum.getPoint3Flow()) && !TextUtils.isEmpty(pumpDatum.getPoint2Flow())
                && !TextUtils.isEmpty(pumpDatum.getHasPump()) && !TextUtils.isEmpty(pumpDatum.getPoint2Pressure())
                && !TextUtils.isEmpty(pumpDatum.getLowDateinstalledPump()) && !TextUtils.isEmpty(pumpDatum.getPoint3Electric())
                && !TextUtils.isEmpty(pumpDatum.getLowMakePump()) && !TextUtils.isEmpty(pumpDatum.getPoint3Diesel())
                && !TextUtils.isEmpty(pumpDatum.getLowModelPump()) && !TextUtils.isEmpty(pumpDatum.getPoint3Pressure())) {
            holder.chk.setChecked(true);
        } else {
            holder.chk.setChecked(false);
        }

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getActivity(), PumpActivity.class).putExtra(AppConstant.IS_NEW, false).putExtra("position", position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp().getPumpData().size();
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
