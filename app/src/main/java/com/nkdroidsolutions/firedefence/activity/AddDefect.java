package com.nkdroidsolutions.firedefence.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.fragment.Form_4.Form44;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.util.ExifUtils;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import java.io.File;
import java.util.Calendar;

public class AddDefect extends AppCompatActivity implements AllObserver {

    private Toolbar toolbar;
    private EditText edit_defect, edit_preffereddate, edit_deadline, edit_importance;
    private ImageView img;
    private TextView txt_save;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private int curnt_date_pick;
    private String path1 = "";
    private File main_dir;
    private com.nkdroidsolutions.firedefence.model.Form4Model.AddDefect addDefect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_defect);

        main_dir = new File(Environment.getExternalStorageDirectory() + "/" + AppConstant.FOLDER_NAME);
        if (!main_dir.exists()) {
            main_dir.mkdir();
        }

        addDefect = new com.nkdroidsolutions.firedefence.model.Form4Model.AddDefect();

        setToolbar();
        initUI();
        clicks();

    }

    private void clicks() {
        edit_preffereddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curnt_date_pick = 1;
                showDialog(999);
            }
        });

        edit_deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curnt_date_pick = 2;
                showDialog(999);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = System.currentTimeMillis();
                path1 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
                File file = new File(path1);
                Uri outputFileUri = Uri.fromFile(file);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(intent, AppConstant.REQUEST_CODE_TAKE_PICTURE);
            }
        });

        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDefect.setDefects(edit_defect.getText().toString());
                addDefect.setPrefferDate(edit_preffereddate.getText().toString());
                addDefect.setRepairDeadline(edit_deadline.getText().toString());
                addDefect.setImportance(edit_importance.getText().toString());
                Bitmap bm = ExifUtils.decodeFile(path1);
                addDefect.setDefectImage(FunctionUtils.getInstance().encodeToBase64(bm).trim());

                observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp().getAddDefect().add(addDefect);
                Form44.getInstance().updateList();

//                form4.defect_image_path = path1;

//                Log.d("dc", form4.defect_desc);

                // Intent output = new Intent();
                // setResult(RESULT_OK, output);
                finish();

            }
        });

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

            if (curnt_date_pick == 1) {
                edit_preffereddate.setText(date);
            } else {
                edit_deadline.setText(date);
            }

        }
    };

    private void initUI() {
        txt_save = (TextView) findViewById(R.id.txt_save);
        img = (ImageView) findViewById(R.id.img);
        edit_defect = (EditText) findViewById(R.id.edit_defect);
        edit_preffereddate = (EditText) findViewById(R.id.edit_preffered_date);
        edit_deadline = (EditText) findViewById(R.id.edit_deadline);
        edit_importance = (EditText) findViewById(R.id.edit_importance);

        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Add Defect");
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {

            case AppConstant.REQUEST_CODE_TAKE_PICTURE:
                try {
                    Log.e("path", path1);
                    Bitmap bm = ExifUtils.decodeFile(path1);
                    bm = ExifUtils.rotateBitmap(path1, bm);
                    img.setImageBitmap(bm);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
