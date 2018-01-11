package com.example.studioquaiouest.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import io.smooch.ui.ConversationActivity;

public class credits extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

    }

    /**
     * Actions for toolbar menu
     */
    @Override
    //load menu file//
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_credits, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //set on-click actions//
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.openSmooch:
                ConversationActivity.show(this);
                return true;
            case R.id.achieve:
                Intent achieve = new Intent(this, achieveChoose.class);
                startActivity(achieve);
                return true;
            case R.id.changelog:

                final Uri changelogUri = Uri.parse("http://derpy.me/acs-changelog");
                Intent openUpdate = new Intent(Intent.ACTION_VIEW, changelogUri);
                startActivity(openUpdate);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }






    public void goBack (View view){
        finish();
    }
}
