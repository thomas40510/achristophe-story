package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

public class screen29 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen29);
    }

    /**
     * Actions for toolbar menu
     */
    @Override
    //load menu file//
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic, menu); //your file name
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

    public View view;
    public String number;

    public void Verif (View view){

        EditText phoneNbr = (EditText)findViewById(R.id.txtNumber);
        number = phoneNbr.getText().toString().replaceAll("\\s", "");


        if (number.equals("0768270098")){

            SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
            achieveprefs.isUnlocked[1][6] = prefs.getBoolean("achieveSave16", false);

            if (!achieveprefs.isUnlocked[1][6]){
                SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                editor.putBoolean("achieveSave16", true);
                editor.commit();
                Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
            }

            hideEmpty(phoneNbr);
        }

        if (number.equals("0621041300")){
            findViewById(R.id.imgWrong).setVisibility(View.INVISIBLE);
            hideEmpty(phoneNbr);
            gotoNext(view);
        }

        else {
            findViewById(R.id.imgWrong).setVisibility(View.VISIBLE);
            hideEmpty(phoneNbr);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
        }
    }
    public void hideEmpty (EditText phoneNbr){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        phoneNbr.setText("");
    }
    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int i = 14;
        int s = 31;
        int r = 3;
        intent.putExtra("screenshot", i)
                .putExtra("next", s)
                .putExtra("resumenbr", r);
        startActivity(intent);

    }
}
