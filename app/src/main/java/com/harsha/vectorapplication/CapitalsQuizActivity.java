package com.harsha.vectorapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CapitalsQuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "millisLeft";
    private static final String KEY_ANSWERED = "answered";
    private static final String KEY_QUESTION_LIST= "questionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioGroup rbGroup;
    private Button buttonConfirmNext;

    private ColorStateList textColourDefaultRb;
    private ColorStateList textColourDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    public static int score;
    private int tscore = 0;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitals_quiz);

        try {
            textViewQuestion = (TextView) findViewById(R.id.Question);
            textViewCountDown = (TextView) findViewById(R.id.CountDown);
            textViewQuestionCount = (TextView) findViewById(R.id.QuestionCount);
            textViewScore = (TextView) findViewById(R.id.score);
            rbGroup = (RadioGroup) findViewById(R.id.rbGroup);
            rb1 = (RadioButton) findViewById(R.id.rb1);
            rb2 = (RadioButton) findViewById(R.id.rb2);
            rb3 = (RadioButton) findViewById(R.id.rb3);
            buttonConfirmNext = (Button) findViewById(R.id.buttonConfirmNext);
            textColourDefaultRb = rb1.getTextColors();
            textColourDefaultCd = textViewCountDown.getTextColors();

            if(savedInstanceState == null) {
                CapitalsQuizDbHelper dbHelper = new CapitalsQuizDbHelper(this);
                questionList = dbHelper.getAllQuestions();
                questionCountTotal = questionList.size();
                Collections.shuffle(questionList);

                showNextQuestion();
            }else{
                questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
//                if(questionList ==  null){
//                    finish();
//                }
                questionCountTotal = questionList.size();
                questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
                currentQuestion = questionList.get(questionCounter-1);
                score = savedInstanceState.getInt(KEY_SCORE);
                timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
                answered = savedInstanceState.getBoolean(KEY_ANSWERED);

                if(!answered){
                    startCountDown();
                }else{
                    updateCountDownText();
                    showSolution();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            Log.e("Error", e.toString());
        }

    }
    public void confirmNext(View view){
        if (!answered) {
            if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                checkAnswer();
            } else {
                Toast.makeText(CapitalsQuizActivity.this, "Please Select An Option", Toast.LENGTH_SHORT).show();
            }
        } else {
            showNextQuestion();
        }

    }


    private void showNextQuestion(){
        try {
            rb1.setTextColor(textColourDefaultRb);
            rb2.setTextColor(textColourDefaultRb);
            rb3.setTextColor(textColourDefaultRb);
            rbGroup.clearCheck();

            if (questionCounter < questionCountTotal) {
                currentQuestion = questionList.get(questionCounter);
                textViewQuestion.setText(currentQuestion.getQuestion());
                rb1.setText(currentQuestion.getOption1());
                rb2.setText(currentQuestion.getOption2());
                rb3.setText(currentQuestion.getOption3());

                questionCounter++;
                textViewQuestionCount.setText("Question: " + questionCounter + " / " + questionCountTotal);
                answered = false;
                buttonConfirmNext.setText("Confirm");
                timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                startCountDown();
            } else {
                finishQuiz();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            Log.e("Error", e.toString());
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis/1000)/60;
        int seconds = (int) (timeLeftInMillis/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);

        if(timeLeftInMillis < 10000){
            textViewCountDown.setTextColor(Color.RED);
        }
        else{
            textViewCountDown.setTextColor(textColourDefaultCd);
        }
    }


    private void checkAnswer(){
        answered = true;
        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected)+1;

        if(answerNr == currentQuestion.getAnswerNo()){
            score++;
            textViewScore.setText("Score:"+score);
        }
        showSolution();
    }

    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNo()){
            case 1: rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option1 is correct");
                break;
            case 2: rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option2 is correct");
                break;
            case 3: rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option 3 is correct");
                break;
            default: textViewQuestion.setText("Wrong");

        }
        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }
    public void finishQuiz(){
       /* Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);*/
        StaticScore.capitalScore = score  ;
        score = 0;
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null)
            countDownTimer.cancel();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}




