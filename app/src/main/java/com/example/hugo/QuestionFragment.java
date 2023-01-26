package com.example.hugo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    private static final String ARG_QUESTION = "question";
    private static final String ARG_SELECTED_OPTION = "selected_option";
    private Question question;
    private Option selectedOption;
    public String siteToEvaluate = "";


    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(Question question, Option selectedOption) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_QUESTION, question);
        args.putParcelable(ARG_SELECTED_OPTION, selectedOption);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.question = getArguments().getParcelable(ARG_QUESTION);
            this.selectedOption = getArguments().getParcelable(ARG_SELECTED_OPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView questionText = view.findViewById(R.id.txtQuestion);
        questionText.setText(question.getText());
        ImageView image = (ImageView) view.findViewById(R.id.question_img);
        image.setElevation(80);
        image.setImageResource(question.getImage());

        RadioGroup radioGroup = view.findViewById(R.id.radioGroupQuestion);

        for (Option option : question.getOptions()) {

            RadioButton btn = new RadioButton(getContext());
            btn.setText(option.getText());
            btn.setTextColor(getResources().getColor(R.color.dark));
            btn.setTextAppearance(getContext(),R.style.styleFontA);
//            btn.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL); - redundant as previous is working
            btn.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.pink_700)));
            btn.setHighlightColor(getResources().getColor(R.color.pink_700));
            radioGroup.addView(btn);

            if (option.equals(selectedOption)) {
                btn.setChecked(true);
            }

            btn.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (getActivity() instanceof QuestionActivity) {
                        ((QuestionActivity) getActivity()).questionAnswered(question, option);
                        if (option.getPoints() == 2) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Zadaj stranku:");
                            // Set up the input
                            final EditText input = new EditText(getContext());
                            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
                            builder.setView(input);
                            // Set up the buttons
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    siteToEvaluate = input.getText().toString();
                                    Toast.makeText(getContext(), siteToEvaluate, Toast.LENGTH_SHORT).show();
//                                    Intent i = new Intent(getActivity(), QuestionActivity.class);
//                                    i.putExtra("SITE", siteToEvaluate);
//                                    startActivity(i);
                                    ((QuestionActivity) requireActivity()).siteToEvaluate = siteToEvaluate;


                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
//                            builder.show();
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            //For positive button:
                            Button button1 = alertDialog.findViewById(android.R.id.button1);
                            button1.setTextSize(25);
                            //For negative button:
                            Button button2 = alertDialog.findViewById(android.R.id.button2);
                            button2.setTextSize(25);

                        }
                    }
                }
            });
        }
    }
}