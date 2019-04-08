package com.harsha.vectorapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    private  TextView textViewScience;
    private  TextView textViewSports;
    private  TextView textViewMathematics;
    private  TextView textViewComputer;
    private  TextView textViewCapital;
    private  TextView textViewCurrency;

    public static final String SCIENCE_PREFS = "science_prefs";
    public static final String KEY_SCIENCESCORE = "scienceScore";

    public static final String SPORTS_PREFS = "sports_prefs";
    public static final String KEY_SPORTSCORE = "sportScore";

    public static final String MATH_PREFS = "math_prefs";
    public static final String KEY_MATHSCORE = "mathScore";

    public static final String COMPUTER_PREFS = "computer_prefs";
    public static final String KEY_COMPUTERSCORE = "computerScore";

    public static final String CAPITAL_PREFS = "capital_prefs";
    public static final String KEY_CAPITALSCORE = "capitalScore";

    public static final String CURRENCY_PREFS = "currency_prefs";
    public static final String KEY_CURRENCYSCORE = "currencyScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textViewScience = (TextView) findViewById(R.id.Science);
        loadScienceScore();
        textViewSports = (TextView) findViewById(R.id.Sports);
        loadSportScore();
        textViewMathematics = (TextView) findViewById(R.id.Mathematics);
        loadMathScore();
        textViewComputer = (TextView) findViewById(R.id.Computer);
        loadComputerScore();
        textViewCapital = (TextView) findViewById(R.id.Capital);
        loadCapitalScore();
        textViewCurrency = (TextView) findViewById(R.id.Currency);
        loadCurrencyScore();


        textViewScience.setText(StaticScore.scienceScore + "");
        SharedPreferences prefscience = getSharedPreferences(SCIENCE_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefscience.edit();
        editor.putInt(KEY_SCIENCESCORE, StaticScore.scienceScore);
        editor.apply();

        textViewSports.setText(StaticScore.sportsScore + "");
        SharedPreferences prefsports = getSharedPreferences(SPORTS_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor1 = prefsports.edit();
        editor1.putInt(KEY_SPORTSCORE, StaticScore.sportsScore);
        editor1.apply();

        textViewMathematics.setText(StaticScore.mathScore + "");
        SharedPreferences prefmath = getSharedPreferences(MATH_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = prefmath.edit();
        editor2.putInt(KEY_MATHSCORE, StaticScore.mathScore);
        editor2.apply();

        textViewComputer.setText(StaticScore.computerScore + "");
        SharedPreferences prefcomputer = getSharedPreferences(COMPUTER_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = prefcomputer.edit();
        editor3.putInt(KEY_MATHSCORE, StaticScore.computerScore);
        editor3.apply();

        textViewCapital.setText(StaticScore.capitalScore + "");
        SharedPreferences prefcapital = getSharedPreferences(CAPITAL_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor4 = prefcapital.edit();
        editor4.putInt(KEY_CAPITALSCORE, StaticScore.capitalScore);
        editor4.apply();

        textViewCurrency.setText(StaticScore.currencyScore + "");
        SharedPreferences prefcurrency = getSharedPreferences(CURRENCY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor5 = prefcurrency.edit();
        editor5.putInt(KEY_CURRENCYSCORE, StaticScore.currencyScore);
        editor5.apply();

    }

    private void loadScienceScore(){
        SharedPreferences prefscience = getSharedPreferences(SCIENCE_PREFS, MODE_PRIVATE);
        int scienceScore = prefscience.getInt(KEY_SCIENCESCORE, StaticScore.scienceScore);
        textViewScience.setText("" +scienceScore);
    }

    private void loadSportScore(){
        SharedPreferences prefsports = getSharedPreferences(SPORTS_PREFS, MODE_PRIVATE);
        int Score = prefsports.getInt(KEY_SPORTSCORE, StaticScore.sportsScore);
        textViewSports.setText("" +Score);
    }

    private void loadMathScore(){
        SharedPreferences prefmath = getSharedPreferences(MATH_PREFS, MODE_PRIVATE);
        int Score = prefmath.getInt(KEY_MATHSCORE, StaticScore.mathScore);
        textViewMathematics.setText("" +Score);
    }

    private void loadComputerScore(){
        SharedPreferences prefcomputer = getSharedPreferences(COMPUTER_PREFS, MODE_PRIVATE);
        int Score = prefcomputer.getInt(KEY_COMPUTERSCORE, StaticScore.computerScore);
        textViewComputer.setText("" +Score);
    }

    private void loadCapitalScore(){
        SharedPreferences prefcapital = getSharedPreferences(CAPITAL_PREFS, MODE_PRIVATE);
        int Score = prefcapital.getInt(KEY_COMPUTERSCORE, StaticScore.capitalScore);
        textViewCapital.setText("" +Score);
    }
    private void loadCurrencyScore(){
        SharedPreferences prefcurrency = getSharedPreferences(CURRENCY_PREFS, MODE_PRIVATE);
        int Score = prefcurrency.getInt(KEY_CURRENCYSCORE, StaticScore.currencyScore);
        textViewCurrency.setText("" +Score);
    }


}
