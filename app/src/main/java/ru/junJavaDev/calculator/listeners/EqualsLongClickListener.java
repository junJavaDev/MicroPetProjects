package ru.junJavaDev.calculator.listeners;

import static android.view.Gravity.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import ru.junJavaDev.calculator.CalcActivity;
import ru.junJavaDev.calculator.R;

import ru.junJavaDev.calculator.TextActivity;

public class EqualsLongClickListener extends AbstractListener {

    public EqualsLongClickListener(CalcActivity activity) {
        super(activity);
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public boolean onLongClick(View v) {
        switch (activity.getInputView().getText().toString()) {
            case "88" -> showText(v, 25, LEFT, R.string.eighty_eight);
            case "411" -> showText(v, 30, CENTER, R.string.love_story);
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
