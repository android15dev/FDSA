package com.nkdroidsolutions.firedefence.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form25;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form2Model.valves.ValveDatum;
import com.nkdroidsolutions.firedefence.util.ExifUtils;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

public class ValveActivity extends AppCompatActivity implements AllObserver {

    private Toolbar toolbar;
    private TextView txt_save;
    private ImageView ivAddBefore, ivAddDuring, ivAddAfter;
    private File main_dir;
    private String path1 = "", path2 = "", path3 = "";
    private File file;

    private CheckBox chk_hasvalve;
    private EditText edit_valveno, edit_flow, edit_pressure1, edit_c1, edit_c2, edit_make, edit_dia, edit_age, edit_model, edit_notes, edit_model1, edit_make1, edit_dateinstalled;
    private ValveDatum valveDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump_type_3);

        if (getIntent().getBooleanExtra(AppConstant.IS_NEW, true)) {
            valveDatum = new ValveDatum();
        } else {
            valveDatum = observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp()
                    .getValveData().get(getIntent().getIntExtra("position", 0));
        }

        setToolbar();
        initUI();
        clicks();

        main_dir = new File(Environment.getExternalStorageDirectory() + "/" + AppConstant.FOLDER_NAME);
        if (!main_dir.exists()) {
            main_dir.mkdir();
        }

    }

    private void takePicture(int current_image) {
        long time = System.currentTimeMillis();
        if (current_image == 1) {
            path1 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
            file = new File(path1);
        } else if (current_image == 2) {
            path2 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
            file = new File(path2);
        } else if (current_image == 3) {
            path3 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
            file = new File(path3);
        }

        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, current_image);
    }


    private void clicks() {
        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valveDatum.setHasPumpValve(getCheckNumber(chk_hasvalve.isChecked()));
                valveDatum.setValveNumber(edit_valveno.getText().toString().trim());
                valveDatum.setFlowValve(edit_flow.getText().toString().trim());
                valveDatum.setPressureValve(edit_pressure1.getText().toString().trim());
                valveDatum.setCBefore(edit_c1.getText().toString().trim());
                valveDatum.setCAfter(edit_c2.getText().toString().trim());
                valveDatum.setMakeValvePump(edit_make.getText().toString().trim());
                valveDatum.setDiaValve(edit_dia.getText().toString().trim());
                valveDatum.setAgeValve(edit_age.getText().toString().trim());
                valveDatum.setModelValve(edit_model.getText().toString().trim());
                valveDatum.setNotesValve(edit_notes.getText().toString().trim());
                valveDatum.setMakePumpValve(edit_make1.getText().toString().trim());
                valveDatum.setModelPumpValve(edit_model.getText().toString().trim());
                valveDatum.setDateinstalledPumpValve(edit_dateinstalled.getText().toString().trim());

                if (getIntent().getBooleanExtra(AppConstant.IS_NEW, true)) {
                    observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp().getValveData().add(valveDatum);
                } else {
                    observerFormTwo.getFormTwo().getResponse().getReport5().getValveDataProp().getValveData().set(getIntent().getIntExtra("position", 0), valveDatum);
                }

                Form25.getInstance().updateValveList();
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }


    private void initUI() {

        chk_hasvalve = (CheckBox) findViewById(R.id.chk_hasvalve);
        edit_valveno = (EditText) findViewById(R.id.edit_valveno);
        edit_flow = (EditText) findViewById(R.id.edit_flow);
        edit_pressure1 = (EditText) findViewById(R.id.edit_pressure1);
        edit_c1 = (EditText) findViewById(R.id.edit_c1);
        edit_c2 = (EditText) findViewById(R.id.edit_c2);
        edit_make = (EditText) findViewById(R.id.edit_make);
        edit_dia = (EditText) findViewById(R.id.edit_dia);
        edit_age = (EditText) findViewById(R.id.edit_age);
        edit_model = (EditText) findViewById(R.id.edit_model);
        edit_notes = (EditText) findViewById(R.id.edit_notes);
        edit_make1 = (EditText) findViewById(R.id.edit_make1);
        edit_model1 = (EditText) findViewById(R.id.edit_model1);
        edit_dateinstalled = (EditText) findViewById(R.id.edit_dateinstalled);


        txt_save = (TextView) findViewById(R.id.txt_save);
        ivAddBefore = (ImageView) findViewById(R.id.ivAddBefore);
        ivAddDuring = (ImageView) findViewById(R.id.ivAddDuring);
        ivAddAfter = (ImageView) findViewById(R.id.ivAddAfter);

        edit_valveno.setText(valveDatum.getValveNumber());
        edit_flow.setText(valveDatum.getFlowValve());
        edit_pressure1.setText(valveDatum.getPressureValve());
        edit_c1.setText(valveDatum.getCBefore());
        edit_c2.setText(valveDatum.getCAfter());
        edit_make.setText(valveDatum.getMakeValvePump());
        edit_dia.setText(valveDatum.getDiaValve());
        edit_age.setText(valveDatum.getAgeValve());
        edit_model.setText(valveDatum.getModelValve());
        edit_notes.setText(valveDatum.getNotesValve());
        edit_make1.setText(valveDatum.getMakePumpValve());
        edit_model1.setText(valveDatum.getModelPumpValve());
        edit_dateinstalled.setText(valveDatum.getDateinstalledPumpValve());

        if (valveDatum.getHasPumpValve().equals("1")) {
            chk_hasvalve.setChecked(true);
        } else {
            chk_hasvalve.setChecked(false);
        }

        if (!TextUtils.isEmpty(valveDatum.getValveImage1())) {
            if (valveDatum.getValveImage1().startsWith("data")) {

                Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                base64ToBitmap.execute(valveDatum.getValveImage1());
                base64ToBitmap.enqueue(new GetBitmap() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        ivAddBefore.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

            } else {
                ImageLoader.getInstance().displayImage(AppConstant.IMAGE_URL + valveDatum.getValveImage1(), ivAddBefore);
            }
        }

        if (!TextUtils.isEmpty(valveDatum.getValveImage2())) {
            if (valveDatum.getValveImage2().startsWith("data")) {

                Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                base64ToBitmap.execute(valveDatum.getValveImage2());
                base64ToBitmap.enqueue(new GetBitmap() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        ivAddDuring.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

            } else {
                ImageLoader.getInstance().displayImage(AppConstant.IMAGE_URL + valveDatum.getValveImage2(), ivAddDuring);
            }
        }


        if (!TextUtils.isEmpty(valveDatum.getValveImage3())) {
            if (valveDatum.getValveImage3().startsWith("data")) {

                Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                base64ToBitmap.execute(valveDatum.getValveImage3());
                base64ToBitmap.enqueue(new GetBitmap() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        ivAddAfter.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

            } else {
                ImageLoader.getInstance().displayImage(AppConstant.IMAGE_URL + valveDatum.getValveImage3(), ivAddAfter);
            }
        }

        ivAddBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(1);
            }
        });

        ivAddDuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(2);
            }
        });

        ivAddAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(3);
            }
        });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {

            case 1:
                try {
                    Log.e("path", path1);
                    Bitmap bm = ExifUtils.decodeFile(path1);
                    bm = ExifUtils.rotateBitmap(path1, bm);
                    ivAddBefore.setImageBitmap(bm);
                    valveDatum.setValveImage1(FunctionUtils.getInstance().encodeToBase64(bm).trim());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {

                    Log.e("path", path2);
                    Bitmap bm = ExifUtils.decodeFile(path2);
                    bm = ExifUtils.rotateBitmap(path2, bm);
                    ivAddDuring.setImageBitmap(bm);
                    valveDatum.setValveImage2(FunctionUtils.getInstance().encodeToBase64(bm).trim());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {

                    Log.e("path", path3);
                    Bitmap bm = ExifUtils.decodeFile(path3);
                    bm = ExifUtils.rotateBitmap(path3, bm);
                    ivAddAfter.setImageBitmap(bm);
                    valveDatum.setValveImage3(FunctionUtils.getInstance().encodeToBase64(bm).trim());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
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
