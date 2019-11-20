package com.stew.andbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

public class LMActivity  extends AppCompatActivity {

    private static final String TAG = LMActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lm);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {

        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onRestart() {

        Log.d(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onResume() {

        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {

        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {

        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
