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
import android.widget.ImageView;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

public class screen5 extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);

        SharedPreferences.Editor editor = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
        editor.putString("savedClass", "com.example.studioquaiouest.myapplication.screen5");
        editor.commit();
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
                Intent achieve = new Intent(this, achievements.class);
                startActivity(achieve);
                return true;
            case R.id.open_Smooch:
                ConversationActivity.show(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void gotoScreen6 (View view){
        EditText reponse = (EditText)findViewById(R.id.nom);
        String nom = reponse.getText().toString();

    if (nom.equals("Christophe") || nom.equals("christophe") || nom.equals("Christophe ") || nom.equals("christophe ")){
        Intent intent = new Intent(this, screen2.class);
        int i = 3;
        intent.putExtra("screenshot", i);
        startActivity(intent);
    }
    else {
        if (nom.equals("1€")){
            SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
            achieveprefs.Achieve[6] = prefs.getString("achieve6", "");
            if (!achieveprefs.Achieve[6].equals("1")){
                SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                editor.putString("achieve6", "1");
                editor.commit();
                Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
            }
        }
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        reponse.setText("");
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
        final ImageView no = (ImageView) findViewById(R.id.no);
                no.setVisibility(View.VISIBLE);


        }
    }
    public void skip (View view){
        Intent skip = new Intent(this, screenshots.class);
        startActivity(skip);
    }
}
