package com.nkdroidsolutions.firedefence.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.fragment.Form_3.Form33;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form3Model.AddDatum;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.util.Calendar;

public class Add_From33_Data extends AppCompatActivity implements AllObserver {

    private Toolbar toolbar;
    private Calendar calendar;
    private EditText edit_manufactirer, edit_type, edit_newtag, edit_comnts_recomm;
    private EditText edit_capacity, edit_location, edit_weight, edit_dateofmanuf, edit_updatelabel;
    private TextView txt_save;
    private DatePickerDialog date_picker;
    private RadioGroup rd_group_recmnd;
    private RadioButton rd_btn_es, rd_btn_c, rd_btn_n, rd_btn_w;
    private AddDatum addDatum;

    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__from33__data);

        if (getIntent().getBooleanExtra(AppConstant.IS_NEW, true)) {
            addDatum = new AddDatum();
        } else {
            addDatum = observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp()
                    .getAddData().get(getIntent().getIntExtra("position", 0));
        }

        setToolbar();
        initUI();
        clicks();
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        DatePickerDialog abc = new DatePickerDialog(this, myDateListener, year, month, day);
        DatePicker dp = abc.getDatePicker();
        dp.setMinDate(System.currentTimeMillis() - 10000);
        return abc;

    }

    private String date = "";
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            String day = arg3 + "";
            if (arg3 < 10) {
                day = "0" + arg3;
            }
            String mnth = (arg2 + 1) + "";
            if ((arg2 + 1) < 10) {
                mnth = "0" + (arg2 + 1);
            }
            date = String.valueOf(new StringBuilder().append(day).append("/").append(mnth).append("/").append(arg1));
            Log.d("date", date);

            edit_dateofmanuf.setText(date);

        }
    };

    private void clicks() {

        edit_dateofmanuf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });

        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDatum.setManufacturer(edit_manufactirer.getText().toString().trim());
                addDatum.setType(edit_type.getText().toString().trim());
                addDatum.setCapacity(edit_capacity.getText().toString().trim());
                addDatum.setLocation(edit_location.getText().toString().trim());
                addDatum.setWeight(edit_weight.getText().toString().trim());
                addDatum.setManuDate(edit_dateofmanuf.getText().toString().trim());
                addDatum.setNewTag(edit_newtag.getText().toString().trim());
                addDatum.setUpdateLabel(edit_updatelabel.getText().toString().trim());
                addDatum.setComments(edit_comnts_recomm.getText().toString().trim());
                int id = rd_group_recmnd.getCheckedRadioButtonId();
                if (id == rd_btn_es.getId()) {
                    addDatum.setServices("1");
                } else if (id == rd_btn_c.getId()) {
                    addDatum.setServices("2");
                } else if (id == rd_btn_n.getId()) {
                    addDatum.setServices("3");
                } else if (id == rd_btn_w.getId()) {
                    addDatum.setServices("4");
                }

                if (getIntent().getBooleanExtra(AppConstant.IS_NEW, true)) {
                    observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().getAddData().add(addDatum);
                } else {
                    observerFormThree.getFormthree().getResponse().getReport3().getExtinCheckProp().
                            getAddData().set(getIntent().getIntExtra("position", 0), addDatum);
                }

                Form33.getInstance().updateList();
                finish();

            }
        });
    }

    private void initUI() {
        txt_save = (TextView) findViewById(R.id.txt_save);
        edit_manufactirer = (EditText) findViewById(R.id.edit_manufactirer);
        edit_type = (EditText) findViewById(R.id.edit_type);
        edit_capacity = (EditText) findViewById(R.id.edit_capacity);
        edit_location = (EditText) findViewById(R.id.edit_location);
        edit_weight = (EditText) findViewById(R.id.edit_weight);
        edit_dateofmanuf = (EditText) findViewById(R.id.edit_dateofmanuf);
        edit_updatelabel = (EditText) findViewById(R.id.edit_updatelabel);
        edit_newtag = (EditText) findViewById(R.id.edit_newtag);
        edit_comnts_recomm = (EditText) findViewById(R.id.edit_comnts_recomm);

        rd_group_recmnd = (RadioGroup) findViewById(R.id.rd_group_recmnd);
        rd_btn_es = (RadioButton) findViewById(R.id.rd_btn_es);
        rd_btn_c = (RadioButton) findViewById(R.id.rd_btn_c);
        rd_btn_n = (RadioButton) findViewById(R.id.rd_btn_n);
        rd_btn_w = (RadioButton) findViewById(R.id.rd_btn_w);

        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        edit_manufactirer.setText(addDatum.getManufacturer());
        edit_type.setText(addDatum.getType());
        edit_capacity.setText(addDatum.getCapacity());
        edit_location.setText(addDatum.getLocation());
        edit_weight.setText(addDatum.getWeight());
        edit_dateofmanuf.setText(addDatum.getManuDate());
        edit_updatelabel.setText(addDatum.getUpdateLabel());
        edit_newtag.setText(addDatum.getNewTag());
        edit_comnts_recomm.setText(addDatum.getComments());

        if (addDatum.getServices().equals("1")) {
            rd_group_recmnd.check(rd_btn_es.getId());
        } else if (addDatum.getServices().equals("2")) {
            rd_group_recmnd.check(rd_btn_c.getId());
        } else if (addDatum.getServices().equals("3")) {
            rd_group_recmnd.check(rd_btn_n.getId());
        } else if (addDatum.getServices().equals("4")) {
            rd_group_recmnd.check(rd_btn_w.getId());
        } else {
            rd_group_recmnd.check(rd_btn_es.getId());
        }

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Add Data");
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

}
