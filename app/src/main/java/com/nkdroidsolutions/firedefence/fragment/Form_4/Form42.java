package com.nkdroidsolutions.firedefence.fragment.Form_4;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.VehicleForm4;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form4Model.Engine;
import com.nkdroidsolutions.firedefence.model.Form4Model.Report2Vehicle;
import com.nkdroidsolutions.firedefence.model.GetAssignedForm1;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nkdroidsolutions.firedefence.web_api.UrlToBitmap;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Form42 extends Fragment implements View.OnClickListener, TextWatcher, AllObserver {

    private TextView next;
    private LinearLayout rootLayout;
    public GetAssignedForm1 form1;

    private View convertView;
    private TextView txt_next, txt_back;
    private File main_dir;
    private String path = "";
    public static File file;
    private Activity activity;
    private Report2Vehicle report2Vehicle = new Report2Vehicle();
    private List<Engine> engine = new ArrayList<Engine>(16);

    public static Form42 newInstance() {
        return new Form42();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form42, container, false);
        return convertView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    private EditText edit_comment1, edit_comment2, edit_comment3, edit_comment4, edit_comment5, edit_comment6, edit_comment7, edit_comment8;
    private EditText edit_comment9, edit_comment10, edit_comment11, edit_comment12, edit_comment13, edit_comment14, edit_comment15, edit_comment16;
    private CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8, chk9, chk10, chk11, chk12, chk13, chk14, chk15, chk16;
    private ImageView img1;

    private void initUI() {
        edit_comment1 = (EditText) convertView.findViewById(R.id.edit_comment_form42_1);
        edit_comment2 = (EditText) convertView.findViewById(R.id.edit_comment_form42_2);
        edit_comment3 = (EditText) convertView.findViewById(R.id.edit_comment_form42_3);
        edit_comment4 = (EditText) convertView.findViewById(R.id.edit_comment_form42_4);
        edit_comment5 = (EditText) convertView.findViewById(R.id.edit_comment_form42_5);
        edit_comment6 = (EditText) convertView.findViewById(R.id.edit_comment_form42_6);
        edit_comment7 = (EditText) convertView.findViewById(R.id.edit_comment_form42_7);
        edit_comment8 = (EditText) convertView.findViewById(R.id.edit_comment_form42_8);

        chk1 = (CheckBox) convertView.findViewById(R.id.chk_form42_1);
        chk2 = (CheckBox) convertView.findViewById(R.id.chk_form42_2);
        chk3 = (CheckBox) convertView.findViewById(R.id.chk_form42_3);
        chk4 = (CheckBox) convertView.findViewById(R.id.chk_form42_4);
        chk5 = (CheckBox) convertView.findViewById(R.id.chk_form42_5);
        chk6 = (CheckBox) convertView.findViewById(R.id.chk_form42_6);
        chk7 = (CheckBox) convertView.findViewById(R.id.chk_form42_7);
        chk8 = (CheckBox) convertView.findViewById(R.id.chk_form42_8);

        edit_comment9 = (EditText) convertView.findViewById(R.id.edit_comment_form43_1);
        edit_comment10 = (EditText) convertView.findViewById(R.id.edit_comment_form43_2);
        edit_comment11 = (EditText) convertView.findViewById(R.id.edit_comment_form43_3);
        edit_comment12 = (EditText) convertView.findViewById(R.id.edit_comment_form43_4);
        edit_comment13 = (EditText) convertView.findViewById(R.id.edit_comment_form43_5);
        edit_comment14 = (EditText) convertView.findViewById(R.id.edit_comment_form43_6);
        edit_comment15 = (EditText) convertView.findViewById(R.id.edit_comment_form43_7);
        edit_comment16 = (EditText) convertView.findViewById(R.id.edit_comment_form43_8);

        chk9 = (CheckBox) convertView.findViewById(R.id.chk_form43_1);
        chk10 = (CheckBox) convertView.findViewById(R.id.chk_form43_2);
        chk11 = (CheckBox) convertView.findViewById(R.id.chk_form43_3);
        chk12 = (CheckBox) convertView.findViewById(R.id.chk_form43_4);
        chk13 = (CheckBox) convertView.findViewById(R.id.chk_form43_5);
        chk14 = (CheckBox) convertView.findViewById(R.id.chk_form43_6);
        chk15 = (CheckBox) convertView.findViewById(R.id.chk_form43_7);
        chk16 = (CheckBox) convertView.findViewById(R.id.chk_form43_8);

        img1 = (ImageView) convertView.findViewById(R.id.img_form42_1);

        img1.setOnClickListener(this);

        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList();
                ((VehicleForm4) getActivity()).viewPager.setCurrentItem(2);
            }
        });
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList();
                ((VehicleForm4) getActivity()).viewPager.setCurrentItem(0);
            }
        });

    }

    private void updateList() {
        report2Vehicle.setEngine(engine);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(report2Vehicle);
        Log.d("list", json);
        observerFormFour.getFormfour().getResponse().getReport2().setVehicleChecklist(json);
    }

    private void takePicture() {
        /*long time = System.currentTimeMillis();

        path = main_dir.getAbsolutePath() + "/" + time + ".jpg";
        file = new File(path);

        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, AppConstant.REQUEST_CODE_TAKE_PICTURE);*/
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = FunctionUtils.getInstance().createImageFile(true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, AppConstant.REQUEST_CODE_TAKE_PICTURE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_form42_1:
                takePicture();
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != getActivity().RESULT_OK) {
            return;
        }

        if (requestCode == AppConstant.REQUEST_CODE_TAKE_PICTURE) {
            Bitmap bm = BitmapFactory.decodeFile(file.getPath());
            bm = FunctionUtils.getInstance().getResizedBitmap(bm, 200);
            img1.setImageBitmap(bm);
            observerFormFour.getFormfour().getResponse().getReport2().setBitmap(bm);
            engine.get(11).setBodywkChkImage(FunctionUtils.getInstance().encodeToBase64(bm).trim());
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        main_dir = new File(Environment.getExternalStorageDirectory() + "/" + AppConstant.FOLDER_NAME);
        if (!main_dir.exists()) {
            main_dir.mkdir();
        }

        initUI();
        if (observerFormFour.getFormfour() != null) {
            if (TextUtils.isEmpty(observerFormFour.getFormfour().getResponse().getReport2().getVehicleChecklist())) {
                report2Vehicle = new Report2Vehicle();
                for (int i = 0; i < 16; i++) {
                    engine.add(new Engine());
                }
            } else {
                report2Vehicle = new Gson().fromJson(observerFormFour.getFormfour().getResponse().getReport2().getVehicleChecklist().replace("\\\\", ""), Report2Vehicle.class);
                if (report2Vehicle != null) {
                    engine = report2Vehicle.getEngine();
                }
            }
            if (report2Vehicle != null) {
                for (int i = 0; i < engine.size(); i++) {
                    switch (i) {
                        case 0:
                            if (engine.get(i).getEngineOilChk().equals("1")) {
                                chk1.setChecked(true);
                            } else {
                                chk1.setChecked(false);
                            }
                            edit_comment1.setText(engine.get(i).getEngineOilComment());
                            break;
                        case 1:
                            if (engine.get(i).getCoolantLvlChk().equals("1")) {
                                chk2.setChecked(true);
                            } else {
                                chk2.setChecked(false);
                            }
                            edit_comment2.setText(engine.get(i).getCoolantLvlComment());
                            break;
                        case 2:
                            if (engine.get(i).getBrakeFluidLvlChk().equals("1")) {
                                chk3.setChecked(true);
                            } else {
                                chk3.setChecked(false);
                            }
                            edit_comment3.setText(engine.get(i).getBrakeFluidLvlComment());
                            break;
                        case 3:
                            if (engine.get(i).getSteeringFluidLvlChk().equals("1")) {
                                chk4.setChecked(true);
                            } else {
                                chk4.setChecked(false);
                            }
                            edit_comment4.setText(engine.get(i).getSteeringFluidLvlComment());
                            break;
                        case 4:
                            if (engine.get(i).getWasherFluidLvlChk().equals("1")) {
                                chk5.setChecked(true);
                            } else {
                                chk5.setChecked(false);
                            }
                            edit_comment5.setText(engine.get(i).getWasherFluidLvlComment());
                            break;
                        case 5:
                            if (engine.get(i).getWasherWiperChk().equals("1")) {
                                chk6.setChecked(true);
                            } else {
                                chk6.setChecked(false);
                            }
                            edit_comment6.setText(engine.get(i).getWasherWiperComment());
                            break;
                        case 6:
                            if (engine.get(i).getLightHornChk().equals("1")) {
                                chk7.setChecked(true);
                            } else {
                                chk7.setChecked(false);
                            }
                            edit_comment7.setText(engine.get(i).getLightHornComment());
                            break;
                        case 7:
                            if (engine.get(i).getTyreTreadSidewallsChk().equals("1")) {
                                chk8.setChecked(true);
                            } else {
                                chk8.setChecked(false);
                            }
                            edit_comment8.setText(engine.get(i).getTyreTreadSidewallsComment());
                            break;
                        case 8:
                            if (engine.get(i).getTyrePressuresChk().equals("1")) {
                                chk9.setChecked(true);
                            } else {
                                chk9.setChecked(false);
                            }
                            edit_comment9.setText(engine.get(i).getTyrePressuresComment());
                            break;
                        case 9:
                            if (engine.get(i).getWheelNutsSecureChk().equals("1")) {
                                chk10.setChecked(true);
                            } else {
                                chk10.setChecked(false);
                            }
                            edit_comment10.setText(engine.get(i).getWheelNutsSecureComment());
                            break;
                        case 10:
                            if (engine.get(i).getConditionBatteryChk().equals("1")) {
                                chk11.setChecked(true);
                            } else {
                                chk11.setChecked(false);
                            }
                            edit_comment11.setText(engine.get(i).getConditionBatteryComment());
                            break;
                        case 11:
                            if (engine.get(i).getBodywkChk().equals("1")) {
                                chk12.setChecked(true);
                            } else {
                                chk12.setChecked(false);
                            }
                            edit_comment12.setText(engine.get(i).getBodywkChkComment());
                            if (!TextUtils.isEmpty(engine.get(i).getBodywkChkImage())) {
                                if (engine.get(i).getBodywkChkImage().startsWith("data")) {

                                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                                    base64ToBitmap.execute(engine.get(i).getBodywkChkImage());
                                    base64ToBitmap.enqueue(new GetBitmap() {
                                        @Override
                                        public void onSuccess(Bitmap bitmap) {
                                            observerFormFour.getFormfour().getResponse().getReport2().setBitmap(bitmap);
                                            img1.setImageBitmap(bitmap);
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            e.printStackTrace();
                                        }
                                    });

                                } else {
                                    UrlToBitmap urltobitmap = new UrlToBitmap();

                                    urltobitmap.execute(AppConstant.IMAGE_URL + engine.get(i).getBodywkChkImage());
                                    urltobitmap.enqueue(new GetBitmap() {
                                        @Override
                                        public void onSuccess(Bitmap bitmap) {
                                            observerFormFour.getFormfour().getResponse().getReport2().setBitmap(bitmap);
                                            img1.setImageBitmap(bitmap);
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            e.printStackTrace();
                                        }
                                    });
//                                    ImageLoader.getInstance().displayImage(AppConstant.IMAGE_URL + engine.get(i).getBodywkChkImage(), img1);
                                }
                            }
                            break;
                        case 12:
                            if (engine.get(i).getFirstAidKidContentChk().equals("1")) {
                                chk13.setChecked(true);
                            } else {
                                chk13.setChecked(false);
                            }
                            edit_comment13.setText(engine.get(i).getFirstAidKidContentComment());
                            break;
                        case 13:
                            if (engine.get(i).getFireExteingusherChk().equals("1")) {
                                chk14.setChecked(true);
                            } else {
                                chk14.setChecked(false);
                            }
                            edit_comment14.setText(engine.get(i).getFireExteingusherComment());
                            break;
                        case 14:
                            if (engine.get(i).getCleanTidyChk().equals("1")) {
                                chk15.setChecked(true);
                            } else {
                                chk15.setChecked(false);
                            }
                            edit_comment15.setText(engine.get(i).getCleanTidyComment());
                            break;
                        case 15:
                            if (engine.get(i).getBreakPadsDisksChk().equals("1")) {
                                chk16.setChecked(true);
                            } else {
                                chk16.setChecked(false);
                            }
                            edit_comment16.setText(engine.get(i).getBreakPadsDisksComment());
                            break;
                    }
                }
            }

        }

        listners();

    }


    private String getCheckNumber(boolean b) {
        if (b) {
            return "1";
        } else {
            return "0";
        }
    }

    private void listners() {
        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(0).setEngineOilChk(getCheckNumber(isChecked));
            }
        });
        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(1).setCoolantLvlChk(getCheckNumber(isChecked));
            }
        });
        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(2).setBrakeFluidLvlChk(getCheckNumber(isChecked));
            }
        });
        chk4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(3).setSteeringFluidLvlChk(getCheckNumber(isChecked));
            }
        });
        chk5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(4).setWasherFluidLvlChk(getCheckNumber(isChecked));
            }
        });
        chk6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(5).setWasherWiperChk(getCheckNumber(isChecked));
            }
        });
        chk7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(6).setLightHornChk(getCheckNumber(isChecked));
            }
        });
        chk8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(7).setTyreTreadSidewallsChk(getCheckNumber(isChecked));
            }
        });
        chk9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(8).setTyrePressuresChk(getCheckNumber(isChecked));
            }
        });
        chk10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(9).setWheelNutsSecureChk(getCheckNumber(isChecked));
            }
        });
        chk11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(10).setConditionBatteryChk(getCheckNumber(isChecked));
            }
        });
        chk12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(11).setBodywkChk(getCheckNumber(isChecked));
            }
        });
        chk13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(12).setFirstAidKidContentChk(getCheckNumber(isChecked));
            }
        });
        chk14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(13).setFireExteingusherChk(getCheckNumber(isChecked));
            }
        });
        chk15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(14).setCleanTidyChk(getCheckNumber(isChecked));
            }
        });
        chk16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                engine.get(15).setBreakPadsDisksChk(getCheckNumber(isChecked));
            }
        });

        edit_comment1.addTextChangedListener(this);
        edit_comment2.addTextChangedListener(this);
        edit_comment3.addTextChangedListener(this);
        edit_comment4.addTextChangedListener(this);
        edit_comment5.addTextChangedListener(this);
        edit_comment6.addTextChangedListener(this);
        edit_comment7.addTextChangedListener(this);
        edit_comment8.addTextChangedListener(this);
        edit_comment9.addTextChangedListener(this);
        edit_comment10.addTextChangedListener(this);
        edit_comment11.addTextChangedListener(this);
        edit_comment12.addTextChangedListener(this);
        edit_comment13.addTextChangedListener(this);
        edit_comment14.addTextChangedListener(this);
        edit_comment15.addTextChangedListener(this);
        edit_comment16.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (s == edit_comment1.getEditableText()) {
            engine.get(0).setEngineOilComment(s.toString());
        } else if (s == edit_comment2.getEditableText()) {
            engine.get(1).setCoolantLvlComment(s.toString());
        } else if (s == edit_comment3.getEditableText()) {
            engine.get(2).setBrakeFluidLvlComment(s.toString());
        } else if (s == edit_comment4.getEditableText()) {
            engine.get(3).setSteeringFluidLvlComment(s.toString());
        } else if (s == edit_comment5.getEditableText()) {
            engine.get(4).setWasherFluidLvlComment(s.toString());
        } else if (s == edit_comment6.getEditableText()) {
            engine.get(5).setWasherWiperComment(s.toString());
        } else if (s == edit_comment7.getEditableText()) {
            engine.get(6).setLightHornComment(s.toString());
        } else if (s == edit_comment8.getEditableText()) {
            engine.get(7).setTyreTreadSidewallsComment(s.toString());
        } else if (s == edit_comment9.getEditableText()) {
            engine.get(8).setTyrePressuresComment(s.toString());
        } else if (s == edit_comment10.getEditableText()) {
            engine.get(9).setWheelNutsSecureComment(s.toString());
        } else if (s == edit_comment11.getEditableText()) {
            engine.get(10).setConditionBatteryComment(s.toString());
        } else if (s == edit_comment12.getEditableText()) {
            engine.get(11).setBodywkChkComment(s.toString());
        } else if (s == edit_comment13.getEditableText()) {
            engine.get(12).setFirstAidKidContentComment(s.toString());
        } else if (s == edit_comment14.getEditableText()) {
            engine.get(13).setFireExteingusherComment(s.toString());
        } else if (s == edit_comment15.getEditableText()) {
            engine.get(14).setCleanTidyComment(s.toString());
        } else if (s == edit_comment16.getEditableText()) {
            engine.get(15).setBreakPadsDisksComment(s.toString());
        }


    }
}