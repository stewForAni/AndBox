package com.stew.andbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity1 extends AppCompatActivity {

    private static final String TAG = TestActivity1.class.getSimpleName();

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        ButterKnife.bind(this);
        button.setOnClickListener(v -> startActivity(new Intent(TestActivity1.this, TestActivity2.class)));
        textView.setText("onPause && onStop\n" +
                "TestActivity1 ------ onPause\n" +
                "TestActivity2 ------ onCreate\n" +
                "TestActivity2 ------ onStart\n" +
                "TestActivity2 ------ onResume\n" +
                "TestActivity1 ------ onStop");

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
