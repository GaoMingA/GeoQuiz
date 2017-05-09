package com.gaoming.example.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.gaoming.example.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "com.gaoming.example.geoquiz.answer_shown";
    private static final String KEY_INDEX = "index";
    private boolean mAnswerIsTrue;
    private boolean mIsShowAnswer = false;
    private TextView mAnswerTextView;
    private TextView mApiTextView;
    private Button mShowAnswer;

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mApiTextView = (TextView) findViewById(R.id.apiLevelTextView);
        mApiTextView.append(" "+Build.VERSION.SDK_INT);
//        mApiTextView.setText("API LEVEL: " + Build.VERSION.SDK_INT);

        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsShowAnswer = true;
                showAnswer();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = mShowAnswer.getWidth() / 2;
                    int cy = mShowAnswer.getHeight() / 2;
                    float radius = mShowAnswer.getWidth();
                    Animator anim = ViewAnimationUtils
                            .createCircularReveal(mShowAnswer, cx, cy, 0, radius);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mShowAnswer.setVisibility(View.VISIBLE);
                        }
                    });
                    anim.start();
                }else{
                    mShowAnswer.setVisibility(View.VISIBLE);
                }
            }
        });
        if (savedInstanceState != null){
            mIsShowAnswer = savedInstanceState.getBoolean(KEY_INDEX);
            showAnswer();
        }
    }

    private void showAnswer(){
        if (mIsShowAnswer){
            if (mAnswerIsTrue){
                mAnswerTextView.setText(R.string.true_button);
            }else {
                mAnswerTextView.setText(R.string.false_button);
            }
            setAnswerShownResult(mIsShowAnswer);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_INDEX,mIsShowAnswer);
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
}
