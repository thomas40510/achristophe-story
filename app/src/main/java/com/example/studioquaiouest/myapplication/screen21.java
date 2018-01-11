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

public class screen21 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen21);

        SharedPreferences prefs = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE);
        int savedDay = prefs.getInt("savedDay", 0);

        if (savedDay < 2){
            SharedPreferences.Editor editor = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
            editor.putInt("savedDay", 7);
            editor.commit();

            Toast.makeText(this, "Progression Sauvegardée !", Toast.LENGTH_SHORT).show();
        }
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

    public void Verif (View view){
        EditText text = (EditText)findViewById(R.id.editText);
        float moyenne = Float.parseFloat(text.getText().toString());

        if (moyenne==22f){
            SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
            achieveprefs.isUnlocked[1][3] = prefs.getBoolean("achieveSave13", false);

            if (!achieveprefs.isUnlocked[1][3]){
                SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                editor.putBoolean("achieveSave13", true);
                editor.commit();
                Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
            }
        }

        if (moyenne == 8.18f){
            findViewById(R.id.imgWrong).setVisibility(View.INVISIBLE);
            gotoNext(view);
        } else {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
            findViewById(R.id.imgWrong).setVisibility(View.VISIBLE);
        }
        hideEmpty();
    }

    public void hideEmpty (){
        ((EditText)findViewById(R.id.editText)).setText("");
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int r = 1;
        int i = 6;
        int s = 23;
        intent.putExtra("resumenbr", r)
                .putExtra("screenshot", i)
                .putExtra("next", s);
        startActivity(intent);
    }
}
