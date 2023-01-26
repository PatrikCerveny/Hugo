package com.example.hugo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultsFragment extends Fragment {

    private static final String ARG_RESULT = "result";
    private Result result;

    public ResultsFragment() {
    }
    public static ResultsFragment newInstance(Result result) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_RESULT, result);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.result = getArguments().getParcelable(ARG_RESULT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = (ImageView) view.findViewById(R.id.results_img);
        image.setImageResource(result.getImage());
        TextView resultTitle = view.findViewById((R.id.titleResult));
        resultTitle.setText(result.getTitle());
        TextView resultSubTitle = view.findViewById(R.id.subTitleResult);
        resultSubTitle.setText(result.getSubTitle());
        TextView resultText = view.findViewById(R.id.txtResult);
        resultText.setText(result.getExplanation());
    }
}
