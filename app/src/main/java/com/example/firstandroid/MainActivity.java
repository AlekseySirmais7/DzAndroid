package com.example.firstandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.firstandroid.Fragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    // sp для текст вью
    // dp - для вью

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentById(R.id.FragmentContainer ) == null) {
            transaction
                    .add(R.id.FragmentContainer, new ListFragment())
                    //.addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }

}
