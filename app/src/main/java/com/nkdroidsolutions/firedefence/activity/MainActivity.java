package com.nkdroidsolutions.firedefence.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;
import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.adapters.MainList_Forms_Adapter;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form1Model.FormOneProp;
import com.nkdroidsolutions.firedefence.model.Form2Model.FormTwoProp;
import com.nkdroidsolutions.firedefence.model.Form3Model.FormThreeProp;
import com.nkdroidsolutions.firedefence.model.Form4Model.FormFourProp;
import com.nkdroidsolutions.firedefence.model.LocalFormProp;
import com.nkdroidsolutions.firedefence.model.allform.AllForm;
import com.nkdroidsolutions.firedefence.storage.Database;
import com.nkdroidsolutions.firedefence.util.ConnectionChecker;
import com.nkdroidsolutions.firedefence.util.FunctionUtils;
import com.nkdroidsolutions.firedefence.util.PrefUtils;
import com.nkdroidsolutions.firedefence.util.handlers.FormFourHandler;
import com.nkdroidsolutions.firedefence.util.handlers.FormOneHandler;
import com.nkdroidsolutions.firedefence.util.handlers.FormThreeHandler;
import com.nkdroidsolutions.firedefence.util.handlers.FormTwoHandler;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.util.observer.Observer_AllForm;
import com.nkdroidsolutions.firedefence.web_api.Fire_API;
import com.nkdroidsolutions.firedefence.web_api.WebHandling;

import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AllObserver, Observer {

    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;

    private TextView tvDate, txt_notes;

    private com.getbase.floatingactionbutton.FloatingActionButton actionA, actionB, actionC, actionD;
    private FloatingActionsMenu multiple_actions;
    private TextView txt_save, toolbarTitle;
    private RecyclerView recycler;
    private MainList_Forms_Adapter adp;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        db = new Database(this);

        multiple_actions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);

        actionA = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_a);
        actionB = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_b);
        actionC = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_c);
        actionD = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_d);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("action", "click 1");
                Intent i = new Intent(MainActivity.this, Form1Activity.class);
                i.putExtra(AppConstant.IS_NEW, true);
                startActivity(i);
                multiple_actions.collapse();
            }
        });

        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("action", "click 2");

                Intent i = new Intent(MainActivity.this, SprinklerForm2.class);
                i.putExtra(AppConstant.IS_NEW, true);
                startActivity(i);
                multiple_actions.collapse();

            }
        });

        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("action", "click 3");
                Intent i = new Intent(MainActivity.this, FireForm3.class);
                i.putExtra(AppConstant.IS_NEW, true);
                startActivity(i);
                multiple_actions.collapse();

            }
        });

        actionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("action", "click 4");
                Intent i = new Intent(MainActivity.this, VehicleForm4.class);
                i.putExtra(AppConstant.IS_NEW, true);
                startActivity(i);
                multiple_actions.collapse();

            }
        });

        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.white));

        setToolbar();
        initView();
        tvDate = (TextView) findViewById(R.id.tvDate);
        txt_notes = (TextView) findViewById(R.id.txt_notes);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adp = new MainList_Forms_Adapter();
        recycler.setAdapter(adp);

        adp.setOnItemClickListner(new MainList_Forms_Adapter.OnClickedListner() {
            @Override
            public void onItemClick(View view, final int position) {

                final ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Getting form details...");
                pDialog.show();


                LocalFormProp localForm = db.getLocalFormbyFormId(observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId());

                if (observerAllForm.getAllForm().getResponse().getForm().get(position).getFormType().equals("1")) {
                    if (localForm == null) {


                        WebHandling.getInstance().getFormOneDetail(AppConstant.ACTION_GETFORMDETAIL, observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId(),
                                observerAllForm.getAllForm().getResponse().getForm().get(position).getFormType(), new FormOneHandler() {
                                    @Override
                                    public void onSuccess(FormOneProp formOneProp) {
                                        pDialog.dismiss();
                                        if (formOneProp != null) {
                                            try {

                                                String json = new Gson().toJson(formOneProp.getResponse());
                                                Log.d("aaaa", " ccccccc        " + json);

                                                observerFormOne.setFormone(formOneProp);

                                                Intent i = new Intent(MainActivity.this, Form1Activity.class);
                                                i.putExtra(AppConstant.IS_NEW, false);
                                                i.putExtra("id", observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId());
                                                startActivity(i);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                    }
                                });
                    } else {
                        pDialog.dismiss();
                        FormOneProp formOneProp = new Gson().fromJson(localForm.getForm_data(), FormOneProp.class);

                        observerFormOne.setFormone(formOneProp);

                        Intent i = new Intent(MainActivity.this, Form1Activity.class);
                        i.putExtra(AppConstant.IS_NEW, false);
                        i.putExtra("id", observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId());
                        startActivity(i);
                    }
                } else if (observerAllForm.getAllForm().getResponse().getForm().get(position).getFormType().equals("2")) {
                    if (localForm == null) {
                        WebHandling.getInstance().getFormTwoDetail(AppConstant.ACTION_GETFORMDETAIL, observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId(),
                                observerAllForm.getAllForm().getResponse().getForm().get(position).getFormType(), new FormTwoHandler() {
                                    @Override
                                    public void onSuccess(FormTwoProp formTwoProp) {
                                        pDialog.dismiss();
                                        if (formTwoProp != null) {
                                            try {
                                                String json = new Gson().toJson(formTwoProp.getResponse());
                                                Log.d("aaaa", " ccccccc        " + json);

                                                observerFormTwo.setFormTwo(formTwoProp);

                                                Intent i = new Intent(MainActivity.this, SprinklerForm2.class);
                                                i.putExtra(AppConstant.IS_NEW, false);
                                                i.putExtra("id", observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId());
                                                startActivity(i);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                    } else {
                        pDialog.dismiss();
                        FormTwoProp formTwoProp = new Gson().fromJson(localForm.getForm_data(), FormTwoProp.class);

                        observerFormTwo.setFormTwo(formTwoProp);

                        Intent i = new Intent(MainActivity.this, SprinklerForm2.class);
                        i.putExtra(AppConstant.IS_NEW, false);
                        i.putExtra("id", observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId());
                        startActivity(i);
                    }

                } else if (observerAllForm.getAllForm().getResponse().getForm().get(position).getFormType().equals("3")) {

                    if (localForm == null) {
                        WebHandling.getInstance().getFormThreeDetail(AppConstant.ACTION_GETFORMDETAIL, observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId(),
                                observerAllForm.getAllForm().getResponse().getForm().get(position).getFormType(), new FormThreeHandler() {
                                    @Override
                                    public void onSuccess(FormThreeProp formThreeProp) {
                                        pDialog.dismiss();
                                        if (formThreeProp != null) {
                                            try {
                                                String json = new Gson().toJson(formThreeProp.getResponse());
                                                Log.d("aaaa", " ccccccc        " + json);

                                                observerFormThree.setFormthree(formThreeProp);

                                                Intent i = new Intent(MainActivity.this, FireForm3.class);
                                                i.putExtra(AppConstant.IS_NEW, false);
                                                i.putExtra("id", observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId());
                                                startActivity(i);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                    } else {
                        pDialog.dismiss();
                        FormThreeProp formThreeProp = new Gson().fromJson(localForm.getForm_data(), FormThreeProp.class);

                        observerFormThree.setFormthree(formThreeProp);

                        Intent i = new Intent(MainActivity.this, FireForm3.class);
                        i.putExtra(AppConstant.IS_NEW, false);
                        i.putExtra("id", observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId());
                        startActivity(i);
                    }
                } else if (observerAllForm.getAllForm().getResponse().getForm().get(position).getFormType().equals("4")) {
                    if (localForm == null) {
                        WebHandling.getInstance().getFormFourDetail(AppConstant.ACTION_GETFORMDETAIL, observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId(),
                                observerAllForm.getAllForm().getResponse().getForm().get(position).getFormType(), new FormFourHandler() {
                                    @Override
                                    public void onSuccess(FormFourProp formFourProp) {
                                        pDialog.dismiss();
                                        if (formFourProp != null) {
                                            try {
                                                String json = new Gson().toJson(formFourProp.getResponse());
                                                Log.d("aaaa", " ccccccc        " + json);

                                                observerFormFour.setFormfour(formFourProp);

                                                Intent i = new Intent(MainActivity.this, VehicleForm4.class);
                                                i.putExtra(AppConstant.IS_NEW, false);
                                                i.putExtra("id", observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId());
                                                startActivity(i);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                    } else {
                        pDialog.dismiss();
                        FormFourProp formFourProp = new Gson().fromJson(localForm.getForm_data(), FormFourProp.class);

                        observerFormFour.setFormfour(formFourProp);

                        Intent i = new Intent(MainActivity.this, VehicleForm4.class);
                        i.putExtra(AppConstant.IS_NEW, false);
                        i.putExtra("id", observerAllForm.getAllForm().getResponse().getForm().get(position).getFormId());
                        startActivity(i);
                    }

                } else {
                    pDialog.dismiss();
                }

            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId = savedInstanceState == null ? R.id.navigation_item_1 : savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        observerAllForm.addObserver(this);

        tvDate.setText("Date : " + FunctionUtils.getInstance().getCurrentDate());
        if (ConnectionChecker.getConnectionInfo(MainActivity.this) == AppConstant.TYPE_NOT_CONNECTED) {
            Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();
        } else {
            getForms();
//            callLoginService();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        observerAllForm.deleteObserver(this);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_save = (TextView) findViewById(R.id.txt_save);
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        txt_save.setVisibility(View.GONE);
        toolbarTitle.setVisibility(View.GONE);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void initView() {
        mDrawer = (NavigationView) findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void itemSelection(int mSelectedId) {


        switch (mSelectedId) {

            case R.id.navigation_item_1:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.navigation_item_2:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;


        }

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID", mSelectedId);
    }


    public void getForms() {

//        String url = "http://fire-defence.net/ws/api.php?action=return_user_forms&user_id=1";
        //     String url = "http://fire-defence.net/ws/api.php?action=return_user_forms&user_id=" + PrefUtils.getCurrentUser(MainActivity.this).userId;

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Fire_API fire_api = retrofit.create(Fire_API.class);

        Call<AllForm> call = fire_api.getAllForm(AppConstant.ACTION_GETALLFORM, PrefUtils.getCurrentUser(this).userId);

        call.enqueue(new Callback<AllForm>() {
            @Override
            public void onResponse(Call<AllForm> call, retrofit2.Response<AllForm> response) {
                try {

/*                    String json = new Gson().toJson(response.body().getResponse());
                    Log.d("aaaa", " ccccccc        " + json);*/

                    observerAllForm.setAllForm(response.body());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                pDialog.dismiss();

            }

            @Override
            public void onFailure(Call<AllForm> call, Throwable t) {
                pDialog.dismiss();

            }
        });


    }

    /*public void getFormDetails(final int position) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Fire_API fire_api = retrofit.create(Fire_API.class);
        Call<FormOneProp> call = fire_api.getFormDetail(AppConstant.ACTION_GETFORMDETAIL, observerAllForm.getAllForm().getResponse().get(position).getFormId(), observerAllForm.getAllForm().getResponse().get(position).getFormType());

        call.enqueue(new Callback<FormOneProp>() {
            @Override
            public void onResponse(Call<FormOneProp> call, retrofit2.Response<FormOneProp> response) {
                try {

                    String json = new Gson().toJson(response.body().getResponse());
                    Log.d("aaaa", " ccccccc        " + json);

                    if (observerAllForm.getAllForm().getResponse().get(position).getFormType().equals("1")) {
                        observerFormOne.setFormone(response.body());

                        Intent i = new Intent(MainActivity.this, Form1Activity.class);
                        i.putExtra(AppConstant.IS_NEW, false);
                        i.putExtra("id", observerAllForm.getAllForm().getResponse().get(position).getFormId());
                        startActivity(i);

                    } else if (observerAllForm.getAllForm().getResponse().get(position).getFormType().equals("4")) {
                        observerFormOne.setFormone(response.body());

                        Intent i = new Intent(MainActivity.this, VehicleForm4.class);
                        i.putExtra(AppConstant.IS_NEW, false);
                        i.putExtra("id", observerAllForm.getAllForm().getResponse().get(position).getFormId());
                        startActivity(i);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                pDialog.dismiss();


            }

            @Override
            public void onFailure(Call<FormOneProp> call, Throwable t) {
                pDialog.dismiss();
                t.printStackTrace();

            }
        });
    }*/


    @Override
    public void update(Observable observable, Object data) {

        if (observable instanceof Observer_AllForm) {
            adp.notifyDataSetChanged();
            if(observerAllForm.getAllForm().getResponse().getNotes().size()>0){
                txt_notes.setText(observerAllForm.getAllForm().getResponse().getNotes().get(0).getNotes());
            }
        }

    }
}
