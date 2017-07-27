package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen55 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen55);
    }

    public void isWrong(View view){
        findViewById(R.id.imgWrong).setVisibility(View.VISIBLE);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
    }

    public void isTrue (View view){
        findViewById(R.id.imgWrong).setVisibility(View.INVISIBLE);
        gotoNext(view);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 57;
        int s = 57;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
