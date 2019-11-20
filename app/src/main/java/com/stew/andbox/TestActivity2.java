package com.stew.andbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity2 extends AppCompatActivity {

    private static final String TAG = TestActivity2.class.getSimpleName();
    @BindView(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate: ");
        } else {
            Log.d(TAG, "onCreate: ----" + savedInstanceState.toString());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);

        textView.setText("screenOrientation : portrait ---> landscape\n\n" +
                "onPause\n" +
                "onStop\n" +
                "onSaveInstanceState---Bundle[{}]\n" +
                "onDestroy\n" +
                "onCreate---Bundle[{}]\n" +
                "onStart\n" +
                "onRestoreInstanceState---Bundle[{}]\n" +
                "onResume\n\n"+
                "系统只有在Activity异常终止的时候才会调用onSaveInstanceState、onRestoreInstanceState来存储和恢复数据，其他情况下不会触发这个过程");
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
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: " + outState.toString());
        super.onSaveInstanceState(outState);

        outState.putString("test", "123");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: " + savedInstanceState.toString());
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "test: " + savedInstanceState.getString("test"));
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
