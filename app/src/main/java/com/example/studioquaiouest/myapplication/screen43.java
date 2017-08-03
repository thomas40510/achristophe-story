package com.example.studioquaiouest.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class screen43 extends AppCompatActivity {


    public static int MOVE_LEFT = 0;
    public static int MOVE_UP = 1;
    public static int MOVE_DOWN = 2;
    public static int MOVE_RIGHT = 3;

    private static String ICICLE_KEY = "snake-view";

    private SnakeView mSnakeView;

    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_screen43);
        setContentView(R.layout.snake_layout);

        mSnakeView = (SnakeView) findViewById(R.id.snake);
        mSnakeView.setDependentViews((TextView) findViewById(R.id.text),
                findViewById(R.id.arrowContainer), findViewById(R.id.background));

        mSnakeView.setMode(SnakeView.READY);

        mSnakeView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mSnakeView.getGameState() == SnakeView.RUNNING) {
                    // Normalize x,y between 0 and 1
                    float x = event.getX() / v.getWidth();
                    float y = event.getY() / v.getHeight();

                    // Direction will be [0,1,2,3] depending on quadrant
                    int direction = 0;
                    direction = (x > y) ? 1 : 0;
                    direction |= (x > 1 - y) ? 2 : 0;

                    // Direction is same as the quadrant which was clicked
                    mSnakeView.moveSnake(direction);

                } else {
                    // If the game is not running then on touching any part of the screen
                    // we start the game by sending MOVE_UP signal to SnakeView
                    mSnakeView.moveSnake(MOVE_UP);
                }
                return false;
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause the game along with the activity
        mSnakeView.setMode(SnakeView.PAUSE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Store the game state
        outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
    }

    /**
     * Handles key events in the game. Update the direction our snake is traveling based on the
     * DPAD.
     *
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                mSnakeView.moveSnake(MOVE_UP);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                mSnakeView.moveSnake(MOVE_RIGHT);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                mSnakeView.moveSnake(MOVE_DOWN);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                mSnakeView.moveSnake(MOVE_LEFT);
                break;
        }

        return super.onKeyDown(keyCode, msg);
    }





    public void gotoNext (View view){

        Intent intent = new Intent(this, screenshots.class);
        int i = 44;
        int s = 45;
        intent.putExtra("screenshot", i).putExtra("next", s);
        startActivity(intent);
    }
}
