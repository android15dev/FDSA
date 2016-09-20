package com.nkdroidsolutions.firedefence.fragment.Form_1_New;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.activity.Form1Activity;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.util.ExifUtils;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nkdroidsolutions.firedefence.web_api.UrlToBitmap;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

/**
 * Created by Sahil on 6/24/2016.
 */
public class Form15 extends Fragment implements AllObserver {

    private String path1 = "", path2 = "", path3 = "";
    private File main_dir;
    private File file;
    Bitmap uImage;

    public static int current_image = 1;
    byte[] ba;
    View view;
    Context context;
    Activity activity;
    ImageView ivThirdForm15_new, ivSecondForm15_new, ivFirstForm15_new;
    EditText etPartsUsedFragment15New, etEngineersCommentsFragment15New;

    public static Form15 newInstance() {
        return new Form15();
    }

    public Form15() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_form15_new, container, false);

        main_dir = new File(Environment.getExternalStorageDirectory() + "/" + AppConstant.FOLDER_NAME);
        if (!main_dir.exists()) {
            main_dir.mkdir();
        }

        return view;
    }

    private void takePicture(int current_image) {
        long time = System.currentTimeMillis();
        if (current_image == 4) {
            path1 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
            file = new File(path1);
        } else if (current_image == 5) {
            path2 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
            file = new File(path2);
        } else if (current_image == 6) {
            path3 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
            file = new File(path3);
        }
        //  path1 = main_dir.getAbsolutePath() + "/" + time + ".jpg";
        //File file = new File(path1);
        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, current_image);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        this.activity = activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
        listners();

        if (observerFormOne.getFormone() != null) {
            etEngineersCommentsFragment15New.setText(observerFormOne.getFormone().getResponse().getReport5().getEngineerNote());
            etPartsUsedFragment15New.setText(observerFormOne.getFormone().getResponse().getReport5().getPartsUsed());

            if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage1())) {
                if (observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage1().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage1());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImageBitmap1(bitmap);
                            ivFirstForm15_new.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage1());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImageBitmap1(bitmap);
                            ivFirstForm15_new.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
/*                    ImageLoader.getInstance().displayImage(
                            AppConstant.IMAGE_URL + observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage1(), ivFirstForm15_new);*/
                }
            }
            if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage2())) {
                if (observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage2().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage2());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImageBitmap2(bitmap);
                            ivSecondForm15_new.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage2());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImageBitmap2(bitmap);
                            ivSecondForm15_new.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
 //                   ImageLoader.getInstance().displayImage(AppConstant.IMAGE_URL + observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage2(), ivSecondForm15_new);
                }
            }
            if (!TextUtils.isEmpty(observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage3())) {
                if (observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage3().startsWith("data")) {

                    Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                    base64ToBitmap.execute(observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage3());
                    base64ToBitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImageBitmap3(bitmap);
                            ivThirdForm15_new.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    UrlToBitmap urltobitmap = new UrlToBitmap();

                    urltobitmap.execute(AppConstant.IMAGE_URL + observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage3());
                    urltobitmap.enqueue(new GetBitmap() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImageBitmap3(bitmap);
                            ivThirdForm15_new.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
//                    ImageLoader.getInstance().displayImage(AppConstant.IMAGE_URL + observerFormOne.getFormone().getResponse().getReport5().getImage().getPartsImage3(), ivThirdForm15_new);
                }
            }
        }


    }

    private void init() {
        etEngineersCommentsFragment15New = (EditText) view.findViewById(R.id.etEngineersCommentsFragment15New);
        etPartsUsedFragment15New = (EditText) view.findViewById(R.id.etPartsUsedFragment15New);
        ivThirdForm15_new = (ImageView) view.findViewById(R.id.ivThirdForm15_new);
        ivFirstForm15_new = (ImageView) view.findViewById(R.id.ivFirstForm15_new);
        ivSecondForm15_new = (ImageView) view.findViewById(R.id.ivSecondForm15_new);

        ivFirstForm15_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(4);
            }
        });

        ivSecondForm15_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(5);
            }
        });

        ivThirdForm15_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(6);
            }
        });


        getActivity().findViewById(R.id.back123).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("1") ||
                        observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("2") ||
                        observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("3") ||
                        observerFormOne.getFormone().getResponse().getReport3().getReasonForVisit().contains("4")) {

                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(3);
                } else {
                    ((Form1Activity) getActivity()).viewPager.setCurrentItem(2);
                }
            }
        });

        getActivity().findViewById(R.id.next123).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Form1Activity) getActivity()).viewPager.setCurrentItem(5);

            }
        });
    }

    private void listners() {
        etEngineersCommentsFragment15New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport5().setEngineerNote(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPartsUsedFragment15New.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observerFormOne.getFormone().getResponse().getReport5().setPartsUsed(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private static final String TEMP_PHOTO_FILE = "temporary_holder.jpg";

    private void pickImage(int resultCode) {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, resultCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != getActivity().RESULT_OK) {
            return;
        }

        switch (requestCode) {

            case 4:
                try {
                    Log.e("path", path1);
                    Bitmap bm = ExifUtils.decodeFile(path1);
                    bm = ExifUtils.rotateBitmap(path1, bm);
                    ivFirstForm15_new.setImageBitmap(bm);
                    observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImageBitmap1(bm);
                    observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImage1(
                            FunctionUtils.getInstance().encodeToBase64(bm).trim());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                try {

                    Log.e("path", path2);
                    Bitmap bm = ExifUtils.decodeFile(path2);
                    bm = ExifUtils.rotateBitmap(path2, bm);
                    ivSecondForm15_new.setImageBitmap(bm);
                    observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImageBitmap2(bm);
                    observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImage2(
                            FunctionUtils.getInstance().encodeToBase64(bm).trim());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                try {

                    Log.e("path", path3);
                    Bitmap bm = ExifUtils.decodeFile(path3);
                    bm = ExifUtils.rotateBitmap(path3, bm);
                    ivThirdForm15_new.setImageBitmap(bm);
                    observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImageBitmap3(bm);
                    observerFormOne.getFormone().getResponse().getReport5().getImage().setPartsImage3(
                            FunctionUtils.getInstance().encodeToBase64(bm).trim());


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}