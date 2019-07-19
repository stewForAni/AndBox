package com.stew.andbox;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomViewActivity extends AppCompatActivity {
    @BindView(R.id.show_popup_window)
    Button showPopupWindow;
    @BindView(R.id.hide_popup_window)
    Button hidePopupWindow;

    PopupWindow window;
    View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_view);
        ButterKnife.bind(this);

        rootView = LayoutInflater.from(this).inflate(R.layout.activity_bottom_view, null);

        initPopupWindow();
        showPopupWindow.setOnClickListener(v -> showPop());
        hidePopupWindow.setOnClickListener(v -> hidePopup());
    }

    private void initPopupWindow() {

        View view = LayoutInflater.from(this).inflate(R.layout.pop, null);

        window = new PopupWindow(this);
        window.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        window.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        window.setContentView(view);

        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.setOutsideTouchable(false);
        window.setAnimationStyle(R.style.pop_window_anim_style);
    }

    private void hidePopup() {
        window.dismiss();
    }

    private void showPop() {
        window.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

}
