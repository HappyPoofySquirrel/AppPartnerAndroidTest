package com.apppartner.androidprogrammertest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimationActivity extends BaseActivity implements View.OnTouchListener {

    TextView animatePrompt;
    TextView animateBonus;
    ImageView icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        animatePrompt = (TextView) findViewById(R.id.animateBonus);
        animateBonus = (TextView) findViewById(R.id.animatePrompt);
        icon = (ImageView) findViewById(R.id.icon);

        icon.setOnTouchListener(this);

        String promptFontPath = "fonts/Jelloween - Machinato ExtraLight.ttf";
        String bonusFontPath = "fonts/Jelloween - Machinato SemiBold.ttf";
        Typeface promptTf = Typeface.createFromAsset(this.getAssets(), promptFontPath);
        Typeface bonusTf = Typeface.createFromAsset(this.getAssets(), bonusFontPath);
        animatePrompt.setTypeface(bonusTf);
        animateBonus.setTypeface(promptTf);

    }

    int counter = 0;
    int displayCounter = 2;

    public void animateAlpha(View view) {

        if (counter < 2) {
            AlphaAnimation animation = new AlphaAnimation(1f, 0f);
            icon.setAlpha(1f);
            animation.setDuration(2000);
            animation.setRepeatCount(1);
            animation.setRepeatMode(Animation.REVERSE);
            icon.startAnimation(animation);
            Toast.makeText(this, "Press " + displayCounter + " more time(s) to play connect 3", Toast.LENGTH_SHORT).show();
            counter += 1;
            displayCounter -= 1;
        } else {
            Intent intent = new Intent(this, ConnectThreeActivity.class);
            startActivity(intent);
        }
    }

    float x, y = 0.0f;
    boolean moving = false;

    public boolean onTouch(View arg0, MotionEvent arg1) {

        switch (arg1.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (moving) {
                    x = arg1.getRawX() - icon.getWidth() / 2;
                    y = arg1.getRawY() - icon.getHeight() * 3 / 2;

                    icon.setX(x);
                    icon.setRotation(360);
                    icon.setY(y);
                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        return true;
    }


    @Override
    public void onResume() {
        super.onResume();
        counter = 0;
        displayCounter = 2;
    }

}
