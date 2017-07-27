package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class screen29 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen29);
    }

    public View view;
    public String number;

    public void Verif (View view){

        EditText phoneNbr = (EditText)findViewById(R.id.txtNumber);
        number = phoneNbr.getText().toString();

        if (number.equals("0621041300")){
            findViewById(R.id.imgWrong).setVisibility(View.INVISIBLE);
            hideEmpty(phoneNbr);
            gotoNext(view);
        } else{
            findViewById(R.id.imgWrong).setVisibility(View.VISIBLE);
            hideEmpty(phoneNbr);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
        }
    }
    public void hideEmpty (EditText phoneNbr){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        phoneNbr.setText("");
    }
    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int i = 14;
        int s = 31;
        int r = 3;
        intent.putExtra("screenshot", i)
                .putExtra("next", s)
                .putExtra("resumenbr", r);
        startActivity(intent);

    }
}
