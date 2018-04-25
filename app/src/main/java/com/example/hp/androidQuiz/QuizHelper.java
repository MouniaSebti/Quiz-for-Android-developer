package com.example.hp.androidQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuizHelper extends SQLiteOpenHelper {

    private Context context;

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "Quiz.db";
    private static final String TABLE_NAME = "Quiz";

    private static final String KEY_ID = "qid"; // id of question
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c

    private static final String CREATE_TABLE ="CREATE TABLE " + TABLE_NAME + " ( " +  KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_QUES + " VARCHAR(255), " + KEY_OPTA + " VARCHAR(255), " + KEY_OPTB + " VARCHAR(255), " + KEY_OPTC + " VARCHAR(255), " + KEY_ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public QuizHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<Question> arraylist = new ArrayList<>();

        arraylist.add(new Question("Android is initially developed by:", "Apple Inc", "Google Inc", "Nokia Inc", "Google Inc"));
        arraylist.add(new Question("The root element of AndroidManifest.xml is:", "manifest", "application", "action", "manifest"));
        arraylist.add(new Question("You can shut down an activity by calling its ___ method", "finishActivity()", "finish()", "onDestroy()", "finish()"));
        arraylist.add(new Question("What is the directory name where the XML layout files are stored", "/res/layout", "/assets", "/res/values", "/res/layout"));
        arraylist.add(new Question("Immediate base class for activity and services", "Context", "onCreate", "ApplicationContext", "Context"));
        arraylist.add(new Question("Which of the following can be used to bind data from an SQL database to a ListView in an Android application? ", "SQLiteCursor", "SimpleCursor", "SimpleCursorAdapter", "SimpleCursorAdapter"));
        arraylist.add(new Question("Specify the name of the class which is inherited to create a user interface screen", "View", "Fragment", "Activity", "Activity"));
        arraylist.add(new Question("Which attribute of the element <uses-sdk> is used to specify the minimum API Level required for the application to run?", "android:targetSdkVersion", "android:minSdkVersion", "android:maxSdkVersion", "android:minSdkVersion"));
        arraylist.add(new Question("Once installed on a device, each Android application lives in ___?", " Device memory", "External memory", "Security sandbox", "Security sandbox"));
        arraylist.add(new Question("When contentProvider would be activated?", "using Intent", "using ContentResolver", "using SQLite", "using ContentResolver"));

        arraylist.add(new Question("Which one is NOT related to fragment class?", "dialogFragment", "preferenceFragment", "cursorFragment", "cursorFragment"));
        arraylist.add(new Question("Which one is not a nickname of a version of Android?", "Cupcake", " Muffin", "Gingerbread", " Muffin"));
        arraylist.add(new Question("When developing for the Android OS, Java byte code is compiled into what?", "Dalvik byte code", "C source code", "Dalvik application code", "Dalvik byte code"));
        arraylist.add(new Question("Which of the following would you have to include in your project to use the SimpleAdapter class?", "import android.widget", "import android.database.sqlite", "import android.content", "import android.widget"));
        arraylist.add(new Question("Select depreciated components from the list below", "Horizontal Scroll View", "Absolute Layout", "Grid Layout", "Absolute Layout"));
        arraylist.add(new Question("Which of the following does not belong to transitions?", "ViewSlider", "ViewFlipper", "ViewSwitcher", "ViewSlider"));
        arraylist.add(new Question("Which one of the following is not included in API level 8 or lesser?", "Fragment", "Spinner", "Progress Bar", "Fragment"));
        arraylist.add(new Question("Action bar can be associated to", "Only fragments", "Both activities and fragments", "Only activities", "Only activities"));
        arraylist.add(new Question("Which is not included in the Android Software Development kit (SDK)", "Android OS", "Handset Emulators", "Debugger", "Android OS"));
        arraylist.add(new Question("How to get a response from an activity in Android?", "Bundle()", "startActivityToResult()", "startActivityForResult()", "startActivityForResult()"));

        arraylist.add(new Question("How many threads are there in asyncTask in android?", "Only one", "Two", "AsyncTask doesn't have tread", "Only one"));
        arraylist.add(new Question("How to pass the data between activities in Android?", "Intent", "Content Provider", "Broadcast receiver", "Intent"));
        arraylist.add(new Question("Which layer is not included in the architecture diagram of an Android Platform", "Data Interchange", "Linux Kernel", "Libraries", "Data Interchange"));
        arraylist.add(new Question("Components of the underlying OS are written in __, while user and built-in applications are built for Android in __", "java, java", "java, C/C++", "C/C++, java", "C/C++, java"));
        arraylist.add(new Question("What are return types of startActivityForResult() in android?", "RESULT_OK", "RESULT_CANCEL", "Both", "Both"));
        arraylist.add(new Question("How many orientations does android support?", "4", "2", "10", "4"));
        arraylist.add(new Question("Which permissions are required to get a location in android?", "WIFI permission", "GPRS permission", "ACCESS_FINE and ACCESS_COARSE", "ACCESS_FINE and ACCESS_COARSE"));
        arraylist.add(new Question("Is it possible to have an activity without UI to perform action/actions?", "Not possible", "Wrong question", "Yes, it's possible", "Yes, it's possible"));
        arraylist.add(new Question("Which of the following files are advised not to modify", "R file", "Build file", "Both", "Build file"));
        arraylist.add(new Question("How to get a response from an activity in Android?", "status code > 100", "status code < 100", "status >= 400", "status >= 400"));

        arraylist.add(new Question("What operating system is used as the base of the Android stack?", "Windows", "Linux", "macOS", "Linux"));
        arraylist.add(new Question("Which of these are not one of the three main components of the APK?", "Webkit", "Dalvik Executable ", "Native Libraries", " Webkit"));
        arraylist.add(new Question("Which among these are NOT a part of Android’s native libraries?", "SQLite", "Dalvik", "OpenGL", "Dalvik"));
        arraylist.add(new Question("What file is responsible for gluing everything together, explaining what the app consists of, what its main building blocks are..?", "R file", "Strings XML", "Manifest file ", "Manifest file "));
        arraylist.add(new Question("The Android Software Development Kit (SDK) is all you need to develop applications for Android", "True", "False", "I don't know", "True"));
        arraylist.add(new Question("If you want share the data across all applications, you should go for?", "Internal Storage", "Shared Preferences", "content provider", "content provider"));
        arraylist.add(new Question("How to store heavy structured data in android?", "Cursor", "Shared Preferences", "SQlite database", "SQlite database"));
        arraylist.add(new Question("What is the name of the program that converts Java byte code into Dalvik byte code?", "Dex compiler ", "Dalvik Converter", "Android Interpretive Compiler (AIC)", "Dex compiler"));
        arraylist.add(new Question("What part of the Android platform is open source?", "low-level Linux modules", "application frame work", "Both", "Both"));
        arraylist.add(new Question("Android is licensed under which open source licensing license?","Gnu’s GPL","Apache/MIT","Sourceforge","Apache/MIT"));

        arraylist.add(new Question("In Android Architecture, the top most layer is","Applications Framework","Linux Kernel","Applications","Applications"));
        arraylist.add(new Question("In Android Architecture, the layer below Application is","Linux Kernel","Applications Framework","System Libraries & Android Runtime","Applications Framework"));
        arraylist.add(new Question("In Android Architecture, the layer below Applications Framework is","System Libraries & Android Runtime","Applications"," Linux Kernel","System Libraries & Android Runtime"));
        arraylist.add(new Question("In Android Architecture, the layer below System Libraries and Android runtime is","Applications Framework","Linux Kernel","Applications","Linux Kernel"));
        arraylist.add(new Question("Following is a client-server tool in Android","Android debug bridge","Android emulator","none of these","Android debug bridge"));
        arraylist.add(new Question("What does the .apk extension stand for?","Application Package","Application Program Kit","Android Proprietary Kit","Application Package"));
        arraylist.add(new Question("Which of the  important device characteristics that you should consider as you design and develop your application?","Screen size and density","Device features","Both","Both"));
        arraylist.add(new Question("What is splash screen in android?","Initial activity of an application","Initial screen of an application","Initial method of an application","Initial screen of an application"));
        arraylist.add(new Question("Which of the following is/are are the sub classes in Android?","Action Bar Activity","Launcher Activity","Both","Both"));
        arraylist.add(new Question("Which among the following are part of the Application Framework layer of Android Architecture","Activity Manager","Window Manager","Both","Both"));

        this.addQuestion(arraylist);
    }


    private void addQuestion(ArrayList<Question> allQuestions) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Question question : allQuestions) {
                values.put(KEY_QUES, question.getQUESTION());
                values.put(KEY_ANSWER, question.getANSWER());
                values.put(KEY_OPTA, question.getOPTA());
                values.put(KEY_OPTB, question.getOPTB());
                values.put(KEY_OPTC, question.getOPTC());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    List<Question> getAllQuestions() {

        List<Question> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {KEY_ID, KEY_QUES, KEY_OPTA, KEY_OPTB, KEY_OPTC, KEY_ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            Question question = new Question();
            question.setID(cursor.getInt(0));
            question.setQUESTION(cursor.getString(1));
            question.setOPTA(cursor.getString(2));
            question.setOPTB(cursor.getString(3));
            question.setOPTC(cursor.getString(4));
            question.setANSWER(cursor.getString(5));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }

}


