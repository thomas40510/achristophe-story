package com.example.studioquaiouest.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

import io.smooch.ui.ConversationActivity;

import static android.text.Html.fromHtml;

public class day6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day6);

        SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
        achieveprefs.isUnlocked[2][2] = prefs.getBoolean("achieveSave22", false);

        if (!achieveprefs.isUnlocked[2][2]){
            SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
            editor.putBoolean("achieveSave22", true);
            editor.commit();
            Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();

            getName(view);

        }

        SharedPreferences prefs2 = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE);
        int savedDay = prefs2.getInt("savedDay", 0);

        int s = 6;

        if (savedDay < s){
            SharedPreferences.Editor editor2 = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
            editor2.putInt("savedDay", s);
            editor2.commit();

            Toast.makeText(this, "Progression Sauvegardée !", Toast.LENGTH_SHORT).show();
        }

    }
    View view;

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

    String userName;

    public void getName(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText name = new EditText(this);
        builder.setTitle("GG !")
                .setMessage("Merci de rentrer ton nom pour officialiser la fin de l'aventure...")
                .setView(name)
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userName = name.getText().toString();
                        showName();
                    }
                });
        builder.show();
    }

    public void showName(){
        AlertDialog.Builder showname = new AlertDialog.Builder(this);
        showname.setTitle("Merci de confirmer")
                .setMessage("Tu t'appelles donc " + userName + " c'est bien ça ?"+"\n"+"Attention, tu ne pourras plus le changer !")
                .setPositiveButton("C'est ça !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onKeyMetric();
                    }
                })
                .setNegativeButton("Pas du tout !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getName(view);
                    }
                });
        showname.show();
    }

    public void onKeyMetric() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());


        //Answers.getInstance().logCustom(new CustomEvent("End")
        Answers.getInstance().logCustom(new CustomEvent("End test") //for Dev_0408
                .putCustomAttribute("User / Date", userName+" / "+formattedDate));
    }


    public void gotoNext (View view){
        Intent intent = new Intent(this, epilogue.class);
        startActivity(intent);
    }
}
