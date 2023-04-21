package ru.junJavaDev.calculator.listeners;

import android.os.Vibrator;
import android.view.View;

import ru.junJavaDev.calculator.MainActivity;
import ru.junJavaDev.calculator.Setting;

public class CleanAllListener implements View.OnClickListener{
    private Vibrator vibrator;
    private MainActivity mainActivity;

    public CleanAllListener(Vibrator vibrator, MainActivity mainActivity) {
        this.vibrator = vibrator;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        mainActivity.reset();
    }
}
