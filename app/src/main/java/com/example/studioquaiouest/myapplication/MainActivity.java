package com.example.studioquaiouest.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.Random;

import io.smooch.ui.ConversationActivity;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    //Remote Config keys
    private static final String LOADING_PHRASE_CONFIG_KEY = "loading_phrase";
    private static final String WELCOME_MESSAGE_KEY = "welcome_message";
    private static final String UPDATE_MESSAGE = "update_message";
    private static final String UPDATE_LINK = "update_link";
    private static final String ENABLE_EASTER = "enable_easter";
    private static final String EASTER_DAY = "easter_day";

    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private TextView mWelcomeTextView;
    private TextView mUpdateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView versionname = (TextView) findViewById(R.id.versionname);
        String version = BuildConfig.VERSION_NAME;
        versionname.setText(version);

        restoreEaster();

        mWelcomeTextView = (TextView) findViewById(R.id.welcomeMsg);
        mUpdateTextView = (TextView) findViewById(R.id.update);
        TextView fetchButton = (TextView) findViewById(R.id.txtfetch);
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchWelcome();
            }
        });

        //get Remote Config instance
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        // Create Remote Config Setting to enable developer mode.
        // Fetching configs from the server is normally limited to 5 requests per hour.
        // Enabling developer mode allows many more requests to be made per hour, so developers
        // can fadeoff different config values during development.
        // [START enable_dev_mode]
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        // [END enable_dev_mode]

        //set RemoteConfig defaults
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        fetchWelcome();
        Randomize();
    }
/**
 * Actions for toolbar menu
 */
    @Override
    //load menu file//
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_intro, menu); //your file name
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

    /**
     * Fetch welcome message from server.
     */
    private void fetchWelcome() {
        mWelcomeTextView.setText(mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY));

        long cacheExpiration = 1800; // 1/2 hour in seconds.
        // If in developer mode cacheExpiration is set to 0 so each fetch will retrieve values from
        // the server.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        // [START fetch_config_with_callback]
        // cacheExpirationSeconds is set to cacheExpiration here, indicating that any previously
        // fetched and cached config would be considered expired because it would have been fetched
        // more than cacheExpiration seconds ago. Thus the next fetch would go to the server unless
        // throttling is in progress. The default expiration duration is 43200 (12 hours).
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "MotD récupéré !",
                                    Toast.LENGTH_SHORT).show();

                            // Once the config is successfully fetched it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(MainActivity.this, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        displayWelcomeMessage();
                    }
                });
        // [END fetch_config_with_callback]
    }

    /**
     * display welcome message as fetched from welcome_message.
     */
    // [START display_welcome_message]
    private void displayWelcomeMessage() {
        // [START get_config_values]
        String welcome_message = mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY);
        String updatemsg = mFirebaseRemoteConfig.getString(UPDATE_MESSAGE);
        String updatelink = mFirebaseRemoteConfig.getString(UPDATE_LINK);
        Easter.enable = mFirebaseRemoteConfig.getString(ENABLE_EASTER);
        Easter.easterday = mFirebaseRemoteConfig.getString(EASTER_DAY);
        // [END get_config_values]
        mWelcomeTextView.setText(welcome_message);
        mUpdateTextView.setText(updatemsg);
        final Uri updateuri = Uri.parse(updatelink);

        mUpdateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent openUpdate = new Intent(Intent.ACTION_VIEW, updateuri);
                startActivity(openUpdate);
            }

        });

    }
    // [END display_welcome_message]

    public void bye(View view){
        findViewById(R.id.byeimg).setVisibility(View.VISIBLE);
        findViewById(R.id.button_yes).setVisibility(View.INVISIBLE);
        findViewById(R.id.button3).setVisibility(View.INVISIBLE);
        Button adieu = (Button) findViewById(R.id.button_no);
        findViewById(R.id.textView).setVisibility(View.INVISIBLE);
        adieu.setText("CASSE TOI ALORS !!");
        adieu.setTextSize(20);

    }
    public void gotoPswd(View view) {
        if (Easter.enable.equals("true")){
            Intent easter = new Intent(this, easter_rules.class);
            startActivity(easter);
        }
        else {
            Intent intent = new Intent(this, Password.class);
            findViewById(R.id.byeimg).setVisibility(View.INVISIBLE);
            startActivity(intent);
        }
    }
    public void Randomize (){
        Random r = new Random();
        int i = r.nextInt(100);
        if (i==42){
            final ImageView square = (ImageView) findViewById(R.id.square);
            square.setVisibility(View.VISIBLE);
            square.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences prefs = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
                    achieveprefs.isUnlocked[1][1] = prefs.getBoolean("achieveSave11", false);

                    if (!achieveprefs.isUnlocked[1][1]){
                        SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
                        editor.putBoolean("achieveSave11", true);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Achievement unlocked !", Toast.LENGTH_SHORT).show();
                    }

                    square.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    public void gotoLB (View view){

        Intent intent = new Intent(this, screen43.class);
        startActivity(intent);
    }

    public void restoreEaster(){
        String saveFile = "AchievePrefsFile";
        SharedPreferences prefs = getSharedPreferences(saveFile, MODE_PRIVATE);
        achieveprefs.Achieve[10] = prefs.getString("achieve10", "0");

        SharedPreferences prefsnew = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE);
        achieveprefs.isUnlocked[1][0] = prefsnew.getBoolean("achieveSave10", false);

        if (!achieveprefs.isUnlocked[1][0] && achieveprefs.Achieve[10].equals("1")){

            SharedPreferences.Editor editor = getSharedPreferences(achieveprefs.ACH_PREFS, MODE_PRIVATE).edit();
            editor.putBoolean("achieveSave10", true);
            editor.commit();
            Toast.makeText(this, "Easter restored !", Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoLast (View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText password = new EditText(this);
        password.setInputType(2);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        builder.setView(password);
        builder.setTitle("Merci d'entrer le code")
                .setMessage("(pour des raisons de sécurité...)")
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String code = password.getText().toString();
                        if(code.equals("2242")){
                            SharedPreferences prefs = getSharedPreferences(save.MY_PREFS, MODE_PRIVATE);
                            save.saveCode = prefs.getString("savedClass", "");

                            if (!save.saveCode.contains("com.example.studioquaiouest")){
                                save.saveCode = "com.example.studioquaiouest.myapplication.screen2";
                            }

                            Intent last = new Intent();
                            last.setClassName(getApplicationContext(), save.saveCode);
                            startActivity(last);
                        }
                        else if(code.equals("1234")){
                            Intent stupid = new Intent(getApplicationContext(), stupid.class);
                            startActivity(stupid);
                        }
                        else {
                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            v.vibrate(300);
                            Toast.makeText(getApplicationContext(), "Mauvais code, réessayer.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.show();

    }


}


