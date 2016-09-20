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
import com.nkdroidsolutions.firedefence.model.Form2Model.checks.HalfYearly;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.ArrayList;
import java.util.Arrays;

public class Form24_2_Adapter extends RecyclerView.Adapter<Form24_2_Adapter.Form24ViewHolder> implements AllObserver {

    ArrayList<String> itemList = new ArrayList<>();
    Form24 context;


    public Form24_2_Adapter(Form24 context) {
        this.context = context;
        itemList.addAll(new ArrayList<String>(Arrays.asList(context.getActivity().getResources().getStringArray(R.array.half_yearly))));

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

        HalfYearly halfYearly = observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp().getHalfYearly().get(position);
        if (position == 0) {
            if (halfYearly.getAlarmValveInspectionDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getAlarmValveInspectionNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getAlarmValveInspectionComment());
        } else if (position == 1) {
            if (halfYearly.getAirValveInspectionDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getAirValveInspectionNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getAirValveInspectionComment());
        } else if (position == 2) {
            if (halfYearly.getAlarmTurbineDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getAlarmTurbineNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getAlarmTurbineComment());
        } else if (position == 3) {
            if (halfYearly.getAlarmFilterDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getAlarmFilterNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getAlarmFilterComment());
        } else if (position == 4) {
            if (halfYearly.getBellTestDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getBellTestNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getBellTestComment());
        } else if (position == 5) {
            if (halfYearly.getChainLocksDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getChainLocksNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getChainLocksComment());
        } else if (position == 6) {
            if (halfYearly.getChnageOverAirDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getChnageOverAirNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getChnageOverAirComment());
        } else if (position == 7) {
            if (halfYearly.getCleanResetAcceleratorDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getCleanResetAcceleratorNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getCleanResetAcceleratorComment());
        } else if (position == 8) {
            if (halfYearly.getCompressorOilLevelDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getCompressorOilLevelNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getCompressorOilLevelComment());
        } else if (position == 9) {
            if (halfYearly.getCompressorAirFilterDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getCompressorAirFilterNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getCompressorAirFilterComment());
        } else if (position == 10) {
            if (halfYearly.getChangeOverWaterDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getChangeOverWaterNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getChangeOverWaterComment());
        } else if (position == 11) {
            if (halfYearly.getOrainAirFromRemotePointDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getOrainAirFromRemotePointNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getOrainAirFromRemotePointComment());
        } else if (position == 12) {
            if (halfYearly.getFailToStartTestDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getFailToStartTestNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getFailToStartTestComment());
        } else if (position == 13) {
            if (halfYearly.getFireInPumphouseDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getFireInPumphouseNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getFireInPumphouseComment());
        } else if (position == 14) {
            if (halfYearly.getOverSpeedTestDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getOverSpeedTestNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getOverSpeedTestComment());
        } else if (position == 15) {
            if (halfYearly.getPressureReliefTestDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getPressureReliefTestNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getPressureReliefTestComment());
        } else if (position == 16) {
            if (halfYearly.getOpticalTacoTestDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getOpticalTacoTestNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getOpticalTacoTestComment());
        } else if (position == 17) {
            if (halfYearly.getTankBallValveDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getTankBallValveNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getTankBallValveComment());
        } else if (position == 18) {
            if (halfYearly.getTraceHeatingDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getTraceHeatingNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getTraceHeatingComment());
        } else if (position == 19) {
            if (halfYearly.getStorageTankLevelDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getStorageTankLevelNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getStorageTankLevelComment());
        } else if (position == 20) {
            if (halfYearly.getStorageTankHeightDone().equals("1")) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
            if (halfYearly.getStorageTankHeightNa().equals("1")) {
                holder.chk1.setChecked(true);
            } else {
                holder.chk1.setChecked(false);
            }
            holder.edit.setText(halfYearly.getStorageTankHeightComment());
        }

        holder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (position == 0) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAlarmValveInspectionDone(getCheckNumber(isChecked));
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAirValveInspectionDone(getCheckNumber(isChecked));
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAlarmTurbineDone(getCheckNumber(isChecked));
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAlarmFilterDone(getCheckNumber(isChecked));
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setBellTestDone(getCheckNumber(isChecked));
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setChainLocksDone(getCheckNumber(isChecked));
                } else if (position == 6) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setChnageOverAirDone(getCheckNumber(isChecked));
                } else if (position == 7) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setCleanResetAcceleratorDone(getCheckNumber(isChecked));
                } else if (position == 8) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setCompressorOilLevelDone(getCheckNumber(isChecked));
                } else if (position == 9) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setCompressorAirFilterDone(getCheckNumber(isChecked));
                } else if (position == 10) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setChangeOverWaterDone(getCheckNumber(isChecked));
                } else if (position == 11) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setOrainAirFromRemotePointDone(getCheckNumber(isChecked));
                } else if (position == 12) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setFailToStartTestDone(getCheckNumber(isChecked));
                } else if (position == 13) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setFireInPumphouseDone(getCheckNumber(isChecked));
                } else if (position == 14) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setOverSpeedTestDone(getCheckNumber(isChecked));
                } else if (position == 15) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setPressureReliefTestDone(getCheckNumber(isChecked));
                } else if (position == 16) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setOpticalTacoTestDone(getCheckNumber(isChecked));
                } else if (position == 17) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setTankBallValveDone(getCheckNumber(isChecked));
                } else if (position == 18) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setTraceHeatingDone(getCheckNumber(isChecked));
                } else if (position == 19) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setStorageTankLevelDone(getCheckNumber(isChecked));
                } else if (position == 20) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setStorageTankHeightDone(getCheckNumber(isChecked));
                }
            }
        });

        holder.chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (position == 0) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAlarmValveInspectionNa(getCheckNumber(isChecked));
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAirValveInspectionNa(getCheckNumber(isChecked));
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAlarmTurbineNa(getCheckNumber(isChecked));
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAlarmFilterNa(getCheckNumber(isChecked));
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setBellTestNa(getCheckNumber(isChecked));
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setChainLocksNa(getCheckNumber(isChecked));
                } else if (position == 6) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setChnageOverAirNa(getCheckNumber(isChecked));
                } else if (position == 7) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setCleanResetAcceleratorNa(getCheckNumber(isChecked));
                } else if (position == 8) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setCompressorOilLevelNa(getCheckNumber(isChecked));
                } else if (position == 9) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setCompressorAirFilterNa(getCheckNumber(isChecked));
                } else if (position == 10) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setChangeOverWaterNa(getCheckNumber(isChecked));
                } else if (position == 11) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setOrainAirFromRemotePointNa(getCheckNumber(isChecked));
                } else if (position == 12) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setFailToStartTestNa(getCheckNumber(isChecked));
                } else if (position == 13) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setFireInPumphouseNa(getCheckNumber(isChecked));
                } else if (position == 14) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setOverSpeedTestNa(getCheckNumber(isChecked));
                } else if (position == 15) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setPressureReliefTestNa(getCheckNumber(isChecked));
                } else if (position == 16) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setOpticalTacoTestNa(getCheckNumber(isChecked));
                } else if (position == 17) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setTankBallValveNa(getCheckNumber(isChecked));
                } else if (position == 18) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setTraceHeatingNa(getCheckNumber(isChecked));
                } else if (position == 19) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setStorageTankLevelNa(getCheckNumber(isChecked));
                } else if (position == 20) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setStorageTankHeightNa(getCheckNumber(isChecked));
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
                            .getHalfYearly().get(position).setAlarmValveInspectionComment(s.toString());
                } else if (position == 1) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAirValveInspectionComment(s.toString());
                } else if (position == 2) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAlarmTurbineComment(s.toString());
                } else if (position == 3) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setAlarmFilterComment(s.toString());
                } else if (position == 4) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setBellTestComment(s.toString());
                } else if (position == 5) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setChainLocksComment(s.toString());
                } else if (position == 6) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setChnageOverAirComment(s.toString());
                } else if (position == 7) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setCleanResetAcceleratorComment(s.toString());
                } else if (position == 8) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setCompressorOilLevelComment(s.toString());
                } else if (position == 9) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setCompressorAirFilterComment(s.toString());
                } else if (position == 10) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setChangeOverWaterComment(s.toString());
                } else if (position == 11) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setOrainAirFromRemotePointComment(s.toString());
                } else if (position == 12) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setFailToStartTestComment(s.toString());
                } else if (position == 13) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setFireInPumphouseComment(s.toString());
                } else if (position == 14) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setOverSpeedTestComment(s.toString());
                } else if (position == 15) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setPressureReliefTestComment(s.toString());
                } else if (position == 16) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setOpticalTacoTestComment(s.toString());
                } else if (position == 17) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setTankBallValveComment(s.toString());
                } else if (position == 18) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setTraceHeatingComment(s.toString());
                } else if (position == 19) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setStorageTankLevelComment(s.toString());
                } else if (position == 20) {
                    observerFormTwo.getFormTwo().getResponse().getReport4().getChecksDoneProp()
                            .getHalfYearly().get(position).setStorageTankHeightComment(s.toString());
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