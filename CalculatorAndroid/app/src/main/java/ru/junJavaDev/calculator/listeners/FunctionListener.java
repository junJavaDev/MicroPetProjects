package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.CalcActivity.secondArgument;
import static ru.junJavaDev.calculator.CalcActivity.firstArgument;

import android.annotation.SuppressLint;
import android.view.View;

import ru.junJavaDev.calculator.CalcActivity;
import ru.junJavaDev.calculator.R;

import ru.junJavaDev.calculator.Setting;

public class FunctionListener extends AbstractListener {

    public FunctionListener(CalcActivity activity) {
        super(activity);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        secondArgument = activity.getNumber();
        switch (view.getId()) {
            case R.id.btFraction -> {
                secondArgument = 1 / secondArgument;
                activity.showResult(secondArgument);
            }
            case R.id.btSquare -> {
                secondArgument = Math.sqrt(secondArgument);
                activity.showResult(secondArgument);
            }
            case R.id.btPower -> {
                secondArgument *= secondArgument;
                activity.showResult(secondArgument);
            }
            case R.id.btPercent -> {
                if (firstArgument == null) {
                    secondArgument = 0D;
                } else {
                    secondArgument *= firstArgument / 100;
                }
                activity.showResult(secondArgument);
            }
        }
    }
}
