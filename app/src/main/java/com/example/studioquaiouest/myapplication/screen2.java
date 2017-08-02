package com.example.studioquaiouest.myapplication;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import io.smooch.core.Smooch;
import io.smooch.ui.ConversationActivity;


public class screen2 extends AppCompatActivity {
    public int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            i = extras.getInt("screenshot");
        }
        String uri = "@drawable/screenshot00"+i;
        int ImageRessource = getResources().getIdentifier(uri, null, getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.imageView4);
        Drawable res = getResources().getDrawable(ImageRessource);
        imageView.setImageDrawable(res);


    }

    /**
     * Actions for toolbar menu
     */
    @Override
    //load menu file//
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_intro, menu);//your file name
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onMenuOpened (int featureID, Menu menu){
        int unreadCount = Smooch.getConversation().getUnreadCount();
        Toast.makeText(this, ""+unreadCount+" messages non lus", Toast.LENGTH_SHORT).show();
        return super.onMenuOpened(featureID, menu);
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

    public void gotoScreen3 (View view) {
        int s = 2*i+1;
        String className = "com.example.studioquaiouest.myapplication.screen"+s;
        Class<?> c = null;
            try {
               c = Class.forName(className);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
    /**public void gotoScreen3 (View view) {

        String vidLink = "@drawable/screenshot00"+i;
        int ImageRessource = getResources().getIdentifier(vidLink, null, getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.imageView4);
        Drawable res = getResources().getDrawable(ImageRessource);
        imageView.setImageDrawable(res);
        i++;
    }*/
}
