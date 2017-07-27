package com.example.studioquaiouest.myapplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.google.firebase.iid.FirebaseInstanceId;

import io.smooch.ui.ConversationActivity;

public class screen17 extends AppCompatActivity {

    int f = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen17);
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

    public void confirm (View view){
        TextView lastWord = (TextView) findViewById(R.id.txtLastWord);
        Button lastButton = (Button) findViewById(R.id.blastword);
        lastButton.setVisibility(View.VISIBLE);
        lastWord.setVisibility(View.VISIBLE);
    }
    public void gotoScreen18 (View view){
        RadioButton bRight = (RadioButton) findViewById(R.id.radio_right);
        TextView Wrong = (TextView) findViewById(R.id.txtWrong);
        Button lastButton = (Button) findViewById(R.id.blastword);
        TextView lastWord = (TextView) findViewById(R.id.txtLastWord);
        if (bRight.isChecked()){
            Intent intent = new Intent(this, screen2.class);
            lastButton.setVisibility(View.INVISIBLE);
            lastWord.setVisibility(View.INVISIBLE);
            Wrong.setVisibility(View.INVISIBLE);
            bRight.setChecked(false);
            int i = 9;
            intent.putExtra("screenshot", i);
            startActivity(intent);
        }
        else {
            f = f+1;
            if (f == 3) {
                SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                achieveprefs.Achieve[2] = prefs.getString("achieve2", "");
                if (!achieveprefs.Achieve[2].equals("1")) {
                    SharedPreferences.Editor editor3 = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                    editor3.putString("achieve2", "1");
                    editor3.commit();

                    Toast.makeText(this, "Achievement Unlocked", Toast.LENGTH_SHORT).show();
                }
                Wrong.setVisibility(View.VISIBLE);
                lastButton.setVisibility(View.INVISIBLE);
                lastWord.setVisibility(View.INVISIBLE);
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(300);
            }
            else {
                Wrong.setVisibility(View.VISIBLE);
                lastButton.setVisibility(View.INVISIBLE);
                lastWord.setVisibility(View.INVISIBLE);
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(300);
            }
    }
    }}
