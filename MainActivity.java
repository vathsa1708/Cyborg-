package com.example.cyborg;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DisplayContext;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView,quesNo;
    TextView questionTextView;
    Button answer_a, answer_b, answer_c, answer_d;
    Button submitBtn;
    TextView resultOfPlayer,res1,res2,res3;
    TextView questionNo,quesAns,ans;
    Button prevBtn,nextBtn;
    ImageView result;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int QuesNo;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mainAct(View view)
    {
        setContentView(R.layout.main_activity);
    }

    public void Scams(View view)
    {
        setContentView((R.layout.phising_scams));
    }
    public void Exit1(View view)
    {
        setContentView(R.layout.main_activity);
    }
    public void PscamsSol(View view)
    {
        setContentView(R.layout.phising_scams_sol);
    }
    public void Exit1_1(View view)
    {
        setContentView((R.layout.phising_scams));
    }

    public void Fraud(View view)
    {
        setContentView(R.layout.internet_fraud);
    }
    public void InFraudSol(View view)
    {
        setContentView(R.layout.internet_fraud_sol);
    }
    public void Exit2(View view)
    {
        setContentView(R.layout.main_activity);
    }
    public void Exit2_1(View view)
    {
        setContentView(R.layout.internet_fraud);
    }

    public void OnlineInt(View view) { setContentView(R.layout.online_int); }
    public void OnlineIntSol(View view) { setContentView(R.layout.onlineintsol); }
    public void Exit3(View view) { setContentView(R.layout.main_activity);}
    public void Exit3_1(View view)
    {
        setContentView(R.layout.online_int);
    }

    public void Identity(View view){ setContentView(R.layout.identity);}
    public void IdentitySol(View view){ setContentView(R.layout.identitysol);}
    public void Exit4(View view){ setContentView(R.layout.main_activity);}
    public void Exit4_1(View view){ setContentView(R.layout.identity);}

    public void OnlineHar(View view){ setContentView(R.layout.onlinehar);}
    public void OnlineHarSol(View view){ setContentView(R.layout.onlineharsol);}
    public void Exit5(View view){ setContentView(R.layout.main_activity);}
    public void Exit5_1(View view){ setContentView(R.layout.identity);}


    public void StartQuiz(View view)
    {
        setContentView(R.layout.questions);

        totalQuestionsTextView = findViewById(R.id.total_question);
        quesNo = findViewById(R.id.question_no);
        questionTextView = findViewById(R.id.question);
        answer_a = findViewById(R.id.answer_a);
        answer_b = findViewById(R.id.answer_b);
        answer_c = findViewById(R.id.answer_c);
        answer_d = findViewById(R.id.answer_d);
        submitBtn = findViewById(R.id.submit_btn);

        answer_a.setOnClickListener(this);
        answer_b.setOnClickListener(this);
        answer_c.setOnClickListener(this);
        answer_d.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions: " + totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        answer_a.setBackgroundColor(Color.WHITE);
        answer_b.setBackgroundColor(Color.WHITE);
        answer_c.setBackgroundColor(Color.WHITE);
        answer_d.setBackgroundColor(Color.WHITE);
        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else if(clickedButton.getId() == R.id.prev)
        {
            if(currentQuestionIndex==0)
            {
                ExitPage();
            }
            else
            {
                currentQuestionIndex--;
                loadAnswer();
            }
        }
        else if(clickedButton.getId() == R.id.next)
        {
            currentQuestionIndex++;
            loadAnswer();
        }
        else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GRAY);
        }
    }

    void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        int ci =currentQuestionIndex + 1;
        quesNo.setText("Question no: "+ci);
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        answer_a.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        answer_b.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        answer_c.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        answer_d.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }


    public void Answers(View view)
    {
        setContentView(R.layout.correct_answers);

        questionNo = findViewById(R.id.QuestionNo);
        quesAns = findViewById(R.id.questionans);
        ans = findViewById(R.id.choices);
        prevBtn = findViewById(R.id.prev);
        nextBtn = findViewById(R.id.next);

        prevBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        currentQuestionIndex=0;

        loadAnswer();
    }

    void loadAnswer()
    {
        if(currentQuestionIndex ==totalQuestion)
        {
            setContentView(R.layout.final_page);
            return;
        }
        questionNo.setText("Question Number: " + (currentQuestionIndex+1));
        quesAns.setText(QuestionAnswer.question[currentQuestionIndex]);
        ans.setText(QuestionAnswer.correctAnswers[currentQuestionIndex]);
    }
    void ExitPage()
    {
        setContentView(R.layout.main_activity);
    }

    void finishQuiz()
    {
        setContentView(R.layout.results);
        resultOfPlayer = findViewById(R.id.Research);

        resultOfPlayer.setText("Your Result: "+ score );

        res1 = findViewById(R.id.Res1);
        result = findViewById(R.id.resimage);

        if(score<0.6*20)
        {
            res1.setText("You're weak in knowledge about cyber knowledge. Please gain knowledge through CYBORG.");
            result.setImageResource(R.drawable.failed);
        }
        else
        {
            res1.setText("You're good in knowledge about cyber crimes.");
            result.setImageResource(R.drawable.success);
        }
    }
    
    public void exitQuiz(View view)
    {
        setContentView(R.layout.main_activity);
    }

    public void restartQuiz(View view) {
        score = 0;
        currentQuestionIndex = 0;
        StartQuiz(view);
    }

}