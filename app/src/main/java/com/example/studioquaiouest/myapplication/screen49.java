package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class screen49 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen49);
    }

    View view;
    int radioId;

    public void Verif (View view){

        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup2);
        radioId = group.getCheckedRadioButtonId();

        if (radioId == R.id.radio4){
            findViewById(R.id.txtWrong).setVisibility(View.INVISIBLE);
            RadioButton radio = (RadioButton) findViewById(radioId);
            radio.setChecked(false);
            gotoNext(view);
        } else {
            Wrong();
        }
    }

    public void Wrong(){
        findViewById(R.id.txtWrong).setVisibility(View.VISIBLE);
        RadioButton radio = (RadioButton) findViewById(radioId);
        radio.setChecked(false);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int i = 51;
        int s = 51;
        int r = 10;
        intent.putExtra("screenshot", i).putExtra("next", s).putExtra("resumenbr", r);
        startActivity(intent);
    }
}
