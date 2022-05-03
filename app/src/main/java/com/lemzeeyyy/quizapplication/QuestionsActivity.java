package com.lemzeeyyy.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import com.lemzeeyyy.quizapplication.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTV, qCountTV, timerTV,difficulty ;
    private Button option1, option2, option3, option4 ;
    private List<Question> questionList;
    private  int questNum;
    private CountDownTimer countDownTimer;
    private int score;
    private SoundPool soundPool;
    private int correctSound;
    private int wrongSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();
        correctSound = soundPool.load(this,R.raw.correct,1);
        wrongSound = soundPool.load(this,R.raw.defeat_two,1);
        difficulty = findViewById(R.id.showDiff);
        Intent intent = getIntent();
        difficulty.setText(intent.getStringExtra("DIFFICULTY"));
        questionTV = findViewById(R.id.questionsID);
        qCountTV = findViewById(R.id.questionNumberID);
        timerTV = findViewById(R.id.countDownTimerID);
        option1 = findViewById(R.id.opt1ID);
        option2 = findViewById(R.id.opt2ID);
        option3 = findViewById(R.id.opt3ID);
        option4 = findViewById(R.id.opt4ID);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        score = 0 ;
    }

    private void getQuestionsList(){
        questionList = new ArrayList<>();
        questionList.add(new Question("Question 1", "OptA","OptB", "OptC","OptD",2));
        questionList.add(new Question("Question 1", "OptB","OptA", "OptC","OptD",1));
        questionList.add(new Question("Question 1", "OptC","OptB", "OptA","OptD",3));
        questionList.add(new Question("Question 1", "OptD","OptB", "OptC","OptA",4));

        setQuestion();
    }
    private void setQuestion(){
        timerTV.setText(String.valueOf(10));
        questionTV.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());
        option4.setText(questionList.get(0).getOptionD());

        qCountTV.setText(String.valueOf(1)+"/"+String.valueOf(questionList.size()));

        startTimer();
        questNum = 0 ;
    }
    private void startTimer(){
        countDownTimer = new CountDownTimer(12000,1000) {
            @Override
            public void onTick(long l) {
                if(l<10000)
                    timerTV.setText(String.valueOf(l/1000));

            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };
        countDownTimer.start();
    }


    @Override
    public void onClick(View view) {
        int selectedOpt = 0 ;
        switch (view.getId()){
            case R.id.opt1ID:
                selectedOpt = 1;
                break;
            case R.id.opt2ID:
                selectedOpt = 2;
                break;
            case R.id.opt3ID:
                selectedOpt = 3 ;
                break;
            case R.id.opt4ID:
                selectedOpt = 4;
                break;
            default:
        }
        countDownTimer.cancel();

        checkAnswer(selectedOpt,view);

    }
    private void checkAnswer(int selectedOpt,View view){
        if(selectedOpt == questionList.get(questNum).getCorrectAns()){
            //right answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            soundPool.play(correctSound,1,1,0,0,1);
            score++;

        }
        else{
            //wrong answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            soundPool.play(wrongSound,1,1,0,0,1);


            switch (questionList.get(questNum).getCorrectAns()){
                case 1:
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 3:
                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 4:
                    option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        },2000);

    }
    private void changeQuestion(){
        if(questNum < questionList.size() - 1){
            questNum++;

            playAnim(questionTV, 0,0);
            playAnim(option1, 0,1);
            playAnim(option2, 0,2);
            playAnim(option3, 0,3);
            playAnim(option4, 0,4);

            qCountTV.setText(String.valueOf(questNum+1)+"/"+String.valueOf(questionList.size()));
            timerTV.setText(String.valueOf(10));
            startTimer();

        }else{
            //display score in score activity
            Intent intent = new Intent(QuestionsActivity.this, ScoreActivity.class);
            intent.putExtra("SCORE",String.valueOf(score)+"/"+String.valueOf(questionList.size()));
            startActivity(intent);
            QuestionsActivity.this.finish();
        }

    }
    private void playAnim(View view,final int value, final int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if(value == 0 ){
                            switch (viewNum){
                                case 0:
                                    ((TextView)view).setText(questionList.get(questNum).getQuestion());
                                    break;
                                case 1:
                                    ((Button)view).setText(questionList.get(questNum).getOptionA());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionList.get(questNum).getOptionB());
                                    break;
                                case 3:
                                    ((Button)view).setText(questionList.get(questNum).getOptionC());
                                    break;
                                case 4:
                                    ((Button)view).setText(questionList.get(questNum).getOptionD());
                                    break;

                            }

                            if(viewNum!=0){
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6200EE")));
                            }
                            playAnim(view,1,viewNum);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }
}