package com.example.hugo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.List;

public class ReadActivity extends AppCompatActivity {

    public EditText edit;
    public String siteToSearch;
    public String queryText;
    static JSONObject jObj = null;
    static String json = "";
    public final String exampleText = "";
    Button analyzeButton;
    public int selectedId1;
    public int selectedId2;
    public int selectedId3;
    public int selectedId4;
    public int selectedId5;
    public int selectedId6;
    public int selectedId7;
    public int selectedId8;
    public int selectedId9;
    public double scoreOfSite;
    public double result = 0;

    List<Question> allQuestions = Question.Companion.getQuestions();

    ListOfWebsites websites = new ListOfWebsites();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);



        // Question number one:
        edit = (EditText) findViewById (R.id.edit_text_example);
        actv(false);
        RadioGroup radioGroupOne = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroupOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = radioGroupOne.findViewById(checkedId);
                int index = radioGroupOne.indexOfChild(button);
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Vybral si si internet. Zadaj stránku", Toast.LENGTH_SHORT).show();
                        selectedId1 = 0;
                        actv(true);
                        //could be replaced by getText().toString()?
                        break;


                    case 1:
                        actv(false);
                        Toast.makeText(getApplicationContext(), "Vybral si si Facebook", Toast.LENGTH_SHORT).show();
                        selectedId1 = 1;
                        break;

                    case 2:
                        actv(false);
                        Toast.makeText(getApplicationContext(), "Vybral si si inú sociálnu sieť", Toast.LENGTH_SHORT).show();
                        selectedId1 = 2;
                        break;

                    case 3:
                        actv(false);
                        Toast.makeText(getApplicationContext(), "Vybral si si časopis", Toast.LENGTH_SHORT).show();
                        selectedId1 = 3;
                        break;

                    case 4:
                        actv(false);
                        Toast.makeText(getApplicationContext(), "Vybral si si knihu", Toast.LENGTH_SHORT).show();
                        selectedId1 = 4;
                        break;
                }
            }
        });

        // Question number two:
        RadioGroup radioGroupTwo = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroupTwo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = radioGroupTwo.findViewById(checkedId);
                int index = radioGroupTwo.indexOfChild(button);
                selectedId2 = checkedId;
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Poznáš autora", Toast.LENGTH_SHORT).show();
                        selectedId2 = 0;
                        break;

                    case 1:
                        Toast.makeText(getApplicationContext(), "Nepoznáš autora", Toast.LENGTH_SHORT).show();
                        selectedId2 = 1;
                        break;
                }
            }
        });
        // Question number three:
        RadioGroup radioGroupThree = (RadioGroup) findViewById(R.id.radioGroup3);
        radioGroupThree.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = radioGroupThree.findViewById(checkedId);
                int index = radioGroupThree.indexOfChild(button);
                selectedId3 = checkedId;
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Máš viac zdrojov", Toast.LENGTH_SHORT).show();
                        selectedId3 = 0;
                        break;

                    case 1:
                        Toast.makeText(getApplicationContext(), "Nemáš viac zdrojov", Toast.LENGTH_SHORT).show();
                        selectedId3 = 1;
                        break;
                }
            }
        });

        // Question number four:
        RadioGroup radioGroupFour = (RadioGroup) findViewById(R.id.radioGroup4);
        radioGroupFour.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = radioGroupFour.findViewById(checkedId);
                int index = radioGroupFour.indexOfChild(button);
                selectedId4 = checkedId;
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Súvisí s predajom produktov", Toast.LENGTH_SHORT).show();
                        selectedId4 = 0;
                        break;

                    case 1:
                        Toast.makeText(getApplicationContext(), "Nesúvisí s predajom produktov", Toast.LENGTH_SHORT).show();
                        selectedId4 = 1;
                        break;
                }
            }
        });

        // Question number five:
        RadioGroup radioGroupFive = (RadioGroup) findViewById(R.id.radioGroup5);
        radioGroupFive.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = radioGroupFive.findViewById(checkedId);
                int index = radioGroupFive.indexOfChild(button);
                selectedId5 = checkedId;
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Informácia v tebe vyvoláva strach", Toast.LENGTH_SHORT).show();
                        selectedId5 = 0;
                        break;

                    case 1:
                        Toast.makeText(getApplicationContext(), "Informácia v tebe nevyvoláva strach", Toast.LENGTH_SHORT).show();
                        selectedId5 = 1;
                        break;
                }
            }
        });

        // Question number six:
        RadioGroup radioGroupSix = (RadioGroup) findViewById(R.id.radioGroup6);
        radioGroupSix.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = radioGroupSix.findViewById(checkedId);
                int index = radioGroupSix.indexOfChild(button);
                selectedId6 = checkedId;
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Pre teba je to novinka", Toast.LENGTH_SHORT).show();
                        selectedId6 = 0;
                        break;

                    case 1:
                        Toast.makeText(getApplicationContext(), "Je to všeobecne známa vec", Toast.LENGTH_SHORT).show();
                        selectedId6 = 1;
                        break;
                }
            }
        });

        // Question number seven:
        RadioGroup radioGroupSeven = (RadioGroup) findViewById(R.id.radioGroup7);
        radioGroupSeven.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = radioGroupSeven.findViewById(checkedId);
                int index = radioGroupSeven.indexOfChild(button);
                selectedId7 = checkedId;
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Wau, to je bomba", Toast.LENGTH_SHORT).show();
                        selectedId7 = 0;
                        break;

                    case 1:
                        Toast.makeText(getApplicationContext(), "Nič extra", Toast.LENGTH_SHORT).show();
                        selectedId7 = 1;
                        break;
                }
            }
        });

        // Question number eight:
        RadioGroup radioGroupEight = (RadioGroup) findViewById(R.id.radioGroup8);
        radioGroupEight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = radioGroupEight.findViewById(checkedId);
                int index = radioGroupEight.indexOfChild(button);
                selectedId8 = checkedId;
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Existuje viacero zdrojov, ktoré to potvrdzujú", Toast.LENGTH_SHORT).show();
                        selectedId8 = 0;
                        break;

                    case 1:
                        Toast.makeText(getApplicationContext(), "Nie je to možné overiť", Toast.LENGTH_SHORT).show();
                        selectedId8 = 1;
                        break;
                }
            }
        });

        // Question number nine:
        RadioGroup radioGroupNine = (RadioGroup) findViewById(R.id.radioGroup9);
        radioGroupNine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = radioGroupNine.findViewById(checkedId);
                int index = radioGroupNine.indexOfChild(button);
                selectedId9 = checkedId;
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Je to citlivá téma, ktorá rozdeľuje spoločnosť", Toast.LENGTH_SHORT).show();
                        selectedId9 = 0;
                        break;

                    case 1:
                        Toast.makeText(getApplicationContext(), "Nie je vôbec tak dramatické", Toast.LENGTH_SHORT).show();
                        selectedId9 = 1;
                        break;
                }
            }
        });

        // Question number ten - key words:
        EditText keyWordsText = findViewById(R.id.et_keyWords);
        keyWordsText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                queryText = s.toString();
            }
        });

        // Button to analyze inputs:
        analyzeButton = findViewById(R.id.btn_finishReadQuery);
        analyzeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReadActivity.this, "Analyzujem...", Toast.LENGTH_LONG).show();
                doBackgroundAnalysis();
            }
        });
    }

    // method to make background analysis
    private void doBackgroundAnalysis() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //do your work here
                analyzeInputs();

    //  code to process scraping
//                JSONObject keyWordsResult = searchKeyWords();

//                try {
//                    keyWordsResult.getString("source");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        analyzeButton.setText("Analyzujem");

                        // exampleText = getSiteString("");
                    }
                });

                //do your work after the job is done
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ReadActivity.this, "Analýza bola úspešne ukončená", Toast.LENGTH_SHORT).show();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(ReadActivity.this, ResultActivityRead.class);
                                i.putExtra("Final Score", result);
                                startActivity(i);
                                analyzeButton.setText("Zisti výsledky");
                            }
                        }, 5000);
                    }
                });
            }
        }).start();
    }

//    Method to return string from particular site
//    private String getSiteString(String site) {
//        String stream = "";
//        try {
//            URL url = new URL((site);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line);
//                    stringBuilder.append("\n");
//                }
//                stream = stringBuilder.toString();
//                urlConnection.disconnect();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return stream;
//    }

    // Method to activate EditText in first question:
    private void actv(final boolean active) {
        edit.setEnabled(active);
        if (active) {
            edit.requestFocus();
        }
    }

//    Method to analyze results of the inputs
    private void analyzeInputs() {
//        8 points for Yes/No questions (Questions 2-9)
        if(selectedId2 == 1) {
            result++;
        }

        if(selectedId3 == 1) {
            result++;
        }

        if(selectedId4 == 0) {
            result++;
        }

        if(selectedId5 == 0) {
            result++;
        }

        if(selectedId6 == 0) {
            result++;
        }

        if(selectedId7 == 0) {
            result++;
        }

        if(selectedId8 == 1) {
            result++;
        }

        if(selectedId9 == 0) {
            result++;
        }

//        10 points for question 1
        if(selectedId1 == 0) {
            siteToSearch = edit.getText().toString();
            siteToSearch.toLowerCase();
            siteToSearch.trim();
            scoreOfSite = websites.getWebsiteScore(siteToSearch);
            result = result + scoreOfSite;
        }

        if(selectedId1 == 1) {
          result = result + 7;
        }

        if(selectedId1 == 2) {
            result = result + 5;
        }

        if(selectedId1 == 3) {
            result = result + 3;
        }

        if(selectedId1 == 4 ) {
            result = result + 1;
        }

    }

//  Method to scrape the internet and return sites based on input
//    public JSONObject searchKeyWords() {
//        try {
//            String url = "https://api.serpdog.io/search?api_key=62e660878ba326a38ae69a4a&q= " + queryText + "&num=100&gl=sk&hl=sk&device=mobile";
//            URL urlForGetRequest = new URL(url);
//            String readLine = null;
//            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                while ((readLine = in.readLine()) != null) {
//                    response.append(readLine);
//                }
//                in.close();
//                System.out.println(response.toString());
//                json = response.toString();
//            } else {
//                throw new Exception("Error in API Call");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        try {
//            jObj = new JSONObject(json);
//        } catch (JSONException e) {
//            Log.e("JSON Parser", "Error parsing data " + e.toString());
//        }
//
//        // return JSON String
//        return jObj;
//    }

}

