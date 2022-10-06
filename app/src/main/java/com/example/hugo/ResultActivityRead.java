package com.example.hugo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivityRead extends AppCompatActivity {
    static final String SCORE = "SCORE";

    double finalScore = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity_read);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            finalScore = extras.getDouble(SCORE);
        }
        int percentage = (int) ((finalScore / 30) * 100);
        Toast.makeText(ResultActivityRead.this, "Skóre je: " + percentage + "%", Toast.LENGTH_SHORT).show();

        TextView result_view = (TextView) findViewById(R.id.result_percentage);
        result_view.setText(String.valueOf(percentage + "%"));

        TextView result_view_desc = (TextView) findViewById(R.id.result_percentage_text);
        result_view_desc.setText(String.valueOf("Hugo stanovil mieru dezinformatívnosti na úroveň " + percentage + "%"));

    }
}
