package com.example.firstandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.firstandroid.fragments.ListFragment;
import com.example.firstandroid.fragments.OneNumberFragment;

public class MainActivity extends AppCompatActivity implements NumberViewer {

    private static final String TAG_FOR_SAVE_ONE_NUMBER_FRAGMENT = "tagForFindOneNumberFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ListFragment listFragment = new ListFragment();
        if (fragmentManager.findFragmentById(R.id.FragmentContainer) == null) {
            transaction
                    .add(R.id.FragmentContainer, listFragment)
                    .commitAllowingStateLoss();
        }

    }

    @Override
    public void showNum(int number) {

        OneNumberFragment oneNumberFrag = OneNumberFragment.newInstance(number);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FragmentContainer,
                        oneNumberFrag,
                        TAG_FOR_SAVE_ONE_NUMBER_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }
}


