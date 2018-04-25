package com.example.hp.androidQuiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import info.hoang8f.widget.FButton;

public class Time_Up extends AppCompatActivity {
    FButton playAgainButton, quitButton;
    TextView timeUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time__up);

        //Initialize
        playAgainButton = (FButton)findViewById(R.id.playAgainButton);
        quitButton = (FButton)findViewById(R.id.quitButton);
        timeUpText = (TextView)findViewById(R.id.timeUpText);

        //ads
        MobileAds.initialize(this, "7314105676320068~1083202037");
        AdView mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //play again button onclick listener
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Time_Up.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //Quit button - This will quit the game
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
      /*  Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        timeUpText.setTypeface(typeface);
        playAgainButton.setTypeface(typeface); */
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
