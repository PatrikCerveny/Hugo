package com.example.hugo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivityRead extends AppCompatActivity {
    static final String SCORE = "SCORE";

    double finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity_read);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            finalScore = extras.getDouble(SCORE);
        }
        int percentage = (int) ((finalScore / 17) * 100);
        if(percentage > 100) percentage = 100;
        Toast.makeText(ResultActivityRead.this, "Skóre je: " + percentage + "%", Toast.LENGTH_SHORT).show();

        TextView result_view = (TextView) findViewById(R.id.result_percentage);
        result_view.setText(String.valueOf(percentage + "%"));

        TextView result_view_desc = (TextView) findViewById(R.id.result_percentage_text);
        result_view_desc.setText(String.valueOf("Hugo stanovil mieru dezinformatívnosti na úroveň " + percentage + "%"));

        Button resultsButton = findViewById(R.id.results_btn);
        int finalPercentage = percentage;
        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showResults = new Intent(ResultActivityRead.this, ResultsActivity.class);
                showResults.putExtra(ResultsActivity.EXPLAINSCORE, finalPercentage);
                startActivity(showResults);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave_out);
            }
        });

    }

    @Override
    public void onBackPressed() {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter_out);
    }
    }
