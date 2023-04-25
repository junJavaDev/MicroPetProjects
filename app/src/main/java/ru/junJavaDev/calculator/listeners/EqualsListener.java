package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.CalcActivity.action;
import static ru.junJavaDev.calculator.CalcActivity.getNumber;
import static ru.junJavaDev.calculator.CalcActivity.isCalculation;
import static ru.junJavaDev.calculator.CalcActivity.secondArgument;
import static ru.junJavaDev.calculator.CalcActivity.firstArgument;
import static ru.junJavaDev.calculator.CalcActivity.equals;

import android.view.View;

import ru.junJavaDev.calculator.CalcActivity;
import ru.junJavaDev.calculator.Setting;

public class EqualsListener extends AbstractListener {

    public EqualsListener(CalcActivity activity) {
        super(activity);
    }

    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        if (action != null) {
            if (!isCalculation) {
                secondArgument = getNumber();
                isCalculation = true;
            }
            equals = calculateEquals();
            activity.showResult(equals);
            firstArgument = equals;
        }
    }

    public static Double calculateEquals() {
        return switch (action) {
            case plus -> firstArgument + secondArgument;
            case minus -> firstArgument - secondArgument;
            case multiply -> firstArgument * secondArgument;
            case division -> firstArgument / secondArgument;
        };
    }

}
