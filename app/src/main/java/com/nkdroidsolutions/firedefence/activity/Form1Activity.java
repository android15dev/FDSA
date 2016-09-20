package com.nkdroidsolutions.firedefence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.fragment.Form_1_New.Form11;
import com.nkdroidsolutions.firedefence.fragment.Form_1_New.Form12;
import com.nkdroidsolutions.firedefence.fragment.Form_1_New.Form13;
import com.nkdroidsolutions.firedefence.fragment.Form_1_New.Form14;
import com.nkdroidsolutions.firedefence.fragment.Form_1_New.Form15;
import com.nkdroidsolutions.firedefence.fragment.Form_1_New.Form16;
import com.nkdroidsolutions.firedefence.fragment.Form_1_New.Form17;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form1Model.FormOneProp;
import com.nkdroidsolutions.firedefence.model.Form1Model.Image;
import com.nkdroidsolutions.firedefence.model.Form1Model.Report1;
import com.nkdroidsolutions.firedefence.model.Form1Model.Report2;
import com.nkdroidsolutions.firedefence.model.Form1Model.Report3;
import com.nkdroidsolutions.firedefence.model.Form1Model.Report4;
import com.nkdroidsolutions.firedefence.model.Form1Model.Report5;
import com.nkdroidsolutions.firedefence.model.Form1Model.Report6;
import com.nkdroidsolutions.firedefence.model.Form1Model.Report7;
import com.nkdroidsolutions.firedefence.model.Form1Model.Response;
import com.nkdroidsolutions.firedefence.model.LocalFormProp;
import com.nkdroidsolutions.firedefence.model.NonSwipeableViewPAger;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import github.chenupt.springindicator.SpringIndicator;


public class Form1Activity extends AppCompatActivity implements AllObserver {

    private Toolbar toolbar;
    public ViewPager viewPager;
    private TabPagerAdapter tabPagerAdapter;
    public LinearLayout rootLayout;
    private TextView toolbarTitle;

    public static String id = "";
    private Form15 sampleFragment4;
    private TextView txt_save;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        setToolbar();
        db = new Database(this);

        txt_save = (TextView) findViewById(R.id.txt_save);

        viewPager = (NonSwipeableViewPAger) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        viewPager.setAdapter(tabPagerAdapter);


        if (getIntent().getBooleanExtra(AppConstant.IS_NEW, true)) {
            id = "";
            observerFormOne.setFormone(new FormOneProp());
            observerFormOne.getFormone().setResponse(new Response());
            observerFormOne.getFormone().getResponse().setReport1(new Report1());
            observerFormOne.getFormone().getResponse().setReport2(new Report2());
            observerFormOne.getFormone().getResponse().setReport3(new Report3());
            observerFormOne.getFormone().getResponse().setReport4(new Report4());
            observerFormOne.getFormone().getResponse().setReport5(new Report5());
            observerFormOne.getFormone().getResponse().getReport5().setImage(new Image());
            observerFormOne.getFormone().getResponse().setReport6(new Report6());
            observerFormOne.getFormone().getResponse().setReport7(new Report7());
        } else {
            id = getIntent().getStringExtra("id");
        }

        // just set viewPager
        springIndicator.setViewPager(viewPager);

        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
                String json = gson.toJson(observerFormOne.getFormone());

                LocalFormProp prop = new LocalFormProp();
                prop.setForm_data(json);
                prop.setForm_id(Form1Activity.id);
                prop.setForm_type("1");
                db.addLocalForm(prop);
                FunctionUtils.getInstance().showToast("Saved");

            }
        });
    }


    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Form 1");
            setSupportActionBar(toolbar);
            toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
            toolbarTitle.setVisibility(View.VISIBLE);
            toolbarTitle.setText("Engineers Report");
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    class TabPagerAdapter extends FragmentStatePagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:
                    Form11 sampleFragment = Form11.newInstance();
                    return sampleFragment;


                case 1:
                    Form12 sampleFragment1 = Form12.newInstance();
                    return sampleFragment1;


                case 2:
                    Form13 sampleFragment2 = Form13.newInstance();
                    return sampleFragment2;


                case 3:
                    Form14 sampleFragment3 = Form14.newInstance();
                    return sampleFragment3;


                case 4:
                    sampleFragment4 = Form15.newInstance();
                    return sampleFragment4;

                case 5:
                    Form16 sampleFragment5 = Form16.newInstance();
                    return sampleFragment5;

                case 6:
                    Form17 sampleFragment6 = Form17.newInstance();
                    return sampleFragment6;
            }
            Form11 sampleFragment = Form11.newInstance();
            return sampleFragment;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ++position + "";
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (sampleFragment4 != null) {
            sampleFragment4.onActivityResult(requestCode, resultCode, data);
        }

    }
}
