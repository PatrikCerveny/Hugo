package com.example.hugo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {
    HashMap<Question, Option> answers = new HashMap<>();

    Button backButton;
    Button nextButton;

    int currentQuestionIndex = 0;
    double finalResults = 0.0;
    double websiteScore = 0.0;
    ListOfWebsites listOfWebsites;
    String siteToEvaluate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        listOfWebsites = new ListOfWebsites(getAssets());

        backButton = findViewById(R.id.back_button);
        nextButton = findViewById(R.id.next_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackButtonClicked();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextButtonClicked();
            }
        });

        if (savedInstanceState == null) {
            showCurrentQuestion();
            renderButtons();

        }
    }

    private void showCurrentQuestion() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .setCustomAnimations(
                        R.anim.animation_enter,
                        R.anim.animation_leave_out,
                        R.anim.animation_leave,
                        R.anim.animation_enter_out
                )
                .add(R.id.fragment_container_view,
                        QuestionFragment.newInstance(
                                Question.Companion.getQuestions().get(currentQuestionIndex),
                                answers.get(Question.Companion.getQuestions().get(currentQuestionIndex))
                        ))
                .commit();
    }

    private void onNextButtonClicked() {
        Question currentQuestion = Question.Companion.getQuestions().get(currentQuestionIndex);

        if (answers.containsKey(currentQuestion)) {
            currentQuestionIndex++;
            if (currentQuestionIndex < Question.Companion.getQuestions().size()) {
                showCurrentQuestion();
            } else {
                doBackgroundAnalysis();
//                getResults();
                Intent i = new Intent(this, ResultActivityRead.class);
                i.putExtra(ResultActivityRead.SCORE, finalResults);
                startActivity(i);
            }
        } else {
            Toast.makeText(this, "Prosím odpovedaj!", Toast.LENGTH_SHORT).show();
        }
        renderButtons();
    }

    private void renderButtons() {
        if (currentQuestionIndex == 0) {
            backButton.setVisibility(View.INVISIBLE);
        } else {
            backButton.setVisibility(View.VISIBLE);
        }

        if (currentQuestionIndex == Question.Companion.getQuestions().size() - 1) {
            nextButton.setText("Results");
        } else {
            nextButton.setText("Ďalej");
        }
    }

    private void onBackButtonClicked() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .setCustomAnimations(
                            R.anim.animation_leave,
                            R.anim.animation_enter_out,
                            R.anim.animation_leave_out,
                            R.anim.animation_enter
                    )
                    .add(R.id.fragment_container_view,
                            QuestionFragment.newInstance(
                                    Question.Companion.getQuestions().get(currentQuestionIndex),
                                    answers.get(Question.Companion.getQuestions().get(currentQuestionIndex))
                            ))
                    .commit();
        }
        renderButtons();
    }

    public void questionAnswered(Question question, Option option) {
        answers.put(question, option);
    }

    public void getResults() {
        for (Map.Entry<Question, Option> questionOptionEntry : answers.entrySet()) {
            finalResults = finalResults + questionOptionEntry.getValue().getPoints();
        }
    }

    /* full url mapped to domain, e.g.:
     *   https://www.google.com/asodjaosjd -> google.com
     *  */
    public double onAnalysisFinished(String domain) {
        String tldDomain;
        String[] parts = domain.split("\\.");
        if (parts.length > 1) {
            tldDomain = parts[parts.length - 2] + "." + parts[parts.length - 1];
        } else {
            tldDomain = domain;
        }
       return websiteScore = listOfWebsites.getWebsiteScore(tldDomain) - 2;
    }

    private void doBackgroundAnalysis() {
                getResults();
                if(!siteToEvaluate.equals("")) {
                    finalResults += onAnalysisFinished(siteToEvaluate);
                }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter_out);
    }
}


//        for further use ->
//        private void doBackgroundAnalysis(String query) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String google = "https://www.google.com/search?q=";
//                String charset = "UTF-8";
//                String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36"; // Change this to your company's name and bot homepage!
//
//                Elements links = null;
//                Map<String, String> potentialLinks = new HashMap<>();
//
//                try {
//                    links = Jsoup.connect(google + URLEncoder.encode(query, charset)).userAgent(userAgent).get().select("*");
//
//                    for (Element link : links) {
//                        String title = link.text();
//                        String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
//                        try {
//                            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        if (!url.startsWith("http")) {
//                            continue; // Ads/news/etc.
//                        }
//
//                        try {
//                            URI uri = new URI(url);
//
//                            String host = uri.getHost();
//                            String domainName = host.startsWith("www.") ? host.substring(4) : host;
//                            potentialLinks.put(url, domainName);
//                        } catch (URISyntaxException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        onAnalysisFinished(potentialLinks);
//                    }
//                });
//            }
//        }).start();
//    }