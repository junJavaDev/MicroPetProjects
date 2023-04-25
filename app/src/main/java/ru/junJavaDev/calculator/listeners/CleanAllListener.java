package ru.junJavaDev.calculator.listeners;

import android.view.View;

import ru.junJavaDev.calculator.CalcActivity;
import ru.junJavaDev.calculator.Setting;

public class CleanAllListener extends AbstractListener{

    public CleanAllListener(CalcActivity activity) {
        super(activity);
    }

    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        activity.reset();
    }
}
