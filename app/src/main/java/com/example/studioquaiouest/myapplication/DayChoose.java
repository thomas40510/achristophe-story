package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class DayChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_choose);

        SharedPreferences prefs = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE);
        int saveDay = prefs.getInt("savedDay", 0);

        int i = 1;

        while (saveDay >= i){
            String buttonId = "buttonD"+i;
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            findViewById(resId).setEnabled(true);

            i++;
        }

    }

    public void gotoNext (View view) {

        int isChecked = 0;
        boolean hasNext = false;
        for (int i = 1; i < 7; i++) {
            String radioID = "buttonD" + i;
            int resID = getResources().getIdentifier(radioID, "id", getPackageName());
            if (((RadioButton) findViewById(resID)).isChecked()) {
                isChecked = i;
                hasNext = true;
                break;
            }
        }
        if (hasNext) {
            String className = "com.example.studioquaiouest.myapplication.day" + isChecked;
            Class<?> c = null;
            try {
                c = Class.forName(className);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Intent intent = new Intent(this, c);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Vous n'avez pas choisi !", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack (View view){
        finish();
    }

}
