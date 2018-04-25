package com.example.hp.androidQuiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;


import info.hoang8f.widget.FButton;

public class SplashScreen extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    FButton start;
    ImageView logo;
    LinearLayout title,button;
    Animation uptodown,downtoup;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        title = (LinearLayout) findViewById(R.id.AppName);
        button = (LinearLayout) findViewById(R.id.StartQuiz);

        start =(FButton)findViewById(R.id.playGame);
        logo =(ImageView) findViewById(R.id.logo);

        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
       /* Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/scratchdroid.ttf");
        start.setTypeface(typeface);
        appname.setTypeface(typeface); */

        // Animation baby
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        title.setAnimation(uptodown);
        button.setAnimation(downtoup);

        //PlayGame button - it will take you to the MainGameActivity
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}