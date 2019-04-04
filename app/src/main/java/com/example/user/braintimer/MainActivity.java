package com.example.user.braintimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button btn1,btn2,btn3,btn4,play_again;
    ArrayList<Integer> answers= new ArrayList<>();
    TextView timer,addition,levels,status,score_mark;
    int locationOfCorrectAnswer;
    int total;
    int getOnes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        timer=findViewById(R.id.timer);
        addition=findViewById(R.id.addition);
        levels=findViewById(R.id.levels);
        status=findViewById(R.id.status);
        play_again=findViewById(R.id.playagain);
        score_mark=findViewById(R.id.score_mark);

        generateQuestion();

        playAgainFun();

        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                play_again.setVisibility(View.INVISIBLE);
                score_mark.setVisibility(View.INVISIBLE);
                status.setText("");
                total=0;
                getOnes=0;
                levels.setText("0" +  "/" + "0");
                generateQuestion();
                playAgainFun();
            }
        });


    }

    public void playAgainFun(){

        new CountDownTimer(22000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                score_mark.setVisibility(View.VISIBLE);
                score_mark.setText("Your Score is "+ String.valueOf(getOnes + "/" +total) + " in 30Seconds  ! ");
                play_again.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void generateQuestion(){

        Random random= new Random();

        locationOfCorrectAnswer=random.nextInt(4);

        int a=random.nextInt(21);

        int b=random.nextInt(21);

        addition.setText(String.valueOf(a + " + " + b));

        answers.clear();

        for (int postion =0; postion<4; postion++){

            if (locationOfCorrectAnswer==postion){

                answers.add(a+b);

            }else{

                int locationWrongAnswer=random.nextInt(41);

                while (locationWrongAnswer == a+b){

                    locationWrongAnswer=random.nextInt(41);

                }

                answers.add(locationWrongAnswer);

            }
        }


        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));


        Log.i(TAG, "checkAnswer: " + locationOfCorrectAnswer );

    }

    public void checkAnswer(View view){
        total++;
        Log.i(TAG, "tag: " + view.getTag().toString());
        if (view.getTag().toString()
                .equals(Integer.toString(locationOfCorrectAnswer))){

            status.setVisibility(View.VISIBLE);

            status.setText("Correct");

            getOnes++;


        }else{

            status.setVisibility(View.VISIBLE);

            status.setText("Wrong");

        }

        levels.setText(String.valueOf(getOnes + "/" + total));

        generateQuestion();
    }


}
