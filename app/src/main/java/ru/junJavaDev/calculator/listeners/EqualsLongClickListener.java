package ru.junJavaDev.calculator.listeners;

import static android.view.Gravity.*;
import static ru.junJavaDev.calculator.CalcActivity.calcView;
import static ru.junJavaDev.calculator.TextActivity.textView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import com.example.calculator.R;

import ru.junJavaDev.calculator.TextActivity;

public class EqualsLongClickListener implements View.OnLongClickListener {

    @SuppressLint("RtlHardcoded")
    @Override
    public boolean onLongClick(View v) {
        switch (calcView.getText().toString()) {
            case "88" ->  showText(v, 25, LEFT, R.string.eighty_eight);
            case "511" -> showText(v, 35, CENTER, R.string.love_story);
        }
        return false;
    }

    private void showText(View v, float textSize, int gravity, int text) {
        Intent intent = new Intent(v.getContext(), TextActivity.class);
        intent.putExtra("textSize", textSize);
        intent.putExtra("gravity", gravity);
        intent.putExtra("text", text);
        v.getContext().startActivity(intent);
    }
}
