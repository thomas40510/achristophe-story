package com.example.studioquaiouest.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

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
            if (isChecked == 2){
                Toast.makeText(this, "Il n'y a pas encore d'achievements ici !", Toast.LENGTH_SHORT).show();
            } else {
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
            }
        } else {
            Toast.makeText(this, "Vous n'avez pas choisi !", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * progress reset functions
     */

    public void resetChoose(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Que veux-tu reset ?")
                .setPositiveButton("La progression !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        resetProgress();

                        Toast.makeText(getApplicationContext(), "Progress reset !", Toast.LENGTH_SHORT).show();
                        gotoMain();
                    }
                })
                .setNegativeButton("Les Achievements !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){

                        resetAchieve();

                        Toast.makeText(getApplicationContext(), "Achievements reset !", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("TOUT !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        resetAchieve();
                        resetProgress();

                        Toast.makeText(getApplicationContext(), "All reset !", Toast.LENGTH_SHORT).show();
                        gotoMain();

                        if (annihilate && canAnnihilate) {
                            SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                            achieveprefs.isUnlocked[2][3] = prefs.getBoolean("achieveSave23", false);
                            if (!achieveprefs.isUnlocked[2][3]) {
                                SharedPreferences.Editor editor4 = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                                editor4.putBoolean("achieveSave23", true);
                                editor4.commit();

                                Toast.makeText(getApplicationContext(), "Achievement Unlocked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        builder.show();

    }

    boolean hasEaster;
    boolean annihilate;
    boolean canAnnihilate;

    public void resetProgress(){
        SharedPreferences.Editor editor = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
        editor.putInt("savedDay", 0);
        editor.commit();
    }

    public void resetAchieve(){
        int n = 0;
        annihilate = true;
        achieveprefs achprefs = new achieveprefs();
        achprefs.fetchUnlock(this);

        for (int r = 0 ; r < 3 ; r++){
            for (int c = 0 ; c < 10 ; c++){
                if (achieveprefs.isUnlocked[r][c]){
                    n++;
                }
            }
        }

        if (n>=20) {
            canAnnihilate = true;
        }

        if (achieveprefs.isUnlocked[1][0]){
            hasEaster = true;
        }
        if (achieveprefs.isUnlocked[2][3]){
            annihilate = false;
        }

        for(boolean[] arr : achieveprefs.isUnlocked){
            Arrays.fill(arr, false);
        }

        if (!annihilate){
            achieveprefs.isUnlocked[2][3] = true;
        }
        if (hasEaster){
            achieveprefs.isUnlocked[1][0] = true;
        }

        achprefs.writeUnlock(this);
    }

    public void gotoMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * End of progress reset functions
     */

    public void goBack (View view){
        finish();
    }
}
