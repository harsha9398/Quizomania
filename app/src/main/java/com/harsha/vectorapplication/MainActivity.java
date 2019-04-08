package com.harsha.vectorapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private Button btnLogout;
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        btnLogout = (Button) findViewById(R.id.btnLogout);
        start = (Button) findViewById(R.id.start);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });


    }

    public void startQuiz(View view) {
        try {
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            Log.e("Error", e.toString());
        }
    }

    public void score(View view) {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);

    }
}
//        try {
//            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
//            startActivityForResult(intent, REQUEST_CODE_QUIZ);
//
//        } catch(Exception e) {
//            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
//            Log.e("Error", e.toString());
//        }
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == REQUEST_CODE_QUIZ){
//            if(resultCode == RESULT_OK){
//                int score = data.getIntExtra(CapitalsQuizActivity.EXTRA_SCORE, 0);
//                if(score > highscore){
//                    updateHighScore(score);
//                }
//
//            }
//        }
//    }
//    private void updateHighScore(int highscoreNew){
//        highscore = highscoreNew;
//        textViewHighScore.setText("High Score" +highscore);
//
//        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt(KEY_HIGHSCORE , highscore);
//        editor.apply();
//
//    }
//    private void loadHighScore(){
//        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        highscore = prefs.getInt(KEY_HIGHSCORE, highscore);
//        textViewHighScore.setText("High Score" +highscore);
//    }


