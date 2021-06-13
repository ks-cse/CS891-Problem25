package com.ksinha.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQno, tvQuestion;
    private RadioGroup radioGroup;
    private RadioButton op1, op2, op3, op4;
    private Button btnNext, btnSubmit;

    private QALibrary qaLibrary;
    private int i, score, limit, quesIntArray[];
    private Bundle bundle;
    //private final int LIMIT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        bundle = getIntent().getExtras();
        limit = bundle.getInt("limit");
        quesIntArray = bundle.getIntArray("ques");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        tvQno = findViewById(R.id.tv_qno);
        tvQuestion = findViewById(R.id.tv_question);
        radioGroup = findViewById(R.id.radiogroup);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);
        btnNext = findViewById(R.id.btn_next);
        btnSubmit = findViewById(R.id.btn_submit);

        qaLibrary = new QALibrary();
        i = 0;
        score = 0;
        fetchQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchAnswer();
                if(i < limit-1)
                    i++;
                fetchQuestion();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchAnswer();
                builder.setTitle("SCORE");
                builder.setMessage("You have scored: " + score + " out of " + limit);
                builder.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void fetchQuestion() {
        // When the last question is displayed,
        // SUBMIT button will be visible
        // and NEXT button will be gone
        if(i == limit-1){
            btnNext.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
        }
        radioGroup.clearCheck();

        tvQno.setText("Q"+(i+1));
        tvQuestion.setText(qaLibrary.getQuestion(quesIntArray[i]));
        op1.setText(qaLibrary.getOption(quesIntArray[i])[0]);
        op2.setText(qaLibrary.getOption(quesIntArray[i])[1]);
        op3.setText(qaLibrary.getOption(quesIntArray[i])[2]);
        op4.setText(qaLibrary.getOption(quesIntArray[i])[3]);
    }

    private void fetchAnswer(){
        int selected;

        if( op1.isChecked() )   selected = 0;
        else if( op2.isChecked() )   selected = 1;
        else if( op3.isChecked() )   selected = 2;
        else if( op4.isChecked() )   selected = 3;
        else selected = -1;

        if( qaLibrary.getAnswer(quesIntArray[i]) == selected )
            score++;
    }
}