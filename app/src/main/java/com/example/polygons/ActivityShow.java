package com.example.polygons;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class ActivityShow extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout layResult, layMap;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private CardView layContainer;
    private static FragMap showMap;

    public static ArrayList<Long> milliArray;
    public static ArrayList<String> namesArray;

    public FusedLocationProviderClient getmFusedLocationClient() {
        return mFusedLocationClient;
    }

    public void setmFusedLocationClient(FusedLocationProviderClient mFusedLocationClient) {
        this.mFusedLocationClient = mFusedLocationClient;
    }

    FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);

        initView();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return true;
    }


    private void initView() {
        fragmentManager = getSupportFragmentManager();
        layMap = (LinearLayout) findViewById(R.id.layMap);
        layResult = (LinearLayout) findViewById(R.id.layResult);
        layContainer = (CardView) findViewById(R.id.layContainer);
//menu = (R.menu.activity_main_actions);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        layMap.setOnClickListener(this);
        layResult.setOnClickListener(this);

        showMap();


    }


    private void showMap() {
       if(showMap==null){
            showMap = new FragMap();}
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layContainer, showMap);
        fragmentTransaction.commit();
        layMap.setBackgroundColor(Color.parseColor("#05c2ff"));
        layResult.setBackgroundColor(Color.parseColor("#4369ca"));
    }


    private void showResult() {
        FragResult showReault = FragResult.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layContainer, showReault);
        fragmentTransaction.commit();
        layMap.setBackgroundColor(Color.parseColor("#4369ca"));
        layResult.setBackgroundColor(Color.parseColor("#05c2ff"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layResult:
                showResult();
                break;

            case R.id.layMap:
                showMap();
                break;


        }


    }
}
