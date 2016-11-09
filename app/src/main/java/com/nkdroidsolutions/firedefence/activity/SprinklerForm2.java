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
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form21;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form22;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form23;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form24;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form25;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form26;
import com.nkdroidsolutions.firedefence.fragment.Form_2.Form27;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form2Model.FormTwoProp;
import com.nkdroidsolutions.firedefence.model.Form2Model.Report1;
import com.nkdroidsolutions.firedefence.model.Form2Model.Report2;
import com.nkdroidsolutions.firedefence.model.Form2Model.Report3;
import com.nkdroidsolutions.firedefence.model.Form2Model.Report4;
import com.nkdroidsolutions.firedefence.model.Form2Model.Report5;
import com.nkdroidsolutions.firedefence.model.Form2Model.Report6;
import com.nkdroidsolutions.firedefence.model.Form2Model.Report7;
import com.nkdroidsolutions.firedefence.model.Form2Model.Response;
import com.nkdroidsolutions.firedefence.model.LocalFormProp;
import com.nkdroidsolutions.firedefence.model.NonSwipeableViewPAger;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

import github.chenupt.springindicator.SpringIndicator;

public class SprinklerForm2 extends AppCompatActivity implements AllObserver {

    private Toolbar toolbar;
    public ViewPager viewPager;
    private TabPagerAdapter tabPagerAdapter;
    private LinearLayout rootLayout;
    private Form25 sampleFragment4;
    private TextView toolbarTitle;
    public static String id = "";
    private Database db;
    private TextView txt_save;


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
            observerFormTwo.setFormTwo(new FormTwoProp());
            observerFormTwo.getFormTwo().setResponse(new Response());
            observerFormTwo.getFormTwo().getResponse().setReport1(new Report1());
            observerFormTwo.getFormTwo().getResponse().setReport2(new Report2());
            observerFormTwo.getFormTwo().getResponse().setReport3(new Report3());
            observerFormTwo.getFormTwo().getResponse().setReport4(new Report4());
            observerFormTwo.getFormTwo().getResponse().setReport5(new Report5());
            observerFormTwo.getFormTwo().getResponse().setReport6(new Report6());
            observerFormTwo.getFormTwo().getResponse().setReport7(new Report7());
        } else {
            id = getIntent().getStringExtra("id");
        }

        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
                String json = gson.toJson(observerFormTwo.getFormTwo());

                LocalFormProp prop = new LocalFormProp();
                prop.setForm_data(json);
                prop.setForm_id(SprinklerForm2.id);
                prop.setForm_type("2");
                db.addLocalForm(prop);
                FunctionUtils.getInstance().showToast("Saved");

            }
        });

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Form 2");
            setSupportActionBar(toolbar);
            toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
            toolbarTitle.setText("Sprinkler Maintenance Check List");
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
                    Form21 sampleFragment = Form21.newInstance();
                    return sampleFragment;


                case 1:
                    Form22 sampleFragment1 = Form22.newInstance();
                    return sampleFragment1;


                case 2:
                    Form23 sampleFragment2 = Form23.newInstance();
                    return sampleFragment2;


                case 3:
                    Form24 sampleFragment3 = Form24.newInstance();
                    return sampleFragment3;


                case 4:
                    sampleFragment4 = Form25.newInstance();
                    return sampleFragment4;

                case 5:
                    Form26 sampleFragment5 = Form26.newInstance();
                    return sampleFragment5;

                case 6:
                    Form27 sampleFragment6 = Form27.newInstance();
                    return sampleFragment6;

            }
            Form21 sampleFragment = Form21.newInstance();
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

        sampleFragment4.onActivityResult(requestCode, resultCode, data);

    }


}
