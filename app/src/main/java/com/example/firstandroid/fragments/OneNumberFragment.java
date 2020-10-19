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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String text = getResources().getString(R.string.defaultOneNumberFragmentText);
        Bundle arguments = getArguments();

        TextView numberText = view.findViewById(R.id.oneNumber);

        if (arguments != null) {
            text = arguments.getString(getResources().getString(R.string.oneNumberFragmentNumberKey));
            numberText.setText(text);
        }

        int textColor = getResources().getColor(R.color.colorBlueForNumber);

        if (Integer.parseInt(text) % 2 == 0) {
            textColor = getResources().getColor(R.color.colorRedForNumber);
        }
        numberText.setTextColor(textColor);
    }


    public static OneNumberFragment newInstance(int param, String numberKey) {
        OneNumberFragment fragment = new OneNumberFragment();
        Bundle bundle = new Bundle();

        bundle.putString(numberKey, String.valueOf(param));

        fragment.setArguments(bundle);
        return fragment;
    }

}
