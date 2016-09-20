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
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.TripTest;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;
import java.util.Arrays;

public class Form24_4_Adapter extends RecyclerView.Adapter<Form24_4_Adapter.Form24ViewHolder> implements AllObserver {

    ArrayList<String> itemList = new ArrayList<>();
    Form24 context;


    public Form24_4_Adapter(Form24 context) {
        this.context = context;
        itemList.addAll(new ArrayList<String>(Arrays.asList(context.getActivity().getResources().getStringArray(R.array.alternate_trip))));

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

        TripTest tripTest = observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp().getTripTest().get(position);
        if (position == 0) {
            if (tripTest.getStartAirPressureDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (tripTest.getStartAirPressureNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(tripTest.getStartAirPressureComment());
        } else if (position == 1) {
            if (tripTest.getAcceleratorSetDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (tripTest.getAcceleratorSetNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(tripTest.getAcceleratorSetComment());
        } else if (position == 2) {
            if (tripTest.getTestValveDiaDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (tripTest.getTestValveDiaNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(tripTest.getTestValveDiaComment());
        } else if (position == 3) {
            if (tripTest.getTimeToTripDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (tripTest.getTimeToTripNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(tripTest.getTimeToTripComment());
        } else if (position == 4) {
            if (tripTest.getTimeToDischargeWaterDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (tripTest.getTimeToDischargeWaterNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(tripTest.getTimeToDischargeWaterComment());
        } else if (position == 5) {
            if (tripTest.getLocationTestTripValveDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (tripTest.getLocationTestTripValveNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(tripTest.getLocationTestTripValveComment());
        }

        holder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (position == 0) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setStartAirPressureDone(getCheckNumber(isChecked));
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setAcceleratorSetDone(getCheckNumber(isChecked));
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setTestValveDiaDone(getCheckNumber(isChecked));
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setTimeToTripDone(getCheckNumber(isChecked));
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setTimeToDischargeWaterDone(getCheckNumber(isChecked));
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setLocationTestTripValveDone(getCheckNumber(isChecked));
                }
            }
        });

        holder.chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (position == 0) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setStartAirPressureNa(getCheckNumber(isChecked));
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setAcceleratorSetNa(getCheckNumber(isChecked));
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setTestValveDiaNa(getCheckNumber(isChecked));
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setTimeToTripNa(getCheckNumber(isChecked));
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setTimeToDischargeWaterNa(getCheckNumber(isChecked));
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setLocationTestTripValveNa(getCheckNumber(isChecked));
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
                            .getTripTest().get(position).setStartAirPressureComment(s.toString());
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setAcceleratorSetComment(s.toString());
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setTestValveDiaComment(s.toString());
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setTimeToTripComment(s.toString());
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setTimeToDischargeWaterComment(s.toString());
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getTripTest().get(position).setLocationTestTripValveComment(s.toString());
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