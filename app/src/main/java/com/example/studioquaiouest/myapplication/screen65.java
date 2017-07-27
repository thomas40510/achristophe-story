package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen65 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen65);
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 62;
        int s = 666;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
