package com.example.studioquaiouest.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class achieveD5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve_d5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUnlock(view);

    }

    View view;

    public void setUnlock(View view){

        for(int j = 0 ; j<4 ; j++){
            String achieveID = "achieve2"+j;
            int resID = getResources().getIdentifier(achieveID, "id", getPackageName());


            if (achieveprefs.isUnlocked[2][j]) {
                findViewById(resID).setAlpha(1);
            }

        }

    }

    public void close (View view){
        finish();
    }
}
