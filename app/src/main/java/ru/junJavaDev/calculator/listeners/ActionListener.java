package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.listeners.EqualsListener.calculateEquals;
import static ru.junJavaDev.calculator.MainActivity.firstArgument;
import static ru.junJavaDev.calculator.MainActivity.getNumber;
import static ru.junJavaDev.calculator.MainActivity.secondArgument;
import static ru.junJavaDev.calculator.MainActivity.isCalculation;
import static ru.junJavaDev.calculator.MainActivity.equals;
import static ru.junJavaDev.calculator.MainActivity.action;
import static ru.junJavaDev.calculator.MainActivity.showResult;

import android.annotation.SuppressLint;
import android.os.Vibrator;
import android.view.View;

import com.example.calculator.R;

import ru.junJavaDev.calculator.Action;
import ru.junJavaDev.calculator.Setting;

public class ActionListener implements View.OnClickListener {
    private final Vibrator vibrator;

    public ActionListener(Vibrator vibrator) {
        this.vibrator = vibrator;
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
            showResult(equals);
            firstArgument = equals;
        }
        switch (view.getId()) {
            case R.id.btPlus:
                action = Action.plus;
                isCalculation = true;
                break;
            case R.id.btMinus:
                action = Action.minus;
                isCalculation = true;
                break;
            case R.id.btMultiply:
                action = Action.multiply;
                isCalculation = true;
                break;
            case R.id.btDivision:
                action = Action.division;
                isCalculation = true;
                break;
        }
    }
}
