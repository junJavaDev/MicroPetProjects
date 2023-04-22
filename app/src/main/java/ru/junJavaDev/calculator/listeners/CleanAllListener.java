package ru.junJavaDev.calculator.listeners;

import android.os.Vibrator;
import android.view.View;

import ru.junJavaDev.calculator.CalcActivity;
import ru.junJavaDev.calculator.Setting;

public class CleanAllListener implements View.OnClickListener{
    private final Vibrator vibrator;
    private final CalcActivity calcActivity;

    public CleanAllListener(Vibrator vibrator, CalcActivity calcActivity) {
        this.vibrator = vibrator;
        this.calcActivity = calcActivity;
    }

    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        calcActivity.reset();
    }
}
