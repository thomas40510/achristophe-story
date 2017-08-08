package com.example.studioquaiouest.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

import io.fabric.sdk.android.Fabric;
import io.smooch.ui.ConversationActivity;

public class credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

    }

    /**
     * Actions for toolbar menu
     */
    @Override
    //load menu file//
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_credits, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //set on-click actions//
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.reset:
                resetChoose();
                return true;
            case R.id.achieve:
                Intent achieve = new Intent(this, achieveChoose.class);
                startActivity(achieve);
                return true;
            case R.id.changelog:

                final Uri changelogUri = Uri.parse("https://drive.google.com/open?id=18xJ-AUdhIm4W48Nupz0IxD8Pah3bsnHX4iLpNDaIsnc");
                Intent openUpdate = new Intent(Intent.ACTION_VIEW, changelogUri);
                startActivity(openUpdate);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void resetChoose(){
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



    public void goBack (View view){
        finish();
    }
}
