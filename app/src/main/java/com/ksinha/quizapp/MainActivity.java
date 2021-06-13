package com.ksinha.quizapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private final int LIMIT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int qno[] = new int[LIMIT];
                int i = 0;
                boolean flag;

                while ( i<LIMIT ){
                    flag = false;
                    int x = random.nextInt(20);
                    if( i == 0 ) qno[i] = x;
                    for(int j=0; j<i; j++){
                        if(x == qno[j]){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        qno[i] = x;
                        i++;
                    }
                }
                /*
                Toast.makeText(MainActivity.this,
                        String.valueOf(Arrays.toString(qno)),
                        Toast.LENGTH_SHORT).show();
                */
                Intent intent = new Intent(MainActivity.this,QuizActivity.class);
                intent.putExtra("limit", LIMIT);
                intent.putExtra("ques", qno);
                startActivity(intent);
            }
        });
    }
}