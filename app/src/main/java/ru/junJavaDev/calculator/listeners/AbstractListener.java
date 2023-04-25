package ru.junJavaDev.calculator.listeners;

import android.os.Vibrator;
import android.view.View;

import ru.junJavaDev.calculator.CalcActivity;

public class AbstractListener implements View.OnClickListener, View.OnLongClickListener {
    protected final CalcActivity activity;
    protected final Vibrator vibrator;

    public AbstractListener(CalcActivity activity) {
        this.activity = activity;
        this.vibrator = activity.getVibrator();
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}
