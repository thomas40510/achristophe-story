package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

public class Password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

    }

    /**
     * Actions for toolbar menu
     */
    @Override
    //load menu file//
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_intro, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //set on-click actions//
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.open_credits:
                Intent credits = new Intent(this, credits.class);
                startActivity(credits);
                return true;
            case R.id.open_achievements:
                Intent achieve = new Intent(this, achieveChoose.class);
                startActivity(achieve);
                return true;
            case R.id.open_Smooch:
                ConversationActivity.show(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public int choice;
    final CharSequence[] items = {"Je ne veux plus qu'on me demande"};

    public void gotoScreen2 (View view){
        EditText pswd = (EditText) findViewById(R.id.pswd);
        String code = pswd.getText().toString();


        if (code.equals("2242")){
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            SharedPreferences prefs = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE);

            if (prefs.getInt("savedDay", 0) == 0) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Avant de commencer...")
                        .setMessage("Souhaitez-vous voir l'introduction ?")
                        /**.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked){
                        choice = 1;
                        }
                        }
                        })
                         */
                        .setPositiveButton("Oui !", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), day1.class);
                                startActivity(intent);
                                Intent intent2 = new Intent(getApplicationContext(), intro.class);
                                startActivity(intent2);
                            }
                        })
                        .setNegativeButton("Nope !", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), day1.class);
                                startActivity(intent);
                            }
                        });
                dialog.show();
            } else {
                Intent next = new Intent(this, day1.class);
                startActivity(next);
            }
            pswd.setText("");
        }
        else if (code.equals("1234")){
            Intent stupid = new Intent(this, stupid.class);
            pswd.setText("");
            startActivity(stupid);
        }
        else {
            if (code.equals("7355608")) {
                findViewById(R.id.imgnope).setVisibility(View.INVISIBLE);
                findViewById(R.id.imgsatan).setVisibility(View.INVISIBLE);
                findViewById(R.id.imgbomb).setVisibility(View.VISIBLE);

                SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                achieveprefs.isUnlocked[0][7] = prefs.getBoolean("achieveSave07", false);

                if (!achieveprefs.isUnlocked[0][7]){
                    SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                    editor.putBoolean("achieveSave07", true);
                    editor.commit();
                    Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
                }

            }
            if (code.equals("2201")) {

                SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                achieveprefs.isUnlocked[0][5] = prefs.getBoolean("achieveSave05", false);

                if (!achieveprefs.isUnlocked[0][5]){
                    SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                    editor.putBoolean("achieveSave05", true);
                    editor.commit();
                    Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
                }

            }
            if (code.equals("666")) {
                findViewById(R.id.imgnope).setVisibility(View.INVISIBLE);
                findViewById(R.id.imgbomb).setVisibility(View.INVISIBLE);
                findViewById(R.id.imgsatan).setVisibility(View.VISIBLE);
                TextView satantxt = (TextView) findViewById(R.id.pasbon);
                satantxt.setText("SATAN EST NOTRE DIEU A TOUS !!");
                satantxt.setVisibility(View.VISIBLE);

                SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                achieveprefs.isUnlocked[0][8] = prefs.getBoolean("achieveSave08", false);

                if (!achieveprefs.isUnlocked[0][8]){
                    SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                    editor.putBoolean("achieveSave08", true);
                    editor.commit();
                    Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
                }

            }
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            pswd.setText("");
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
            if(!code.equals("666")){
                TextView faux = (TextView) findViewById(R.id.pasbon);
                faux.setText("Entre le bon mdp, fdp !");
                faux.setVisibility(View.VISIBLE);
            }
            findViewById(R.id.pasbon).setVisibility(View.VISIBLE);
            if(!code.equals("7355608") && !code.equals("666")){
                findViewById(R.id.imgnope).setVisibility(View.VISIBLE);
                findViewById(R.id.imgbomb).setVisibility(View.INVISIBLE);
                findViewById(R.id.imgsatan).setVisibility(View.INVISIBLE);
            }
        }
    }

    public void openChangelog(View view){

        final Uri changelogUri = Uri.parse("https://docs.google.com/document/d/18xJ-AUdhIm4W48Nupz0IxD8Pah3bsnHX4iLpNDaIsnc/edit?usp=sharing");
        Intent openUpdate = new Intent(Intent.ACTION_VIEW, changelogUri);
        startActivity(openUpdate);
    }

    public void gotoLB (View view){

        Intent intent = new Intent(this, leaderboard.class);
        startActivity(intent);
    }
}

