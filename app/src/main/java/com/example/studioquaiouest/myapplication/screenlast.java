package com.example.studioquaiouest.myapplication;


import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import io.smooch.ui.ConversationActivity;

public class screenlast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenlast);
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

    public void gotoMain (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void gettoken (View view){
        String token = FirebaseInstanceId.getInstance().getToken().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setText(token);
        Toast.makeText(screenlast.this, "FCM copied to Clipboard !",
                Toast.LENGTH_SHORT).show();

    }
    public void gotoCredits (View view){
        Intent credits = new Intent(this, credits.class);
        startActivity(credits);
    }

    public void resetit (View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Que veux-tu reset ?")
                .setPositiveButton("La progression !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences.Editor editor = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
                        editor.putString("savedClass", "com.example.studioquaiouest.myapplication.screen2");
                        editor.commit();

                        Toast.makeText(getApplicationContext(), "Progress reset !", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Les achieveChoose !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){

                        resetachieve();

                        Toast.makeText(getApplicationContext(), "Achievements reset !", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("TOUT !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        resetachieve();

                        SharedPreferences.Editor editor3 = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
                        editor3.putString("savedClass", "com.example.studioquaiouest.myapplication.screen2");
                        editor3.commit();

                        Toast.makeText(getApplicationContext(), "All reset !", Toast.LENGTH_SHORT).show();

                        SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                        achieveprefs.Achieve[3] = prefs.getString("achieve3", "");
                        if (!achieveprefs.Achieve[3].equals("1")) {
                            SharedPreferences.Editor editor4 = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                            editor4.putString("achieve3", "1");
                            editor4.commit();

                            Toast.makeText(getApplicationContext(), "Achievement Unlocked", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        builder.show();

    }
    public void resetachieve (){                //common method for achievements reset (used by both buttons)
        SharedPreferences.Editor editor2 = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
        editor2.putString("achieve1", "0");
        editor2.putString("achieve2", "0");
        editor2.putString("achieve4", "0");
        editor2.putString("achieve5", "0");
        editor2.putString("achieve6", "0");
        editor2.putString("achieve7", "0")
                .putString("achieve8", "0")
                .putString("achieve9", "0")
                .putString("achieve10", "0")
                .putString("achieve11", "0")
                .putString("achieve12", "0");
        editor2.commit();
    }


}
