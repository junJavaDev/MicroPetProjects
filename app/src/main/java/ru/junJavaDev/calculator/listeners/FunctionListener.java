package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.MainActivity.getNumber;
import static ru.junJavaDev.calculator.MainActivity.secondArgument;
import static ru.junJavaDev.calculator.MainActivity.firstArgument;
import static ru.junJavaDev.calculator.MainActivity.showResult;

import android.os.Vibrator;
import android.view.View;

import com.example.calculator.R;

import ru.junJavaDev.calculator.Setting;

public class FunctionListener implements View.OnClickListener {
    private Vibrator vibrator;

    public FunctionListener(Vibrator vibrator) {
        this.vibrator = vibrator;
    }

    @Override
    public void onClick(View view) {
        if (vibrator.hasVibrator()) vibrator.vibrate(Setting.VIBRATOR_DELAY);
        secondArgument = getNumber();
        switch (view.getId()) {
            case R.id.btFraction:
                secondArgument = 1 / secondArgument;
                showResult(secondArgument);
                break;
            case R.id.btSquare:
                secondArgument = Math.sqrt(secondArgument);
                showResult(secondArgument);
                break;
            case R.id.btPower:
                secondArgument *= secondArgument;
                showResult(secondArgument);
                break;
            case R.id.btPercent:
                if (firstArgument == null) {
                    secondArgument = 0D;
                } else {
                    secondArgument *= firstArgument / 100;
                }
                showResult(secondArgument);
                break;
        }
    }
}
