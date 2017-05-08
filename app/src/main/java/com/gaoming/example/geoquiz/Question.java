package com.gaoming.example.geoquiz;

/**
 * Created by gsoft2-3 on 17-5-4.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question (int textResId , boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
