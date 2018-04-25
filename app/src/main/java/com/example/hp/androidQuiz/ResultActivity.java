package com.example.hp.androidQuiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.InterstitialAd;

import info.hoang8f.widget.FButton;

/**
 * Created by hp on 20-10-2016. Updated 01/2018
 */
public class ResultActivity extends Activity {
    FButton playButton, quitButton, rateButton;
    TextView textResult;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        playButton = (FButton)findViewById(R.id.playBtn);
        quitButton = (FButton)findViewById(R.id.quitBtn);
        textResult = (TextView) findViewById(R.id.textResult);

        // Ad
        MobileAds.initialize(this, "7314105676320068~6586228920");
        AdView mAdView = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Initialize the Mobile Ads SDK
        MobileAds.initialize(this, "7314105676320068~8751505064");
        // Create the InterstitialAd and set the adUnitId
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7314105676320068/8751505064");
        mInterstitialAd.loadAd(new AdRequest.Builder().build()); // add is loading


        // Pass the score from MainActivity
        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("score");

        textResult.setText(""+score+"");

        // Play again button onclick listener
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Display the interstitial ad before starting the quiz
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else { // if no ad is loaded, when the phone is offline for axample
                    Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                //Here set a flag to know that your
            }

            @Override
            public void onAdClosed() {
                // Proceed to quiz when ad is finished
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
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

    }

}
