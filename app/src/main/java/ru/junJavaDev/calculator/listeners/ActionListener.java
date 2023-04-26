package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.CalcActivity.firstArgument;
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

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        secondArgument = activity.getNumber();
        if (firstArgument == null || isCalculation) {
            firstArgument = activity.getNumber();
        } else {
            equals = activity.calculateEquals();
            activity.showResult(equals);
            firstArgument = equals;
        }
        switch (view.getId()) {
            case R.id.btPlus -> {
                action = Action.plus;
                activity.showAction();
                isCalculation = true;
            }
            case R.id.btMinus -> {
                action = Action.minus;
                activity.showAction();
                isCalculation = true;
            }
            case R.id.btMultiply -> {
                action = Action.multiply;
                activity.showAction();
                isCalculation = true;
            }
            case R.id.btDivision -> {
                action = Action.division;
                activity.showAction();
                isCalculation = true;
            }
        }
    }
}
