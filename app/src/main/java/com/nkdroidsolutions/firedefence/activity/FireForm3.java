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
import com.nkdroidsolutions.firedefence.fragment.Form_3.Form31;
import com.nkdroidsolutions.firedefence.fragment.Form_3.Form32;
import com.nkdroidsolutions.firedefence.fragment.Form_3.Form33;
import com.nkdroidsolutions.firedefence.fragment.Form_3.Form34;
import com.nkdroidsolutions.firedefence.fragment.Form_3.Form35;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form3Model.FormThreeProp;
import com.nkdroidsolutions.firedefence.model.Form3Model.Report1;
import com.nkdroidsolutions.firedefence.model.Form3Model.Report2;
import com.nkdroidsolutions.firedefence.model.Form3Model.Report3;
import com.nkdroidsolutions.firedefence.model.Form3Model.Report4;
import com.nkdroidsolutions.firedefence.model.Form3Model.Report5;
import com.nkdroidsolutions.firedefence.model.Form3Model.Response;
import com.nkdroidsolutions.firedefence.model.LocalFormProp;
import com.nkdroidsolutions.firedefence.model.NonSwipeableViewPAger;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import github.chenupt.springindicator.SpringIndicator;

public class FireForm3 extends AppCompatActivity implements AllObserver {

    private Toolbar toolbar;
    public ViewPager viewPager;
    private TabPagerAdapter tabPagerAdapter;
    private LinearLayout rootLayout;
    private Form33 sampleFragment2;
    public static String id = "";
    private TextView txt_save, toolbarTitle;
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
        viewPager = (NonSwipeableViewPAger) findViewById(R.id.view_pager);
        txt_save = (TextView) findViewById(R.id.txt_save);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        viewPager.setAdapter(tabPagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        // just set viewPager
        springIndicator.setViewPager(viewPager);

        if (getIntent().getBooleanExtra(AppConstant.IS_NEW, true)) {
            id = "";
            observerFormThree.setFormthree(new FormThreeProp());
            observerFormThree.getFormthree().setResponse(new Response());
            observerFormThree.getFormthree().getResponse().setReport1(new Report1());
            observerFormThree.getFormthree().getResponse().setReport2(new Report2());
            observerFormThree.getFormthree().getResponse().setReport3(new Report3());
            observerFormThree.getFormthree().getResponse().setReport4(new Report4());
            observerFormThree.getFormthree().getResponse().setReport5(new Report5());
        } else {
            id = getIntent().getStringExtra("id");
        }

        txt_save.setVisibility(View.VISIBLE);

        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
                String json = gson.toJson(observerFormThree.getFormthree());

                LocalFormProp prop = new LocalFormProp();
                prop.setForm_data(json);
                prop.setForm_id(FireForm3.id);
                prop.setForm_type("3");
                db.addLocalForm(prop);
                FunctionUtils.getInstance().showToast("Saved");

            }
        });

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Form 3");

            setSupportActionBar(toolbar);
            toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
            toolbarTitle.setText("Fire Extinguisher Check List");
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
                    Form31 sampleFragment = Form31.newInstance();
                    return sampleFragment;

                case 1:
                    Form32 sampleFragment1 = Form32.newInstance();
                    return sampleFragment1;

                case 2:
                    sampleFragment2 = Form33.newInstance();
                    return sampleFragment2;

                case 3:
                    Form34 sampleFragment4 = Form34.newInstance();
                    return sampleFragment4;

                case 4:
                    Form35 sampleFragment5 = Form35.newInstance();
                    return sampleFragment5;

            }
            Form31 sampleFragment = Form31.newInstance();
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
        sampleFragment2.onActivityResult(requestCode, resultCode, data);

    }
}
