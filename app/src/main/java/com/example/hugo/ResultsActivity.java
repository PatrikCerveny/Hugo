package com.example.hugo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    static final String EXPLAINSCORE = "EXPLAINSCORE";
    int explainScore = 0;
    int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            explainScore = extras.getInt(EXPLAINSCORE);
        }

        index = getCurrentResultIndex();
        if (savedInstanceState == null) {
            showCurrentResult();
        }
    }

    private void showCurrentResult() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view2,
                        ResultsFragment.newInstance(
                                Result.Companion.getResults().get(index)
                                ))
                .commit();
    }

    private int getCurrentResultIndex() {
        if(explainScore <= 100 && explainScore >= 85)
            return 0;
        else if(explainScore <= 84 && explainScore >= 60)
            return  1;
        else if(explainScore <= 59 && explainScore >= 35)
            return 2;
        else
            return 3;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter_out);
    }
}
