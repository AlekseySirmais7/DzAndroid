package com.example.firstandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.firstandroid.fragments.ListFragment;
import com.example.firstandroid.fragments.OneNumberFragment;

public class MainActivity extends AppCompatActivity implements NumberViewer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
            Log.d("MY CHECK", "onCreate: NO Bundle!!!");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ListFragment listFragment = new ListFragment(); // не делаю через newInstance т.к.
        listFragment.setArguments(savedInstanceState);
        if (fragmentManager.findFragmentById(R.id.FragmentContainer) == null) {
            transaction
                    .add(R.id.FragmentContainer, listFragment)
                    //.addToBackStack(null)
                    .commitAllowingStateLoss();
        }

    }

    @Override
    public void showNum(int num) {

        OneNumberFragment oneNumberFrag = OneNumberFragment.newInstance(num,
                getResources().getString(R.string.oneNumberFragmentNumberKey));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FragmentContainer,
                        oneNumberFrag,
                        getResources().getString(R.string.tagForFindOneNumberFragment))
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }
}


