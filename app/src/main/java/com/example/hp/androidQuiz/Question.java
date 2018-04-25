package com.example.hp.androidQuiz;

import android.app.Activity;

/**
 * Created by hp on 20-10-2016.
 */
public class Question extends Activity {
    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;

    private String ANSWER;

    public Question() {
        ID = 0;
        QUESTION = "";
        OPTA = "";
        OPTB = "";
        OPTC = "";

        ANSWER = "";

    }

    public Question(String qUESTION, String oPTA, String oPTB, String oPTC, String aNSWER) {
        this.QUESTION = qUESTION;
        this.OPTA = oPTA;
        this.OPTB = oPTB;
        this.OPTC = oPTC;

        this.ANSWER = aNSWER;

    }

    public int getID() {
        return this.ID;
    }

    public String getQUESTION() {
        return this.QUESTION;
    }

    public String getOPTA() {
        return this.OPTA;
    }

    public String getOPTB() {
        return this.OPTB;
    }

    public String getOPTC() {
        return this.OPTC;
    }

    public String getANSWER() {
        return this.ANSWER;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public void setQUESTION(String qUESTION) {
        this.QUESTION = qUESTION;
    }

    public void setOPTA(String oPTA) {
        this.OPTA = oPTA;
    }

    public void setOPTB(String oPTB) {
        this.OPTB = oPTB;
    }

    public void setOPTC(String oPTC) {
        this.OPTC = oPTC;
    }

    public void setANSWER(String aNSWER) {
        this.ANSWER = aNSWER;
    }

}
