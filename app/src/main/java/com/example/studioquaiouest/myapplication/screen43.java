package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen43 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen43);
    }
    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 44;
        int s = 45;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
