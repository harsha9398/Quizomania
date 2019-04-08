package com.harsha.vectorapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.harsha.quizapplication.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class CurrencyQuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CurrencyQuizomania.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public CurrencyQuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;


        final String SQL_CREATE_QUESTIONS_TABLE = "create table " +
                QuizTable.TABLE_NAME +"("+
                QuizTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                QuizTable.COLUMN_QUESTION + " TEXT, "+
                QuizTable.COLUMN_OPTION1 + " TEXT, " +
                QuizTable.COLUMN_OPTION2 + " TEXT, " +
                QuizTable.COLUMN_OPTION3 + " TEXT, " +
                QuizTable.COLUMN_ANSWER_NO + " INTEGER ) ";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ QuizTable.TABLE_NAME);
        onCreate(db);
        fillQuestionsTable();
    }
    private void fillQuestionsTable(){
        Question q1 = new Question("What is the national currency of Austria?", "Dinar","Austrian dollar","Euro", 1);
        addQuestions(q1);
        Question q2 = new Question("What is the national currency of Australia?", "Australian dollar","Australian pound","Australian renmin", 1);
        addQuestions(q2);
        Question q3 = new Question("What is the national currency of China?", "Taiwanese Dollar","Chinese yuan","Chinese Yen", 1);
        addQuestions(q3);
        Question q4 = new Question("What is the national currency of Denmark?", "Danish pound","Danish olut","Danish krone", 3);
        addQuestions(q4);
        Question q5 = new Question("What is the national currency of Malaysia?", "Malaysian rupiah","Malaysian dollar","Malaysian ringgit", 3);
        addQuestions(q5);
    }

    private void addQuestions(Question question) {

        ContentValues cv = new ContentValues();
        cv.put(QuizTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizTable.COLUMN_ANSWER_NO, question.getAnswerNo());
        db.insert(QuizTable.TABLE_NAME, null, cv);

    }

    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " +QuizTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizTable.COLUMN_OPTION3)));
                question.setAnswerNo(c.getInt(c.getColumnIndex(QuizTable.COLUMN_ANSWER_NO)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;

    }
}
