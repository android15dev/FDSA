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
import com.nkdroidsolutions.firedefence.fragment.Form_4.Form41;
import com.nkdroidsolutions.firedefence.fragment.Form_4.Form42;
import com.nkdroidsolutions.firedefence.fragment.Form_4.Form43;
import com.nkdroidsolutions.firedefence.fragment.Form_4.Form44;
import com.nkdroidsolutions.firedefence.fragment.Form_4.Form45;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form4Model.FormFourProp;
import com.nkdroidsolutions.firedefence.model.Form4Model.Report1;
import com.nkdroidsolutions.firedefence.model.Form4Model.Report2;
import com.nkdroidsolutions.firedefence.model.Form4Model.Report3;
import com.nkdroidsolutions.firedefence.model.Form4Model.Report4;
import com.nkdroidsolutions.firedefence.model.Form4Model.Report5;
import com.nkdroidsolutions.firedefence.model.Form4Model.Response;
import com.nkdroidsolutions.firedefence.model.LocalFormProp;
import com.nkdroidsolutions.firedefence.model.NonSwipeableViewPAger;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import github.chenupt.springindicator.SpringIndicator;

public class VehicleForm4 extends AppCompatActivity implements AllObserver {

    private Toolbar toolbar;
    public ViewPager viewPager;
    private TabPagerAdapter tabPagerAdapter;
    private LinearLayout rootLayout;
    private TextView toolbarTitle;
    public static String id = "";
    private Form42 sampleFragment1;
    private TextView txt_save;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

        db = new Database(this);

        setToolbar();
        initUI();

    }

    private void initUI() {
        txt_save = (TextView) findViewById(R.id.txt_save);

        viewPager = (NonSwipeableViewPAger) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        viewPager.setAdapter(tabPagerAdapter);
        springIndicator.setViewPager(viewPager);

        if (getIntent().getBooleanExtra(AppConstant.IS_NEW, true)) {
            id = "";
            observerFormFour.setFormfour(new FormFourProp());
            observerFormFour.getFormfour().setResponse(new Response());
            observerFormFour.getFormfour().getResponse().setReport1(new Report1());
            observerFormFour.getFormfour().getResponse().setReport2(new Report2());
            observerFormFour.getFormfour().getResponse().setReport3(new Report3());
            observerFormFour.getFormfour().getResponse().setReport4(new Report4());
            observerFormFour.getFormfour().getResponse().setReport5(new Report5());
        } else {
            id = getIntent().getStringExtra("id");
        }

        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
                String json = gson.toJson(observerFormFour.getFormfour());

                LocalFormProp prop = new LocalFormProp();
                prop.setForm_data(json);
                prop.setForm_id(VehicleForm4.id);
                prop.setForm_type("4");
                db.addLocalForm(prop);
                FunctionUtils.getInstance().showToast("Saved");

            }
        });

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Form 4");
            setSupportActionBar(toolbar);
            toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
            toolbarTitle.setText("Vehicle Checklist");
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
                    Form41 sampleFragment = Form41.newInstance();
                    return sampleFragment;

                case 1:
                    sampleFragment1 = Form42.newInstance();
                    return sampleFragment1;

                case 2:
                    Form43 sampleFragment3 = Form43.newInstance();
                    return sampleFragment3;

                case 3:
                    Form44 sampleFragment4 = Form44.newInstance();
                    return sampleFragment4;

                case 4:
                    Form45 sampleFragment5 = Form45.newInstance();
                    return sampleFragment5;

            }
            Form41 sampleFragment = Form41.newInstance();
            return sampleFragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ++position + "";
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //   sampleFragment1.onActivityResult(requestCode, resultCode, data);

    }

}
