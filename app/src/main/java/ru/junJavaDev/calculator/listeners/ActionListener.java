package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.listeners.EqualsListener.calculateEquals;
import static ru.junJavaDev.calculator.CalcActivity.firstArgument;
import static ru.junJavaDev.calculator.CalcActivity.getNumber;
import static ru.junJavaDev.calculator.CalcActivity.secondArgument;
import static ru.junJavaDev.calculator.CalcActivity.isCalculation;
import static ru.junJavaDev.calculator.CalcActivity.equals;
import static ru.junJavaDev.calculator.CalcActivity.action;

import android.annotation.SuppressLint;
import android.view.View;

import ru.junJavaDev.calculator.CalcActivity;
import ru.junJavaDev.calculator.R;

import ru.junJavaDev.calculator.Action;
import ru.junJavaDev.calculator.Setting;

public class ActionListener extends AbstractListener {

    public ActionListener(CalcActivity activity) {
        super(activity);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        secondArgument = getNumber();
        if (firstArgument == null || isCalculation) {
            firstArgument = getNumber();
        } else {
            equals = calculateEquals();
            activity.showResult(equals);
            firstArgument = equals;
        }
        switch (view.getId()) {
            case R.id.btPlus -> {
                action = Action.plus;
                isCalculation = true;
            }
            case R.id.btMinus -> {
                action = Action.minus;
                isCalculation = true;
            }
            case R.id.btMultiply -> {
                action = Action.multiply;
                isCalculation = true;
            }
            case R.id.btDivision -> {
                action = Action.division;
                isCalculation = true;
            }
        }
    }
}
