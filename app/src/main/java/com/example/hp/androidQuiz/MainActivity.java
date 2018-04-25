package com.example.hp.androidQuiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.IDNA;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {

    List<Question> quesList;
    int score = 0;
    int qid = 0;
    int timeValue = 10;
    int i=0;
    Question currentQ;
    TextView txtQuestion, timeText, scored, resultText;
    FButton button1, button2, button3;
    CountDownTimer countDownTimer;
    QuizHelper theQuizHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Initializing variables
        button1 = (FButton) findViewById(R.id.button1);
        button2 = (FButton) findViewById(R.id.button2);
        button3 = (FButton) findViewById(R.id.button3);
      //  resultText = (TextView) findViewById(R.id.resultText);
        scored = (TextView) findViewById(R.id.score);
        timeText = (TextView) findViewById(R.id.timers);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        //Setting fonts for textviews and buttons

        //ads
        MobileAds.initialize(this, "7314105676320068~8606468832");
        AdView mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //Our database helper class
        theQuizHelper = new QuizHelper(this);
        //Make db writable
        theQuizHelper.getWritableDatabase();

        //It will check if the ques,options are already added in table or not
        //If they are not added then the getAllOfTheQuestions() will return a list of size zero
        if (theQuizHelper.getAllQuestions().size() == 0) {
            //If not added then add the ques,options in table
            theQuizHelper.allQuestion();
        }

        //This will return us a list of data type Question
        quesList = theQuizHelper.getAllQuestions(); // this will fetch all questions

        //Now we gonna shuffle the elements of the list so that we will get questions randomly
        Collections.shuffle(quesList);

        currentQ = quesList.get(qid); // the current question

        //countDownTimer
        countDownTimer = new CountDownTimer(12000, 1000) {
            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to timeText
                timeText.setText(String.valueOf(timeValue));

                //With each iteration decrement the time by 1 sec
                timeValue -= 1;

                //This means the user is out of time so onFinished will called after this iteration
                if (timeValue == -1) {

                    //Since user is out of time setText as time up
                 //   resultText.setText(getString(R.string.timeup));

                    //Since user is out of time he won't be able to click any buttons
                    //therefore we will disable all four options buttons using this method
                    disableButton();
                }
            }

            //Now user is out of time
            public void onFinish() {
                //We will navigate him to the time up activity using below method
                timeUp();
            }
        }.start();

        //This method will set the que and four options
        updateQuesandAns();

    }

    public void updateQuesandAns() {

        //This method will setText for que and options
        txtQuestion.setText(currentQ.getQUESTION());
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());
        button3.setText(currentQ.getOPTC());

        timeValue = 10;

        //reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();

        //set the value of coin text
        scored.setText(""+String.valueOf(score)+"/10");

        //increment number of questions
        i++;
    }


    //Onclick listener for first button
    public void button1(View view) {
        //compare the option with the ans if yes then make button color green
        if (currentQ.getOPTA().equals(currentQ.getANSWER())) {
            button1.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));

            //Check if user has not exceeds the que limit
            if (qid < quesList.size() - 1 && i<=9) {

                //Now disable all the option button since user ans is correct so
                //user won't be able to press another option button after pressing one button
                disableButton();
                //When the dialog is shown to the user, the timer will be paused in the background
                onPause();
                //set the value of coin text
                scored.setText(""+String.valueOf(score)+"/10");
                //Now since user has ans correct increment the coinvalue
                score++;

                //Show the dialog that ans is correct
                // correctDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //it will increment the question number
                        qid++;
                        //get the que and 4 option and store in the currentQuestion
                        currentQ = quesList.get(qid);
                        //Now this method will set the new que and 4 options
                        updateQuesandAns();
                        //reset the color of buttons back to white
                        resetColor();
                        //Enable button - remember we had disable them when user ans was correct in there particular button methods
                        enableButton();
                    }
                }, 1000);
            }

            //If user has exceeds the que limit just navigate him to results activity
            else if(qid < quesList.size() - 1 && i==10){
                disableButton();
                onPause();
                scored.setText(""+String.valueOf(score)+"/10");
                score++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //go to Results Activity
                        showResult();
                    }
                }, 1200);

            }

        }
        //User ans is wrong then Show the dialog if the answer is wrong
        else {
            if(i<=9){
                // make button color red
                button1.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                // make correct button to green
                if (button2.getText().equals(currentQ.getANSWER())) {
                    button2.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                } else if (button3.getText().equals(currentQ.getANSWER())) {
                    button3.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                }
                disableButton();
                onPause();
                // If user has exceeds the que limit just navigate him to results activity
                // wrongDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //it will increment the question number
                        qid++;
                        //get the que and 4 option and store in the currentQuestion
                        currentQ = quesList.get(qid);
                        //Now this method will set the new que and 4 options
                        updateQuesandAns();
                        //reset the color of buttons back to white
                        resetColor();
                        //Enable button - remember we had disable them when user ans was correct in there particular button methods
                        enableButton();
                    }
                }, 1800);
            }
             else if(i==10){
                button1.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                if (button2.getText().equals(currentQ.getANSWER())) {
                    button2.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                } else if (button3.getText().equals(currentQ.getANSWER())) {
                    button3.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                }
                disableButton();
                onPause();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        showResult();
                    }
                }, 1200);
            }

        }
    }

    //Onclick listener for sec button
    public void button2(View view) {
        if (currentQ.getOPTB().equals(currentQ.getANSWER())) {
            button2.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));

            if (qid < quesList.size() - 1 && i<=9) {

                disableButton();
                onPause();
                //set the value of coin text
                scored.setText(""+String.valueOf(score)+"/10");
                //Now since user has ans correct increment the coinvalue
                score++;

                //correctDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //it will increment the question number
                        qid++;
                        //get the que and 4 option and store in the currentQuestion
                        currentQ = quesList.get(qid);
                        //Now this method will set the new que and 4 options
                        updateQuesandAns();
                        //reset the color of buttons back to white
                        resetColor();
                        //Enable button - remember we had disable them when user ans was correct in there particular button methods
                        enableButton();
                    }
                }, 1000);
            }

            else if(qid < quesList.size() - 1 && i==10){
                disableButton();
                onPause();
                scored.setText(""+String.valueOf(score)+"/10");
                score++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //go to Results Activity
                        showResult();
                    }
                }, 1200);
            }
        } else {

            if(i<=9) {
                button2.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                // make correct button to green
                if (button3.getText().equals(currentQ.getANSWER())) {
                    button3.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                } else if (button1.getText().equals(currentQ.getANSWER())) {
                    button1.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                }
                disableButton();
                onPause();
                // If user has exceeds the que limit just navigate him to results activity
                // wrongDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //it will increment the question number
                        qid++;
                        //get the que and 4 option and store in the currentQuestion
                        currentQ = quesList.get(qid);
                        //Now this method will set the new que and 4 options
                        updateQuesandAns();
                        //reset the color of buttons back to white
                        resetColor();
                        //Enable button - remember we had disable them when user ans was correct in there particular button methods
                        enableButton();
                    }
                }, 1800);
            }
            else if(i==10){
                button2.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.redish));
                // make correct button to green
                if (button3.getText().equals(currentQ.getANSWER())) {
                    button3.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.androidgreen));
                } else if (button1.getText().equals(currentQ.getANSWER())) {
                    button1.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.androidgreen));
                }
                disableButton();
                onPause();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        showResult();
                    }
                }, 1200);
            }
          //  wrongDialog();

        }
    }

    //Onclick listener for third button
    public void button3(View view) {
        if (currentQ.getOPTC().equals(currentQ.getANSWER())) {
            button3.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));

            if (qid < quesList.size() - 1 && i<=9) {
                disableButton();
                onPause();
                //set the value of coin text
                scored.setText(""+String.valueOf(score)+"/10");
                //Now since user has ans correct increment the coinvalue
                score++;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //it will increment the question number
                        qid++;
                        //get the que and 4 option and store in the currentQuestion
                        currentQ = quesList.get(qid);
                        //Now this method will set the new que and 4 options
                        updateQuesandAns();
                        //reset the color of buttons back to white
                        resetColor();
                        //Enable button - remember we had disable them when user ans was correct in there particular button methods
                        enableButton();
                    }
                }, 1000);

              //  correctDialog();
            }
            else if(qid < quesList.size() - 1 && i==10){
                disableButton();
                onPause();
                scored.setText(""+String.valueOf(score)+"/10");
                score++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //go to Results Activity
                        showResult();
                    }
                }, 1200);

            }
        } else {

            if(i<=9) {
                button3.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                //  wrongDialog();
                // make correct button to green
                if (button1.getText().equals(currentQ.getANSWER())) {
                    button1.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                } else if (button2.getText().equals(currentQ.getANSWER())) {
                    button2.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                }
                disableButton();
                onPause();
                // If user has exceeds the que limit just navigate him to results activity
                // wrongDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //it will increment the question number
                        qid++;
                        //get the que and 4 option and store in the currentQuestion
                        currentQ = quesList.get(qid);
                        //Now this method will set the new que and 4 options
                        updateQuesandAns();
                        //reset the color of buttons back to white
                        resetColor();
                        //Enable button - remember we had disable them when user ans was correct in there particular button methods
                        enableButton();
                    }
                }, 1800);
            }
                else if(i==10) {
                button3.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                //  wrongDialog();
                // make correct button to green
                if (button1.getText().equals(currentQ.getANSWER())) {
                    button1.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                } else if (button2.getText().equals(currentQ.getANSWER())) {
                    button2.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
                }
                disableButton();
                onPause();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        showResult();
                    }
                }, 1200);
                }
        }
    }

    //This method will navigate from current activity to results activity
    public void showResult() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

    //This method is called when time is up
    //this method will navigate user to the activity Time_Up
    public void timeUp() {
        Intent intent = new Intent(this, Time_Up.class);
        startActivity(intent);
        finish();
    }

    //If user press home button and come in the game from memory then this
    //method will continue the timer from the previous time it left
    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    //When activity is destroyed then this will cancel the timer
    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    //This will pause the time
    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    //On BackPressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SplashScreen.class);
        startActivity(intent);
        finish();
    }


    //This method will make button color white again since our one button color was turned green
    public void resetColor() {
        button1.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.blueocean));
        button2.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.blueocean));
        button3.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.blueocean));
    }

    //This method will disable all the option button
    public void disableButton() {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
    }

    //This method will all enable the option buttons
    public void enableButton() {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }
}