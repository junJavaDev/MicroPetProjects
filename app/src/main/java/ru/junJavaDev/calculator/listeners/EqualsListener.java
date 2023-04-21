package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.MainActivity.action;
import static ru.junJavaDev.calculator.MainActivity.getNumber;
import static ru.junJavaDev.calculator.MainActivity.isCalculation;
import static ru.junJavaDev.calculator.MainActivity.secondArgument;
import static ru.junJavaDev.calculator.MainActivity.firstArgument;
import static ru.junJavaDev.calculator.MainActivity.equals;
import static ru.junJavaDev.calculator.MainActivity.showResult;

import android.os.Vibrator;
import android.view.View;

import ru.junJavaDev.calculator.Setting;

public class EqualsListener implements View.OnClickListener {
    private Vibrator vibrator;

    public EqualsListener(Vibrator vibrator) {
        this.vibrator = vibrator;
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
            showResult(equals);
            firstArgument = equals;
        }
    }

    public static Double calculateEquals() {
        double result = 0.0;
        switch (action) {
            case plus:
                result = firstArgument + secondArgument;
                break;
            case minus:
                result = firstArgument - secondArgument;
                break;
            case multiply:
                result = firstArgument * secondArgument;
                break;
            case division:
                result = firstArgument / secondArgument;
                break;
        }
        return result;
    }

}
