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
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.Quarterly;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;
import java.util.Arrays;

public class Form24_1_Adapter extends RecyclerView.Adapter<Form24_1_Adapter.Form24ViewHolder> implements AllObserver {

    ArrayList<String> itemList = new ArrayList<>();
    Form24 context;


    public Form24_1_Adapter(Form24 context) {
        this.context = context;
        itemList.addAll(new ArrayList<String>(Arrays.asList(context.getActivity().getResources().getStringArray(R.array.quarterly))));

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

        Quarterly quarterly = observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp().getQuarterly().get(position);
        if (position == 0) {
            if (quarterly.getRiskLayoutChangesDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getRiskLayoutChangesNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getRiskLayoutChangesComment());
        } else if (position == 1) {
            if (quarterly.getOccupacyChangesDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getOccupacyChangesNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getOccupacyChangesComment());
        } else if (position == 2) {
            if (quarterly.getHeatingChangesDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getHeatingChangesNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getHeatingChangesComment());
        } else if (position == 3) {
            if (quarterly.getLightingChangesDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getLightingChangesNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getLightingChangesComment());
        } else if (position == 4) {
            if (quarterly.getEquipmentChangesDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getEquipmentChangesNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getEquipmentChangesComment());
        } else if (position == 5) {
            if (quarterly.getWaterClassificationDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getWaterClassificationNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getWaterClassificationComment());
        } else if (position == 6) {
            if (quarterly.getSprinklerObstructionDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getSprinklerObstructionNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getSprinklerObstructionComment());
        } else if (position == 7) {
            if (quarterly.getClearanceOverStockDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getClearanceOverStockNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getClearanceOverStockComment());
        } else if (position == 8) {
            if (quarterly.getSprinklerTempRateDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getSprinklerTempRateNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getSprinklerTempRateComment());
        } else if (position == 9) {
            if (quarterly.getSprinklerConditionDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getSprinklerConditionNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getSprinklerConditionComment());
        } else if (position == 10) {
            if (quarterly.getPipworkConditionDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getPipworkConditionNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getPipworkConditionComment());
        } else if (position == 11) {
            if (quarterly.getHangerConditionDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getHangerConditionNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getHangerConditionComment());
        } else if (position == 12) {
            if (quarterly.getElectricalConnectionDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getElectricalConnectionNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getElectricalConnectionComment());
        } else if (position == 13) {
            if (quarterly.getAlarmValveProvingPipeDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getAlarmValveProvingPipeNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getAlarmValveProvingPipeComment());
        } else if (position == 14) {
            if (quarterly.getPumpAutoStartDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getPumpAutoStartNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getPumpAutoStartComment());
        } else if (position == 15) {
            if (quarterly.getBatteryLevelDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getBatteryLevelNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getBatteryLevelComment());
        } else if (position == 16) {
            if (quarterly.getBatteryDensityDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getBatteryDensityNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getBatteryDensityComment());
        } else if (position == 17) {
            if (quarterly.getStopValvesDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getStopValvesNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getStopValvesComment());
        } else if (position == 18) {
            if (quarterly.getFlowSwitchesDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getFlowSwitchesNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getFlowSwitchesComment());
        } else if (position == 19) {
            if (quarterly.getSparesDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getSparesNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getSparesComment());
        } else if (position == 20) {
            if (quarterly.getHighestSprinklerDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (quarterly.getHighestSprinklerNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(quarterly.getHighestSprinklerComment());
        }

        holder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (position == 0) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setRiskLayoutChangesDone(getCheckNumber(isChecked));
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setOccupacyChangesDone(getCheckNumber(isChecked));
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setHeatingChangesDone(getCheckNumber(isChecked));
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setLightingChangesDone(getCheckNumber(isChecked));
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setEquipmentChangesDone(getCheckNumber(isChecked));
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setWaterClassificationDone(getCheckNumber(isChecked));
                } else if (position == 6) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSprinklerObstructionDone(getCheckNumber(isChecked));
                } else if (position == 7) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setClearanceOverStockDone(getCheckNumber(isChecked));
                } else if (position == 8) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSprinklerTempRateDone(getCheckNumber(isChecked));
                } else if (position == 9) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSprinklerConditionDone(getCheckNumber(isChecked));
                } else if (position == 10) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setPipworkConditionDone(getCheckNumber(isChecked));
                } else if (position == 11) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setHangerConditionDone(getCheckNumber(isChecked));
                } else if (position == 12) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setElectricalConnectionDone(getCheckNumber(isChecked));
                } else if (position == 13) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setAlarmValveProvingPipeDone(getCheckNumber(isChecked));
                } else if (position == 14) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setPumpAutoStartDone(getCheckNumber(isChecked));
                } else if (position == 15) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setBatteryLevelDone(getCheckNumber(isChecked));
                } else if (position == 16) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setBatteryDensityDone(getCheckNumber(isChecked));
                } else if (position == 17) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setStopValvesDone(getCheckNumber(isChecked));
                } else if (position == 18) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setFlowSwitchesDone(getCheckNumber(isChecked));
                } else if (position == 19) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSparesDone(getCheckNumber(isChecked));
                } else if (position == 20) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setHighestSprinklerDone(getCheckNumber(isChecked));
                }
            }
        });

        holder.chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (position == 0) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setRiskLayoutChangesNa(getCheckNumber(isChecked));
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setOccupacyChangesNa(getCheckNumber(isChecked));
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setHeatingChangesNa(getCheckNumber(isChecked));
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setLightingChangesNa(getCheckNumber(isChecked));
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setEquipmentChangesNa(getCheckNumber(isChecked));
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setWaterClassificationNa(getCheckNumber(isChecked));
                } else if (position == 6) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSprinklerObstructionNa(getCheckNumber(isChecked));
                } else if (position == 7) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setClearanceOverStockNa(getCheckNumber(isChecked));
                } else if (position == 8) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSprinklerTempRateNa(getCheckNumber(isChecked));
                } else if (position == 9) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSprinklerConditionNa(getCheckNumber(isChecked));
                } else if (position == 10) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setPipworkConditionNa(getCheckNumber(isChecked));
                } else if (position == 11) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setHangerConditionNa(getCheckNumber(isChecked));
                } else if (position == 12) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setElectricalConnectionNa(getCheckNumber(isChecked));
                } else if (position == 13) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setAlarmValveProvingPipeNa(getCheckNumber(isChecked));
                } else if (position == 14) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setPumpAutoStartNa(getCheckNumber(isChecked));
                } else if (position == 15) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setBatteryLevelNa(getCheckNumber(isChecked));
                } else if (position == 16) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setBatteryDensityNa(getCheckNumber(isChecked));
                } else if (position == 17) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setStopValvesNa(getCheckNumber(isChecked));
                } else if (position == 18) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setFlowSwitchesNa(getCheckNumber(isChecked));
                } else if (position == 19) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSparesNa(getCheckNumber(isChecked));
                } else if (position == 20) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setHighestSprinklerNa(getCheckNumber(isChecked));
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
                            .getQuarterly().get(position).setRiskLayoutChangesComment(s.toString());
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setOccupacyChangesComment(s.toString());
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setHeatingChangesComment(s.toString());
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setLightingChangesComment(s.toString());
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setEquipmentChangesComment(s.toString());
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setWaterClassificationComment(s.toString());
                } else if (position == 6) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSprinklerObstructionComment(s.toString());
                } else if (position == 7) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setClearanceOverStockComment(s.toString());
                } else if (position == 8) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSprinklerTempRateComment(s.toString());
                } else if (position == 9) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSprinklerConditionComment(s.toString());
                } else if (position == 10) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setPipworkConditionComment(s.toString());
                } else if (position == 11) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setHangerConditionComment(s.toString());
                } else if (position == 12) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setElectricalConnectionComment(s.toString());
                } else if (position == 13) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setAlarmValveProvingPipeComment(s.toString());
                } else if (position == 14) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setPumpAutoStartComment(s.toString());
                } else if (position == 15) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setBatteryLevelComment(s.toString());
                } else if (position == 16) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setBatteryDensityComment(s.toString());
                } else if (position == 17) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setStopValvesComment(s.toString());
                } else if (position == 18) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setFlowSwitchesComment(s.toString());
                } else if (position == 19) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setSparesComment(s.toString());
                } else if (position == 20) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getQuarterly().get(position).setHighestSprinklerComment(s.toString());
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