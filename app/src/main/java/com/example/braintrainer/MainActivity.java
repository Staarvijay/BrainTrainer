package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button goButton;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestion = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView quesTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    TextView instructionTextView;

    public void playAgain(View view){
        score = 0;
        numberOfQuestion = 0;
        timerTextView.setText("30s");
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));

        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Time up!");
                playAgainButton.setVisibility(View.VISIBLE);
                quesTextView.setText("final score- ");
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.INVISIBLE);

            }
        }.start();
    }
    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
//            resultTextView.setText("Correct!");
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
        }else{
//            resultTextView.setText("Wrong :(");
            Toast.makeText(this, "Wrong :(", Toast.LENGTH_SHORT).show();
        }
        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        newQuestion();
    }
    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        quesTextView.setText(Integer.toString(a) + "+" + Integer.toString(b) + "=" + "?");
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void go(View view) {
        goButton.setVisibility(View.INVISIBLE);
        instructionTextView.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quesTextView = findViewById(R.id.quesTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
        instructionTextView = findViewById(R.id.instructionTextView);
        instructionTextView.setText(Html.fromHtml("<b>"+ "INSTRUCTIONS"+ "</b>" + "<br />"+ "<br />"+ "1) There will be some mathematical problem. " + "<br />"+  "2) Most problem done correctly under 30sec increases your speed of calculation and trains your brain."+ "<br />"+ "<br />"+ "<b>"+ "So get ready to train your brain"+ "</b>" +"<br />"+ "<br />"+"<br />"+ "CLICK GO! BUTTON WHENEVER YOU READY"));

    }
}
