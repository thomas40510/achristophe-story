package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

public class screen27 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen27);
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

    public void Verif (View view){
        EditText resultTxt = (EditText) findViewById(R.id.txtResult);
        float result = Float.parseFloat(resultTxt.getText().toString());
        if (result == 22){
            Reset(resultTxt);
            findViewById(R.id.imgWrong).setVisibility(View.INVISIBLE);
            gotoNext(view);
        }

        if (result == 42){
            SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
            achieveprefs.isUnlocked[1][5] = prefs.getBoolean("achieveSave15", false);

            if (!achieveprefs.isUnlocked[1][5]){
                SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                editor.putBoolean("achieveSave15", true);
                editor.commit();
                Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
            }
            Reset(resultTxt);
        }

        else {
            Reset(resultTxt);
            findViewById(R.id.imgWrong).setVisibility(View.VISIBLE);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
        }
    }

    public void Reset(EditText resultTxt){
        resultTxt.setText("");
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 11;
        int s = 29;
        intent.putExtra("screenshot", i)
                .putExtra("next", s);
        startActivity(intent);
    }
}
