package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.smooch.ui.ConversationActivity;

public class screen7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen7);
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

    public void jeveux (View view){
        TextView confirm = (TextView)findViewById(R.id.confirm);
        confirm.setVisibility(View.VISIBLE);
        Button yesbutton = (Button)findViewById(R.id.yesyes);
        yesbutton.setVisibility(View.VISIBLE);
        Button yesbutton2 = (Button)findViewById(R.id.yesno);
        yesbutton2.setVisibility(View.VISIBLE);

    }
    public void jeveuxpas (View view){
        Intent end = new Intent(this, MainActivity.class);
        startActivity(end);
    }
    public void jesuissur (View view){
        Intent next = new Intent(this, screen2.class);
        int i = 4;
        next.putExtra("screenshot", i);
        startActivity(next);
    }
}
