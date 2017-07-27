package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

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

    public int choice;
    final CharSequence[] items = {"Je ne veux plus qu'on me demande"};

    public void gotoScreen2 (View view){
        EditText pswd = (EditText) findViewById(R.id.pswd);
        String code = pswd.getText().toString();

        if (code.equals("111")){
            Intent intent = new Intent(this, screen33.class);
            startActivity(intent);
        }

        if (code.equals("2242")){
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Avant de commencer...")
                    .setMessage("Souhaitez-vous voir l'introduction ?")
                    /**.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
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
                achieveprefs.Achieve[7] = prefs.getString("achieve7", "");
                if (!achieveprefs.Achieve[7].equals("1")) {
                    SharedPreferences.Editor editor3 = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                    editor3.putString("achieve7", "1");
                    editor3.commit();

                    Toast.makeText(this, "Achievement Unlocked", Toast.LENGTH_SHORT).show();
                }
            }
            if (code.equals("2201")) {
                SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                achieveprefs.Achieve[5] = prefs.getString("achieve5", "");
                if (!achieveprefs.Achieve[5].equals("1")) {
                    SharedPreferences.Editor editor3 = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                    editor3.putString("achieve5", "1");
                    editor3.commit();

                    Toast.makeText(this, "Achievement Unlocked", Toast.LENGTH_SHORT).show();
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
                achieveprefs.Achieve[8] = prefs.getString("achieve8", "");
                if (!achieveprefs.Achieve[8].equals("1")) {
                    SharedPreferences.Editor editor4 = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                    editor4.putString("achieve8", "1");
                    editor4.commit();

                    Toast.makeText(this, "Achievement Unlocked !", Toast.LENGTH_SHORT).show();
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
}

