package com.example.hugo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class QuestionsActivity extends AppCompatActivity {
    HashMap<Question, Option> answers = new HashMap<>();

    Button backButton;
    Button nextButton;

    int currentQuestionIndex = 0;

    ListOfWebsites listOfWebsites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions2);

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

                doBackgroundAnalysis("ukrajina,nacisti");
                //                Intent i = new Intent(this, ResultActivityRead.class);
//                i.putExtra(ResultActivityRead.SCORE, 0.0);
//                startActivity(i);
            }
        } else {
            Toast.makeText(this, "Prosim odpovedaj", Toast.LENGTH_SHORT).show();
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

            showCurrentQuestion();
        }
        renderButtons();

    }

    public void sayHello() {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

    public void questionAnswered(Question question, Option option) {
        answers.put(question, option);
        printPoints();
    }

    public void printPoints() {
        for (Map.Entry<Question, Option> questionOptionEntry : answers.entrySet()) {
            Toast.makeText(this, "Question: " + questionOptionEntry.getKey().getText() + " Points: " + questionOptionEntry.getValue().getPoints(), Toast.LENGTH_SHORT).show();
        }
    }

    /* full url mapped to domain, e.g.:
     *   https://www.google.com/asodjaosjd -> google.com
     *  */
    public void onAnalysisFinished(Map<String, String> potentialLinks) {
        double websiteScore = 0.0;

        for (Map.Entry<String, String> entry : potentialLinks.entrySet()) {
            String domain = entry.getValue();

            String tldDomain;
            String[] parts = domain.split("\\.");
            if (parts.length > 1) {
                tldDomain = parts[parts.length - 2] + "." + parts[parts.length - 1];
            } else {
                tldDomain = domain;
            }

            websiteScore += listOfWebsites.getWebsiteScore(tldDomain);
        }

        Toast.makeText(this, "Score: " + websiteScore, Toast.LENGTH_SHORT).show();
    }


    private void doBackgroundAnalysis(String query) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String google = "https://www.google.com/search?q=";
                String charset = "UTF-8";
                String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36"; // Change this to your company's name and bot homepage!

                Elements links = null;
                Map<String, String> potentialLinks = new HashMap<>();

                try {
                    links = Jsoup.connect(google + URLEncoder.encode(query, charset)).userAgent(userAgent).get().select("*");

                    for (Element link : links) {
                        String title = link.text();
                        String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
                        try {
                            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (!url.startsWith("http")) {
                            continue; // Ads/news/etc.
                        }

                        try {
                            URI uri = new URI(url);

                            String host = uri.getHost();
                            String domainName = host.startsWith("www.") ? host.substring(4) : host;
                            potentialLinks.put(url, domainName);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onAnalysisFinished(potentialLinks);
                    }
                });


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        analyzeButton.setText("Analyzujem");
//
//                        // exampleText = getSiteString("");
//                    }
//                });
//
//                //do your work after the job is done
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(ReadActivity.this, "Analýza bola úspešne ukončená", Toast.LENGTH_SHORT).show();
//                        final Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Intent i = new Intent(ReadActivity.this, ResultActivityRead.class);
//                                i.putExtra(ResultActivityRead.SCORE, result);
//                                startActivity(i);
//                                analyzeButton.setText("Zisti výsledky");
//                            }
//                        }, 5000);
//                    }
//                });
            }
        }).start();
    }

}