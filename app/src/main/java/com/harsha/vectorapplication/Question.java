package com.harsha.vectorapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private String Question;
    private String Option1;
    private String Option2;
    private String Option3;
    private int AnswerNo;

    public Question(){}

    public Question(String question, String option1, String option2, String option3, int answerNo) {
        Question = question;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        AnswerNo = answerNo;
    }

    protected Question(Parcel in) {
        Question = in.readString();
        Option1 = in.readString();
        Option2 = in.readString();
        Option3 = in.readString();
        AnswerNo = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Question);
        dest.writeString(Option1);
        dest.writeString(Option2);
        dest.writeString(Option3);
        dest.writeInt(AnswerNo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public int getAnswerNo() {
        return AnswerNo;
    }

    public void setAnswerNo(int answerNo) {
        AnswerNo = answerNo;
    }
}


