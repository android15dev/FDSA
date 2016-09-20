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
import com.nkdroidsolutions.firedefence.fragment.Form_3.Form33;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form3Model.AddDatum;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

public class Form33Adapter extends RecyclerView.Adapter<Form33Adapter.Form33ViewHolder> implements AllObserver {

    Form33 context;


    public Form33Adapter(Form33 context) {
        this.context = context;
    }

    @Override
    public Form33ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lay_fire_from3, null);
        Form33ViewHolder rcv = new Form33ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(Form33ViewHolder holder, final int position) {

        AddDatum addDatum = observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().get(position);
        holder.txt_num.setText((position + 1) + "");
        holder.txt_location.setText(addDatum.getLocation());

        if (!TextUtils.isEmpty(addDatum.getManufacturer()) && !TextUtils.isEmpty(addDatum.getCapacity())
                && !TextUtils.isEmpty(addDatum.getComments()) && !TextUtils.isEmpty(addDatum.getLocation())
                && !TextUtils.isEmpty(addDatum.getManuDate()) && !TextUtils.isEmpty(addDatum.getNewTag())
                && !TextUtils.isEmpty(addDatum.getType()) && !TextUtils.isEmpty(addDatum.getServices())
                && !TextUtils.isEmpty(addDatum.getUpdateLabel()) && !TextUtils.isEmpty(addDatum.getWeight())) {
            holder.chk.setChecked(true);
        } else {
            holder.chk.setChecked(false);
        }

        holder.chk.setEnabled(false);

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getActivity(), Add_From33_Data.class).putExtra(AppConstant.IS_NEW, false).putExtra("position", position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().size();
    }

    public class Form33ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_num, txt_location;
        private CheckBox chk;
        private Button btn_edit;

        public Form33ViewHolder(View itemView) {
            super(itemView);
            txt_num = (TextView) itemView.findViewById(R.id.txt_fenum);
            txt_location = (TextView) itemView.findViewById(R.id.txt_location);
            chk = (CheckBox) itemView.findViewById(R.id.chk);
            btn_edit = (Button) itemView.findViewById(R.id.btn_edit);
        }
    }
}