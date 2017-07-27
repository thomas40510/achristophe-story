package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.smooch.core.Smooch;

public class easter_rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter_rules);
    }
    public void next (View view){
        Intent intent = new Intent(this, Password.class);
        startActivity(intent);
    }
}
