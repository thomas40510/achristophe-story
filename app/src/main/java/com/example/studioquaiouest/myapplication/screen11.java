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
import android.widget.TextView;

import org.w3c.dom.Text;

import io.smooch.ui.ConversationActivity;

public class screen11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen11);

        SharedPreferences.Editor editor = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE).edit();
        editor.putString("savedClass", "com.example.studioquaiouest.myapplication.screen11");
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

    public void gotoScreen12 (View view){
        CheckBox check1 = (CheckBox) findViewById(R.id.checkBox);
        CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox check4 = (CheckBox) findViewById(R.id.checkBox4);

        if (check1.isChecked() && check2.isChecked() && check3.isChecked() && check4.isChecked()){
            Intent intent = new Intent(this, screen2.class);
            int i = 6;
            intent.putExtra("screenshot", i);
            startActivity(intent);
        }
        else {
            TextView faux = (TextView) findViewById(R.id.faux);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
            faux.setVisibility(View.VISIBLE);

        }
    }
}
