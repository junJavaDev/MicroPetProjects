package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.MainActivity.textView;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;

import com.example.calculator.R;

import ru.junJavaDev.calculator.Buttons;

public class EqualsLongClickListener implements View.OnLongClickListener {

    private final Buttons buttons;

    public EqualsLongClickListener(Buttons buttons) {
        this.buttons = buttons;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public boolean onLongClick(View view) {
        if (textView.getText().toString().equals("88")) {
            textView.setTextSize(25);
            textView.setGravity(Gravity.LEFT);
            textView.setText(R.string.eighty_eight);
            buttons.setUnClickable();
        }
        return false;
    }
}
