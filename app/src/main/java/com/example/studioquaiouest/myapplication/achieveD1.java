package com.example.studioquaiouest.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

public class achieveD1 extends AppCompatActivity {

    /**
     * Original code, for backup
     */

    /**protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve_d1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int i = 0;

        SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
        achieveprefs.Achieve[1] = prefs.getString("achieve1", "");
        achieveprefs.Achieve[2] = prefs.getString("achieve2", "");
        achieveprefs.Achieve[3] = prefs.getString("achieve3", "");
        achieveprefs.Achieve[4] = prefs.getString("achieve4", "");
        achieveprefs.Achieve[5] = prefs.getString("achieve5", "");
        achieveprefs.Achieve[6] = prefs.getString("achieve6", "");
        achieveprefs.Achieve[7] = prefs.getString("achieve7", "");
        achieveprefs.Achieve[8] = prefs.getString("achieve8", "");
        achieveprefs.Achieve[9] = prefs.getString("achieve9", "");
        achieveprefs.Achieve[10] = prefs.getString("achieve10", "");
        achieveprefs.Achieve[11] = prefs.getString("achieve11", "");
        achieveprefs.Achieve[12] = prefs.getString("achieve12", "");

       String[] achieves = {"0", achieveprefs.Achieve[1], achieveprefs.Achieve[2], achieveprefs.Achieve[3], achieveprefs.Achieve[4], achieveprefs.Achieve[5],
           achieveprefs.Achieve[6], achieveprefs.Achieve[7], achieveprefs.Achieve[8], achieveprefs.Achieve[9], achieveprefs.Achieve[10], achieveprefs.Achieve[11],
            achieveprefs.Achieve[12]};

        while ( i!=13){

            if (achieves[i].equals("1")){
                String achieveID = "achieve"+i;
                int resID = getResources().getIdentifier(achieveID, "id", getPackageName());
                TableRow achieverow = ((TableRow) findViewById(resID));
                achieverow.setAlpha(1);
            }
            i = i+1;
        }

    } */



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve_d1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setUnlock(view);
    }

    View view;

    public void setUnlock(View view){
        int min = 1;
        int max = 10;

        for(int i = 0 ; i<2 ; i++){
            for(int j = min ; j<max ; j++){
                String achieveID = "achieve"+i+j;
                int resID = getResources().getIdentifier(achieveID, "id", getPackageName());

                Log.e("info", "i = "+i+" j = "+j);

                if (i == 1 && j >= 3){
                    Log.e("TODO", "add missing achievements");
                    break;
                } else {

                        if (achieveprefs.isUnlocked[i][j]) {
                            findViewById(resID).setAlpha(1);
                        }

                }

            }

            min = 0;
            max = 7;
        }
    }

    public void close (View view){
        finish();
    }
}
