package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen25 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen25);
    }
    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int r = 2;
        int i = 10;
        int s = 27;
        intent.putExtra("resumenbr", r)
                .putExtra("screenshot", i)
                .putExtra("next", s);
        startActivity(intent);
    }
}
