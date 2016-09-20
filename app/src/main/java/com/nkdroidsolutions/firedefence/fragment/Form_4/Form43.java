package com.nkdroidsolutions.firedefence.fragment.Form_4;


import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.VehicleForm4;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form4Model.General;
import com.nkdroidsolutions.firedefence.model.Form4Model.Report3General;
import com.nkdroidsolutions.firedefence.model.GetAssignedForm1;
import com.nkdroidsolutions.firedefence.util.ExifUtils;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nkdroidsolutions.firedefence.web_api.UrlToBitmap;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Form43 extends Fragment implements View.OnClickListener, TextWatcher, AllObserver {

    public GetAssignedForm1 form1;


    private View convertView;
    private TextView txt_next, txt_back;
    private File main_dir;
    private String path1 = "", path2 = "";
    private int current_image = 1;
    private File file;
    private Report3General report3General = new Report3General();
    private List<General> general = new ArrayList<General>(3);

    public static Form43 newInstance() {
        return new Form43();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_form44, container, false);
        return convertView;
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

            Log.d("aa", observerFormFour.getFormfour().getResponse().getReport3().getVehicleChecklist().replace("\\\\", ""));

            if (TextUtils.isEmpty(observerFormFour.getFormfour().getResponse().getReport3().getVehicleChecklist())) {
                report3General = new Report3General();
                for (int i = 0; i < 3; i++) {
                    general.add(new General());
                }
            } else {
                report3General = new Gson().fromJson(observerFormFour.getFormfour().getResponse().getReport3().getVehicleChecklist().replace("\\\\", ""), Report3General.class);
                if (report3General != null) {
                    general = report3General.getGeneral();
                }
            }

            //  report3General = new Gson().fromJson(observerFormFour.getFormfour().getResponse().getReport3().getVehicleChecklist().replace("\\\\", ""), Report3General.class);

            if (report3General != null) {
                //  general = report3General.getGeneral();
                for (int i = 0; i < general.size(); i++) {
                    switch (i) {
                        case 0:
                            if (general.get(i).getGeneralMechanical().equals("1")) {
                                chk1.setChecked(true);
                            } else {
                                chk1.setChecked(false);
                            }
                            edit_comment1.setText(general.get(i).getGeneralMechanicalComment());
                            break;
                        case 1:
                            if (general.get(i).getGeneralBodyWork().equals("1")) {
                                chk2.setChecked(true);
                            } else {
                                chk2.setChecked(false);
                            }
                            edit_comment2.setText(general.get(i).getGeneralBodyWorkComment());
                            if (!TextUtils.isEmpty(general.get(i).getGeneralBodyWorkImages())) {
                                if (general.get(i).getGeneralBodyWorkImages().startsWith("data")) {

                                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                                    base64ToBitmap.execute(general.get(i).getGeneralBodyWorkImages());
                                    base64ToBitmap.enqueue(new GetBitmap() {
                                        @Override
                                        public void onSuccess(Bitmap bitmap) {
                                            observerFormFour.getFormfour().getResponse().getReport3().setBitmap1(bitmap);
                                            img1.setImageBitmap(bitmap);
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            e.printStackTrace();
                                        }
                                    });

                                } else {
                                    UrlToBitmap urltobitmap = new UrlToBitmap();

                                    urltobitmap.execute(AppConstant.IMAGE_URL + general.get(i).getGeneralBodyWorkImages());
                                    urltobitmap.enqueue(new GetBitmap() {
                                        @Override
                                        public void onSuccess(Bitmap bitmap) {
                                            observerFormFour.getFormfour().getResponse().getReport3().setBitmap1(bitmap);
                                            img1.setImageBitmap(bitmap);
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            e.printStackTrace();
                                        }
                                    });
                                  //  ImageLoader.getInstance().displayImage(AppConstant.IMAGE_URL + general.get(i).getGeneralBodyWorkImages(), img1);
                                }
                            }
                            break;
                        case 2:
                            if (general.get(i).getConditionInteriorWork().equals("1")) {
                                chk3.setChecked(true);
                            } else {
                                chk3.setChecked(false);
                            }
                            edit_comment3.setText(general.get(i).getConditionInteriorComment());
                            if (!TextUtils.isEmpty(general.get(i).getConditionInteriorImages())) {
                                if (general.get(i).getConditionInteriorImages().startsWith("data")) {

                                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                                    base64ToBitmap.execute(general.get(i).getConditionInteriorImages());
                                    base64ToBitmap.enqueue(new GetBitmap() {
                                        @Override
                                        public void onSuccess(Bitmap bitmap) {
                                            observerFormFour.getFormfour().getResponse().getReport3().setBitmap2(bitmap);
                                            img2.setImageBitmap(bitmap);
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            e.printStackTrace();
                                        }
                                    });

                                } else {
                                    UrlToBitmap urltobitmap = new UrlToBitmap();

                                    urltobitmap.execute(AppConstant.IMAGE_URL + general.get(i).getConditionInteriorImages());
                                    urltobitmap.enqueue(new GetBitmap() {
                                        @Override
                                        public void onSuccess(Bitmap bitmap) {
                                            observerFormFour.getFormfour().getResponse().getReport3().setBitmap2(bitmap);
                                            img2.setImageBitmap(bitmap);
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            e.printStackTrace();
                                        }
                                    });
//                                    ImageLoader.getInstance().displayImage(AppConstant.IMAGE_URL + general.get(i).getConditionInteriorImages(), img2);
                                }
                            }
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
                general.get(0).setGeneralMechanical(getCheckNumber(isChecked));
            }
        });
        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                general.get(1).setGeneralBodyWork(getCheckNumber(isChecked));
            }
        });
        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                general.get(2).setConditionInteriorWork(getCheckNumber(isChecked));
            }
        });

        edit_comment1.addTextChangedListener(this);
        edit_comment2.addTextChangedListener(this);
        edit_comment3.addTextChangedListener(this);
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
            general.get(0).setGeneralMechanicalComment(s.toString());
        } else if (s == edit_comment2.getEditableText()) {
            general.get(1).setGeneralBodyWorkComment(s.toString());
        } else if (s == edit_comment3.getEditableText()) {
            general.get(2).setConditionInteriorComment(s.toString());
        }
    }

    private EditText edit_comment1, edit_comment2, edit_comment3;
    private CheckBox chk1, chk2, chk3;
    private ImageView img1, img2;

    private void initUI() {
        edit_comment1 = (EditText) convertView.findViewById(R.id.edit_comment_form44_1);
        edit_comment2 = (EditText) convertView.findViewById(R.id.edit_comment_form44_2);
        edit_comment3 = (EditText) convertView.findViewById(R.id.edit_comment_form44_3);
        chk1 = (CheckBox) convertView.findViewById(R.id.chk_form44_1);
        chk2 = (CheckBox) convertView.findViewById(R.id.chk_form44_2);
        chk3 = (CheckBox) convertView.findViewById(R.id.chk_form44_3);
        img1 = (ImageView) convertView.findViewById(R.id.img_form44_1);
        img2 = (ImageView) convertView.findViewById(R.id.img_form44_2);


        img1.setOnClickListener(this);
        img2.setOnClickListener(this);

        txt_next = (TextView) convertView.findViewById(R.id.txt_next);
        txt_back = (TextView) convertView.findViewById(R.id.txt_back);

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList();
                ((VehicleForm4) getActivity()).viewPager.setCurrentItem(3);
            }
        });
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList();
                ((VehicleForm4) getActivity()).viewPager.setCurrentItem(1);
            }
        });
    }

    private void updateList() {
        report3General.setGeneral(general);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(report3General);
        Log.d("list", json);
        observerFormFour.getFormfour().getResponse().getReport3().setVehicleChecklist(json);
    }

    private void takePicture(int current_image) {
        long time = System.currentTimeMillis();
        if (current_image == 1) {
            path1 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
            file = new File(path1);
        } else if (current_image == 2) {
            path2 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
            file = new File(path2);
        }
        //  path1 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
        //File file = new File(path1);
        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, AppConstant.REQUEST_CODE_TAKE_PICTURE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_form44_1:
                current_image = 1;
                takePicture(current_image);
                break;

            case R.id.img_form44_2:
                current_image = 2;
                takePicture(current_image);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != getActivity().RESULT_OK) {
            return;
        }

        switch (requestCode) {

            case AppConstant.REQUEST_CODE_TAKE_PICTURE:
                try {
                    if (current_image == 1) {
                        Log.e("path", path1);
                        Bitmap bm = ExifUtils.decodeFile(path1);
                        bm = ExifUtils.rotateBitmap(path1, bm);
                        img1.setImageBitmap(bm);
                        observerFormFour.getFormfour().getResponse().getReport3().setBitmap1(bm);
                        general.get(1).setGeneralBodyWorkImages(FunctionUtils.getInstance().encodeToBase64(bm).trim());

                    } else if (current_image == 2) {
                        Log.e("path", path2);
                        Bitmap bm = ExifUtils.decodeFile(path2);
                        bm = ExifUtils.rotateBitmap(path2, bm);
                        img2.setImageBitmap(bm);
                        observerFormFour.getFormfour().getResponse().getReport3().setBitmap2(bm);
                        general.get(2).setConditionInteriorImages(FunctionUtils.getInstance().encodeToBase64(bm).trim());

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


}