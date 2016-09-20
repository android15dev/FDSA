package com.nkdroidsolutions.firedefence.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form25;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form2Model.pumps.PumpDatum;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

public class PumpActivity extends AppCompatActivity implements AllObserver {

    private Toolbar toolbar;
    private TextView txt_save;
    private CheckBox chk_diesel, chk_electric, chk_haspump;
    EditText etPumpNumber, etstart, etstop, etpumpmake, etpumpmodel, etpumpdia, etpumpage, etmotormake, etmotormodel, etmotorage, etmotordia;
    EditText etnotes, edit_point1_flow, edit_point2_flow, edit_point3_flow, edit_point1_pressure, edit_point2_pressure, edit_point3_pressure;
    EditText edit_point1_diesel, edit_point2_diesel, edit_point3_diesel, edit_point1_electric, edit_point2_electric, edit_point3_electric;
    EditText et_make, et_model, et_dateinstalled;
    private PumpDatum pumpDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punp_type_2);

        if (getIntent().getBooleanExtra(AppConstant.IS_NEW, true)) {
            pumpDatum = new PumpDatum();
        } else {
            pumpDatum = observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp()
                    .getPumpData().get(getIntent().getIntExtra("position", 0));
        }

        setToolbar();
        initUI();
        clicks();

    }

    private void clicks() {
        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pumpDatum.setDiesel(getCheckNumber(chk_diesel.isChecked()));
                pumpDatum.setElectric(getCheckNumber(chk_electric.isChecked()));
                pumpDatum.setHasPump(getCheckNumber(chk_haspump.isChecked()));
                pumpDatum.setPumpNo(etPumpNumber.getText().toString().trim());
                pumpDatum.setPumpStartReading(etstart.getText().toString().trim());
                pumpDatum.setPumpStopReading(etstop.getText().toString().trim());
                pumpDatum.setPumpMake(etpumpmake.getText().toString().trim());
                pumpDatum.setPumpModel(etpumpmodel.getText().toString().trim());
                pumpDatum.setPumpDia(etpumpdia.getText().toString().trim());
                pumpDatum.setPumpAge(etpumpage.getText().toString().trim());
                pumpDatum.setMotorMake(etmotormake.getText().toString().trim());
                pumpDatum.setMotorModel(etmotormodel.getText().toString().trim());
                pumpDatum.setMotorAge(etmotorage.getText().toString().trim());
                pumpDatum.setMotorDia(etmotordia.getText().toString().trim());
                pumpDatum.setMotorNotes(etnotes.getText().toString().trim());
                pumpDatum.setPoint1Flow(edit_point1_flow.getText().toString().trim());
                pumpDatum.setPoint2Flow(edit_point2_flow.getText().toString().trim());
                pumpDatum.setPoint3Flow(edit_point3_flow.getText().toString().trim());
                pumpDatum.setPoint1Pressure(edit_point1_pressure.getText().toString().trim());
                pumpDatum.setPoint2Pressure(edit_point2_pressure.getText().toString().trim());
                pumpDatum.setPoint3Pressure(edit_point3_pressure.getText().toString().trim());
                pumpDatum.setPoint1Diesel(edit_point1_diesel.getText().toString().trim());
                pumpDatum.setPoint2Diesel(edit_point2_diesel.getText().toString().trim());
                pumpDatum.setPoint3Diesel(edit_point3_diesel.getText().toString().trim());
                pumpDatum.setPoint1Electric(edit_point1_electric.getText().toString().trim());
                pumpDatum.setPoint2Electric(edit_point2_electric.getText().toString().trim());
                pumpDatum.setPoint3Electric(edit_point3_electric.getText().toString().trim());
                pumpDatum.setLowMakePump(et_make.getText().toString().trim());
                pumpDatum.setLowModelPump(et_model.getText().toString().trim());
                pumpDatum.setLowDateinstalledPump(et_dateinstalled.getText().toString().trim());

                if (getIntent().getBooleanExtra(AppConstant.IS_NEW, true)) {
                    observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp().getPumpData().add(pumpDatum);
                } else {
                    observerFormTwo.getFormTwo().getResponse().getReport5().getPumpDataProp().getPumpData().set(getIntent().getIntExtra("position", 0), pumpDatum);
                }
                Form25.getInstance().updatePumpList();
                finish();
            }
        });
    }

    private void initUI() {
        chk_diesel = (CheckBox) findViewById(R.id.chk_diesel);
        chk_electric = (CheckBox) findViewById(R.id.chk_electric);
        chk_haspump = (CheckBox) findViewById(R.id.chk_haspump);
        etPumpNumber = (EditText) findViewById(R.id.etPumpNumber);
        etstart = (EditText) findViewById(R.id.etstart);
        etstop = (EditText) findViewById(R.id.etstop);
        etpumpmake = (EditText) findViewById(R.id.etpumpmake);
        etpumpmodel = (EditText) findViewById(R.id.etpumpmodel);
        etpumpdia = (EditText) findViewById(R.id.etpumpdia);
        etpumpage = (EditText) findViewById(R.id.etpumpage);
        etmotormake = (EditText) findViewById(R.id.etmotormake);
        etmotormodel = (EditText) findViewById(R.id.etmotormodel);
        etmotorage = (EditText) findViewById(R.id.etmotorage);
        etmotordia = (EditText) findViewById(R.id.etmotordia);
        etnotes = (EditText) findViewById(R.id.etnotes);
        edit_point1_flow = (EditText) findViewById(R.id.edit_point1_flow);
        edit_point2_flow = (EditText) findViewById(R.id.edit_point2_flow);
        edit_point3_flow = (EditText) findViewById(R.id.edit_point3_flow);
        edit_point1_pressure = (EditText) findViewById(R.id.edit_point1_pressure);
        edit_point2_pressure = (EditText) findViewById(R.id.edit_point2_pressure);
        edit_point3_pressure = (EditText) findViewById(R.id.edit_point3_pressure);
        edit_point1_diesel = (EditText) findViewById(R.id.edit_point1_diesel);
        edit_point2_diesel = (EditText) findViewById(R.id.edit_point2_diesel);
        edit_point3_diesel = (EditText) findViewById(R.id.edit_point3_diesel);
        edit_point1_electric = (EditText) findViewById(R.id.edit_point1_electric);
        edit_point2_electric = (EditText) findViewById(R.id.edit_point2_electric);
        edit_point3_electric = (EditText) findViewById(R.id.edit_point3_electric);

        et_make = (EditText) findViewById(R.id.et_make);
        et_model = (EditText) findViewById(R.id.et_model);
        et_dateinstalled = (EditText) findViewById(R.id.et_dateinstalled);

        txt_save = (TextView) findViewById(R.id.txt_save);

        et_dateinstalled.setText(pumpDatum.getLowDateinstalledPump());
        et_model.setText(pumpDatum.getLowModelPump());
        et_make.setText(pumpDatum.getLowMakePump());
        edit_point1_electric.setText(pumpDatum.getPoint1Electric());
        edit_point2_electric.setText(pumpDatum.getPoint2Electric());
        edit_point3_electric.setText(pumpDatum.getPoint3Electric());
        edit_point1_diesel.setText(pumpDatum.getPoint1Diesel());
        edit_point2_diesel.setText(pumpDatum.getPoint2Diesel());
        edit_point3_diesel.setText(pumpDatum.getPoint3Electric());
        edit_point1_flow.setText(pumpDatum.getPoint1Flow());
        edit_point2_flow.setText(pumpDatum.getPoint2Flow());
        edit_point3_flow.setText(pumpDatum.getPoint3Flow());
        edit_point1_pressure.setText(pumpDatum.getPoint1Pressure());
        edit_point2_pressure.setText(pumpDatum.getPoint2Pressure());
        edit_point3_pressure.setText(pumpDatum.getPoint3Pressure());
        etnotes.setText(pumpDatum.getMotorNotes());
        etPumpNumber.setText(pumpDatum.getPumpNo());
        etpumpage.setText(pumpDatum.getPumpAge());
        etpumpdia.setText(pumpDatum.getPumpDia());
        etpumpmake.setText(pumpDatum.getPumpMake());
        etpumpmodel.setText(pumpDatum.getPumpModel());
        etmotorage.setText(pumpDatum.getMotorAge());
        etmotordia.setText(pumpDatum.getMotorDia());
        etmotormake.setText(pumpDatum.getMotorMake());
        etmotormodel.setText(pumpDatum.getMotorModel());
        etstart.setText(pumpDatum.getPumpStartReading());
        etstop.setText(pumpDatum.getPumpStopReading());

        if (pumpDatum.getDiesel().equals("1")) {
            chk_diesel.setChecked(true);
        } else {
            chk_diesel.setChecked(false);
        }

        if (pumpDatum.getElectric().equals("1")) {
            chk_electric.setChecked(true);
        } else {
            chk_electric.setChecked(false);
        }

        if (pumpDatum.getHasPump().equals("1")) {
            chk_haspump.setChecked(true);
        } else {
            chk_haspump.setChecked(false);
        }


    }


    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setResult(RESULT_CANCELED);
                    finish();
                }
            });
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
