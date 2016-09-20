package com.nkdroidsolutions.firedefence.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form24;
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.Yearly;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;
import java.util.Arrays;

public class Form24_3_Adapter extends RecyclerView.Adapter<Form24_3_Adapter.Form24ViewHolder> implements AllObserver {

    ArrayList<String> itemList = new ArrayList<>();
    Form24 context;


    public Form24_3_Adapter(Form24 context) {
        this.context = context;
        itemList.addAll(new ArrayList<String>(Arrays.asList(context.getActivity().getResources().getStringArray(R.array.yearly))));

    }


    @Override
    public Form24ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lay_checkbox, null);
        Form24ViewHolder rcv = new Form24ViewHolder(layoutView);
        return rcv;
    }


    @Override
    public void onBindViewHolder(Form24ViewHolder holder, final int position) {
        holder.txt.setText(itemList.get(position));

        Yearly yearly = observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp().getYearly().get(position);
        if (position == 0) {
            if (yearly.getPumpsGeneralInspectionDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (yearly.getPumpsGeneralInspectionNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(yearly.getPumpsGeneralInspectionComment());
        } else if (position == 1) {
            if (yearly.getLooseGaurdBoltsDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (yearly.getLooseGaurdBoltsNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(yearly.getLooseGaurdBoltsComment());
        } else if (position == 2) {
            if (yearly.getFluidLeaksDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (yearly.getFluidLeaksNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(yearly.getFluidLeaksComment());
        } else if (position == 3) {
            if (yearly.getDieselPumpRunHalfHourDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (yearly.getDieselPumpRunHalfHourNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(yearly.getDieselPumpRunHalfHourComment());
        } else if (position == 4) {
            if (yearly.getElectricPumpRun10minDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (yearly.getElectricPumpRun10minNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(yearly.getElectricPumpRun10minComment());
        } else if (position == 5) {
            if (yearly.getOilFilterChangeDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (yearly.getOilFilterChangeNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(yearly.getOilFilterChangeComment());
        } else if (position == 6) {
            if (yearly.getStorageTankDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (yearly.getStorageTankNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(yearly.getStorageTankComment());
        }

        holder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (position == 0) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setPumpsGeneralInspectionDone(getCheckNumber(isChecked));
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setLooseGaurdBoltsDone(getCheckNumber(isChecked));
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setFluidLeaksDone(getCheckNumber(isChecked));
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setDieselPumpRunHalfHourDone(getCheckNumber(isChecked));
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setElectricPumpRun10minDone(getCheckNumber(isChecked));
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setOilFilterChangeDone(getCheckNumber(isChecked));
                } else if (position == 6) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setStorageTankDone(getCheckNumber(isChecked));
                }
            }
        });

        holder.chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (position == 0) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setPumpsGeneralInspectionNa(getCheckNumber(isChecked));
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setLooseGaurdBoltsNa(getCheckNumber(isChecked));
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setFluidLeaksNa(getCheckNumber(isChecked));
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setDieselPumpRunHalfHourNa(getCheckNumber(isChecked));
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setElectricPumpRun10minNa(getCheckNumber(isChecked));
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setOilFilterChangeNa(getCheckNumber(isChecked));
                } else if (position == 6) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setStorageTankNa(getCheckNumber(isChecked));
                }
            }
        });

        holder.edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (position == 0) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setPumpsGeneralInspectionComment(s.toString());
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setLooseGaurdBoltsComment(s.toString());
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setFluidLeaksComment(s.toString());
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setDieselPumpRunHalfHourComment(s.toString());
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setElectricPumpRun10minComment(s.toString());
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setOilFilterChangeComment(s.toString());
                } else if (position == 6) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getYearly().get(position).setStorageTankComment(s.toString());
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class Form24ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox chk, chk1;
        private EditText edit;
        private TextView txt;

        public Form24ViewHolder(View itemView) {
            super(itemView);
            chk = (CheckBox) itemView.findViewById(R.id.chk);
            chk1 = (CheckBox) itemView.findViewById(R.id.chk1);
            txt = (TextView) itemView.findViewById(R.id.txt);
            edit = (EditText) itemView.findViewById(R.id.edit_comments);
        }

    }

    private String getCheckNumber(boolean b) {
        if (b) {
            return "1";
        } else {
            return "0";
        }
    }

}