package com.harsha.quizapplication;

import android.provider.BaseColumns;

public final class QuizContract {
    private QuizContract(){}
    public static class QuizTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_question";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NO = "answer_no";
    }
}
