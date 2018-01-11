package com.example.studioquaiouest.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import io.smooch.ui.ConversationActivity;

public class screen53 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen53);
    }

    /**
     * Actions for toolbar menu
     */
    @Override
    //load menu file//
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic, menu); //your file name
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
                Intent achieve = new Intent(this, achieveChoose.class);
                startActivity(achieve);
                return true;
            case R.id.open_Smooch:
                ConversationActivity.show(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendEmail(View view){

        Log.i("Send email", "");

        String[] TO = {"achristophe.story@gmail.com"};
        Intent intent = new Intent(this, screen53.class);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Code request");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "mailcode.get()");

        try {
            startActivity(intent);
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

    EditText codeTxt;

    public void Verif (View view){
        codeTxt = (EditText) findViewById(R.id.txtCode);
        String codeSt = codeTxt.getText().toString();
        if (codeSt.isEmpty()) codeSt = "0";
        int code = Integer.parseInt(codeSt);

        if (code == 959208){
            findViewById(R.id.txtWrong).setVisibility(View.INVISIBLE);
            hideEmpty();
            gotoNext(view);
        } else {
            findViewById(R.id.txtWrong).setVisibility(View.VISIBLE);
            hideEmpty();
            Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            v.vibrate(300);
        }
    }

    public void hideEmpty (){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        codeTxt.setText("");
    }

    public void gotoNext (View view){
        Intent intent = new Intent(this, screenshots.class);
        int i = 53;
        int s = 55;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
