package com.example.hugo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        return inflater.inflate(R.layout.fragment_question2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView questionText = view.findViewById(R.id.txtQuestion);
        questionText.setText(question.getText());

        RadioGroup radioGroup = view.findViewById(R.id.radioGroupQuestion);

        for (Option option : question.getOptions()) {

            RadioButton btn = new RadioButton(getContext());
            btn.setText(option.getText());
            radioGroup.addView(btn);

            if (option.equals(selectedOption)) {
                btn.setChecked(true);
            }

            btn.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (getActivity() instanceof QuestionsActivity) {
                        ((QuestionsActivity) getActivity()).questionAnswered(question, option);
                    }
                }
            });
        }
    }
}