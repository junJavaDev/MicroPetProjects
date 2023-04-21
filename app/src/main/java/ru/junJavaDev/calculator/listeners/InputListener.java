package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.MainActivity.isCalculation;
import static ru.junJavaDev.calculator.MainActivity.textView;

import android.os.Vibrator;
import android.view.View;

import com.example.calculator.R;

import ru.junJavaDev.calculator.Setting;

public class InputListener implements View.OnClickListener {
    private Vibrator vibrator;

    public InputListener(Vibrator vibrator) {
        this.vibrator = vibrator;
    }

    boolean isZero;
    boolean isNegative;
    boolean isZeroNegative;
    boolean isApproximate;
    CharSequence buffer;


    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        if (!isCalculation && textView.getText().length() > 9) {
            return;
        }
        if (isCalculation) {
            textView.setText("0");
            isCalculation = false;
        }
        isApproximate = textView.getText().charAt(0) == '~';
        isZero = textView.getText().charAt(0) == '0' && textView.length() == 1;
        isNegative = textView.getText().charAt(0) == '-';
        isZeroNegative = isNegative && textView.getText().charAt(1) == '0' && textView.length() == 2;


        switch (view.getId()) {

            case R.id.btZero:
                if (isZeroNegative)
                    textView.setText("0");
                else if (!isZero)
                    textView.append("0");
                break;

            case R.id.btOne:
                if (isZero)
                    textView.setText("1");
                else if (isZeroNegative)
                    textView.setText("-1");
                else
                    textView.append("1");
                break;

            case R.id.btTwo:
                if (isZero)
                    textView.setText("2");
                else if (isZeroNegative)
                    textView.setText("-2");
                else
                    textView.append("2");
                break;

            case R.id.btThree:
                if (isZero)
                    textView.setText("3");
                else if (isZeroNegative)
                    textView.setText("-3");
                else
                    textView.append("3");
                break;

            case R.id.btFour:
                if (isZero)
                    textView.setText("4");
                else if (isZeroNegative)
                    textView.setText("-4");
                else
                    textView.append("4");
                break;

            case R.id.btFive:
                if (isZero)
                    textView.setText("5");
                else if (isZeroNegative)
                    textView.setText("-5");
                else
                    textView.append("5");
                break;

            case R.id.btSix:
                if (isZero)
                    textView.setText("6");
                else if (isZeroNegative)
                    textView.setText("-6");
                else
                    textView.append("6");
                break;

            case R.id.btSeven:
                if (isZero)
                    textView.setText("7");
                else if (isZeroNegative)
                    textView.setText("-7");
                else
                    textView.append("7");
                break;

            case R.id.btEight:
                if (isZero)
                    textView.setText("8");
                else if (isZeroNegative)
                    textView.setText("-8");
                else
                    textView.append("8");
                break;

            case R.id.btNine:
                if (isZero)
                    textView.setText("9");
                else if (isZeroNegative)
                    textView.setText("-9");
                else
                    textView.append("9");
                break;

            case R.id.btPositiveNegative:
                if (!isApproximate) {
                    if (isNegative) {
                        textView.setText(
                                textView.getText().subSequence(1, textView.getText().length()));
                    } else {
                        buffer = textView.getText();
                        textView.setText("-");
                        textView.append(buffer);
                    }
                } else if (textView.getText().charAt(1) == '-') {
                    buffer = textView.getText().subSequence(2, textView.getText().length());
                    textView.setText("~");
                    textView.append(buffer);
                } else {
                    buffer = textView.getText().subSequence(1, textView.getText().length());
                    textView.setText("~-");
                    textView.append(buffer);
                }
                break;

            case R.id.btDeciminalSeparator:
                if (textView.getText().toString().indexOf(',') < 0) {
                    textView.append(",");
                }
                break;
            case R.id.btCleanEnd:
                textView.setText("0");
                break;
            case R.id.btBackSpace:                                                                              //Поправить эту логику
                if (textView.getText().length() > 2) {
                    textView.setText(textView.getText().subSequence(0, textView.getText().length() - 1));
                } else if (textView.getText().length() > 1 && !isNegative) {
                    textView.setText(textView.getText().subSequence(0, textView.getText().length() - 1));
                } else {
                    textView.setText("0");
                }
                break;
        }
    }
}
