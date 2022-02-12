package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.MainActivity.action;
import static ru.junJavaDev.calculator.MainActivity.getNumber;
import static ru.junJavaDev.calculator.MainActivity.isCalculation;
import static ru.junJavaDev.calculator.MainActivity.secondArgument;
import static ru.junJavaDev.calculator.MainActivity.firstArgument;
import static ru.junJavaDev.calculator.MainActivity.equals;
import static ru.junJavaDev.calculator.MainActivity.showResult;

import android.view.View;

public class EqualsListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
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
