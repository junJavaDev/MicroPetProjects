package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.CalcActivity.isCalculation;
import static ru.junJavaDev.calculator.CalcActivity.calcView;

import android.annotation.SuppressLint;
import android.view.View;

import ru.junJavaDev.calculator.CalcActivity;
import ru.junJavaDev.calculator.R;

import ru.junJavaDev.calculator.Setting;

public class InputListener extends AbstractListener {
    boolean isZero;
    boolean isNegative;
    boolean isZeroNegative;
    boolean isApproximate;
    CharSequence buffer;

    public InputListener(CalcActivity activity) {
        super(activity);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        if (!isCalculation && calcView.getText().length() > 9) {
            return;
        }
        if (isCalculation) {
            calcView.setText("0");
            isCalculation = false;
        }
        isApproximate = calcView.getText().charAt(0) == '~';
        isZero = calcView.getText().charAt(0) == '0' && calcView.length() == 1;
        isNegative = calcView.getText().charAt(0) == '-';
        isZeroNegative = isNegative && calcView.getText().charAt(1) == '0' && calcView.length() == 2;


        switch (view.getId()) {

            case R.id.btZero:
                if (isZeroNegative)
                    calcView.setText("0");
                else if (!isZero)
                    calcView.append("0");
                break;

            case R.id.btOne:
                if (isZero)
                    calcView.setText("1");
                else if (isZeroNegative)
                    calcView.setText("-1");
                else
                    calcView.append("1");
                break;

            case R.id.btTwo:
                if (isZero)
                    calcView.setText("2");
                else if (isZeroNegative)
                    calcView.setText("-2");
                else
                    calcView.append("2");
                break;

            case R.id.btThree:
                if (isZero)
                    calcView.setText("3");
                else if (isZeroNegative)
                    calcView.setText("-3");
                else
                    calcView.append("3");
                break;

            case R.id.btFour:
                if (isZero)
                    calcView.setText("4");
                else if (isZeroNegative)
                    calcView.setText("-4");
                else
                    calcView.append("4");
                break;

            case R.id.btFive:
                if (isZero)
                    calcView.setText("5");
                else if (isZeroNegative)
                    calcView.setText("-5");
                else
                    calcView.append("5");
                break;

            case R.id.btSix:
                if (isZero)
                    calcView.setText("6");
                else if (isZeroNegative)
                    calcView.setText("-6");
                else
                    calcView.append("6");
                break;

            case R.id.btSeven:
                if (isZero)
                    calcView.setText("7");
                else if (isZeroNegative)
                    calcView.setText("-7");
                else
                    calcView.append("7");
                break;

            case R.id.btEight:
                if (isZero)
                    calcView.setText("8");
                else if (isZeroNegative)
                    calcView.setText("-8");
                else
                    calcView.append("8");
                break;

            case R.id.btNine:
                if (isZero)
                    calcView.setText("9");
                else if (isZeroNegative)
                    calcView.setText("-9");
                else
                    calcView.append("9");
                break;

            case R.id.btPositiveNegative:
                if (!isApproximate) {
                    if (isNegative) {
                        calcView.setText(
                                calcView.getText().subSequence(1, calcView.getText().length()));
                    } else {
                        buffer = calcView.getText();
                        calcView.setText("-");
                        calcView.append(buffer);
                    }
                } else if (calcView.getText().charAt(1) == '-') {
                    buffer = calcView.getText().subSequence(2, calcView.getText().length());
                    calcView.setText("~");
                    calcView.append(buffer);
                } else {
                    buffer = calcView.getText().subSequence(1, calcView.getText().length());
                    calcView.setText("~-");
                    calcView.append(buffer);
                }
                break;

            case R.id.btDeciminalSeparator:
                if (calcView.getText().toString().indexOf(',') < 0) {
                    calcView.append(",");
                }
                break;
            case R.id.btCleanEnd:
                calcView.setText("0");
                break;
            case R.id.btBackSpace:                                                                              //Поправить эту логику
                if (calcView.getText().length() > 2) {
                    calcView.setText(calcView.getText().subSequence(0, calcView.getText().length() - 1));
                } else if (calcView.getText().length() > 1 && !isNegative) {
                    calcView.setText(calcView.getText().subSequence(0, calcView.getText().length() - 1));
                } else {
                    calcView.setText("0");
                }
                break;
        }
    }
}
