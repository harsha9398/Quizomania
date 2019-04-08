package com.harsha.vectorapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }
    public void science(View view){
        ScienceQuizActivity.score=0;
        Intent intent = new Intent(this, ScienceQuizActivity.class);
        startActivity(intent);
    }
    public void sports(View view){
        SportsQuizActivity.score=0;
        Intent intent = new Intent(this, SportsQuizActivity.class);
        startActivity(intent);
    }
    public void mathematics(View view){
        MathematicsQuizActivity.score=0;
        Intent intent = new Intent(this, MathematicsQuizActivity.class);
        startActivity(intent);
    }
    public void computer(View view){
        ComputerQuizActivity.score=0;
        Intent intent = new Intent(this, ComputerQuizActivity.class);
        startActivity(intent);
    }
    public void currency(View view){
        CurrencyQuizActivity.score=0;
        Intent intent = new Intent(this, CurrencyQuizActivity.class);
        startActivity(intent);
    }
    public void capitals(View view){
        CapitalsQuizActivity.score=0;
        Intent intent = new Intent(this, CapitalsQuizActivity.class);
        startActivity(intent);
    }

}
