package com.example.studioquaiouest.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class achieveChoose extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve_choose);

        achieveprefs achPrefs = new achieveprefs();
        achPrefs.fetchUnlock(this);

        unlockCount(view);
    }

    public void unlockCount(View view){
        int i = 0;
        for (int r = 0 ; r < 3 ; r++){
            for (int c = 0 ; c < 10 ; c++){
                if (achieveprefs.isUnlocked[r][c]){
                    i++;
                }
            }
        }
        ((TextView)findViewById(R.id.countTxt)).setText(""+i);
    }

    public void gotoNext (View view) {
        int isChecked = 0;
        boolean hasNext = false;
        for (int i = 1; i < 6; i++) {
            String radioID = "radio" + i;
            int resID = getResources().getIdentifier(radioID, "id", getPackageName());
            if (((RadioButton) findViewById(resID)).isChecked()) {
                isChecked = i;
                hasNext = true;
                break;
            }
        }
        if (hasNext) {
            String className = "com.example.studioquaiouest.myapplication.achieveD" + isChecked;
            Class<?> c = null;
            try {
                c = Class.forName(className);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.e("error", "class not implemented yet");
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
