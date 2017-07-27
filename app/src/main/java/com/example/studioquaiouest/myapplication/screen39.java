package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class screen39 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen39);
    }

    public String answerTxt;
    View view;

    public void Verif (View view){
        EditText answer = (EditText)findViewById(R.id.editText2);
        answerTxt = answer.getText().toString();

        if (answerTxt.equals("Nietzsche")){
            hideEmpty(answer);
            findViewById(R.id.imgNiet).setVisibility(View.INVISIBLE);
            gotoNext(view);
        } else {
            findViewById(R.id.imgNiet).setVisibility(View.VISIBLE);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
            hideEmpty(answer);
        }

    }

    public void hideEmpty (EditText answer){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        answer.setText("");
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, resume.class);
        int i = 36;
        int s = 40;
        int r = 6;
        intent.putExtra("screenshot", i).putExtra("next", s).putExtra("resumenbr", r);
        startActivity(intent);
    }
}
