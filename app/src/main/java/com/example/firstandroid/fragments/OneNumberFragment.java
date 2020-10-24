package com.example.firstandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firstandroid.R;

public class OneNumberFragment extends Fragment {

    private static final String NUMBER_KEY = "numberKey";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String text = getResources().getString(R.string.default_one_number_fragment_text);
        Bundle arguments = getArguments();

        TextView numberText = view.findViewById(R.id.oneNumber);

        if (arguments != null) {
            text = arguments.getString(NUMBER_KEY);
            numberText.setText(text);
        }

        int textColor = getResources().getColor(R.color.color_blue_for_number);

        if (Integer.parseInt(text) % 2 == 0) {
            textColor = getResources().getColor(R.color.color_red_for_number);
        }
        numberText.setTextColor(textColor);
    }


    public static OneNumberFragment newInstance(int param) {
        OneNumberFragment fragment = new OneNumberFragment();
        Bundle bundle = new Bundle();

        bundle.putString(NUMBER_KEY, String.valueOf(param));

        fragment.setArguments(bundle);
        return fragment;
    }

}
