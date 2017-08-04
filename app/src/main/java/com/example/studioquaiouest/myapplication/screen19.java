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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;


public class screen19 extends AppCompatActivity {
int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen19);

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

    public void gotoNext (View view){
        CheckBox BB = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox Me = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox JC = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox Tr = (CheckBox)findViewById(R.id.checkBox4);
        CheckBox Pl = (CheckBox) findViewById(R.id.checkBox5);
        CheckBox Ld = (CheckBox) findViewById(R.id.checkBox6);
        ImageView rly = (ImageView) findViewById(R.id.imgrly);

        if (BB.isChecked() && Me.isChecked() && Pl.isChecked() && Ld.isChecked() && !JC.isChecked() && !Tr.isChecked()){
            Intent intent = new Intent(this, screenshots.class);
            int i = 1;
            int s = 21;
            intent.putExtra("screenshot", i).putExtra("next", s);
            BB.setChecked(false);
            Me.setChecked(false);
            JC.setChecked(false);
            Tr.setChecked(false);
            Pl.setChecked(false);
            Ld.setChecked(false);
            rly.setVisibility(View.INVISIBLE);
            startActivity(intent);
        }
        else {
            if(!BB.isChecked() && !Me.isChecked() && !Pl.isChecked() && !Ld.isChecked() && JC.isChecked() && Tr.isChecked()){
                i = i+1;
                if (i == 3) {

                    SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                    achieveprefs.isUnlocked[0][9] = prefs.getBoolean("achieveSave09", false);

                    if (!achieveprefs.isUnlocked[0][9]){
                        SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                        editor.putBoolean("achieveSave09", true);
                        editor.commit();
                        Toast.makeText(this, "Achievement unlocked !", Toast.LENGTH_SHORT).show();
                    }

                    }
            }
            rly.setVisibility(View.VISIBLE);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            BB.setChecked(false);
            Me.setChecked(false);
            JC.setChecked(false);
            Tr.setChecked(false);
            Pl.setChecked(false);
            Ld.setChecked(false);
            v.vibrate(300);
        }
    }
}
