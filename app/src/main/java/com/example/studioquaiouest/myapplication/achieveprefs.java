package com.example.studioquaiouest.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Administrateur on 12/02/2017.
 */

public class achieveprefs extends Activity {

    public static final String ACH_PREFS = "FinalAchieveSavesFile";
    public static String[] Achieve = {"0", "0", "0", "0", "0","0", "0", "0", "0", "0", "0", "0", "0"};

    public static boolean[][] isUnlocked = new boolean[3][10];

    public SharedPreferences prefs;

    public void fetchUnlock(Context context){
        String achieve = "achieveSave";
        prefs = context.getSharedPreferences(ACH_PREFS, MODE_PRIVATE);
        for (int r = 0 ; r < 3 ; r++){
            for (int c = 0 ; c < 10 ; c++){
                isUnlocked[r][(c)] = prefs.getBoolean(achieve +r +c, false);
            }
        }
    }

    public void writeUnlock(Context context){
        String write = "achieveSave";
        SharedPreferences.Editor editor = context.getSharedPreferences(ACH_PREFS, MODE_PRIVATE).edit();

        for (int w = 0 ; w < 3 ; w++){
            for (int x = 0 ; x < 10 ; x++){
                editor.putBoolean(write+w+x, isUnlocked[w][x]);
                //editor.putBoolean(write+w+x, true);
            }
        }
        editor.commit();
    }
}
