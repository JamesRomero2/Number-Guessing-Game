package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private ImageView splashImg;
    private TextView splashTxt;

    Animation animationImg, animationTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashImg = (ImageView) findViewById(R.id.imageView);
        splashTxt = (TextView) findViewById(R.id.textView);

        animationImg = (Animation) AnimationUtils.loadAnimation(this,
                R.anim.image_animation);
        animationTxt = (Animation) AnimationUtils.loadAnimation(this,
                R.anim.text_animation);
        splashImg.setAnimation(animationImg);
        splashTxt.setAnimation(animationTxt);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }
            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this,
                        MainActivity.class));
                finish();
            }
        }.start();
    }
}