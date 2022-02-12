package ru.junJavaDev.calculator.listeners;

import static ru.junJavaDev.calculator.MainActivity.getNumber;
import static ru.junJavaDev.calculator.MainActivity.secondArgument;
import static ru.junJavaDev.calculator.MainActivity.firstArgument;
import static ru.junJavaDev.calculator.MainActivity.showResult;

import android.view.View;

import com.example.calculator.R;

public class FunctionListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
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
