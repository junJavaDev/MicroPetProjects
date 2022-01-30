package ru.junJavaDev.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.R;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Action action = null;
    Double firstArgument = null;
    Double secondArgument = null;
    Double equals = null;
    TextView textView;
    CharSequence buffer;

    boolean isZero;
    boolean isNegative;
    boolean isZeroNegative;
    boolean isApproximate;
    boolean isCalculation;

    Button buttonZero;
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;
    Button buttonFive;
    Button buttonSix;
    Button buttonSeven;
    Button buttonEight;
    Button buttonNine;

    Button buttonPositiveNegative;
    Button buttonDeciminalSeparator;
    Button buttonBackSpace;
    Button buttonCleanEnd;
    Button buttonCleanAll;

    Button buttonPlus;
    Button buttonMinus;
    Button buttonMultiply;
    Button buttonDivision;
    Button buttonFraction;
    Button buttonSquare;
    Button buttonPower;
    Button buttonPercent;

    Button buttonEquals;
    DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        decimalFormat = new DecimalFormat("###########.#########");
        Objects.requireNonNull(getSupportActionBar()).hide();   // Разобраться с этой ошибкой

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.screenDisplay);
        textView.setMovementMethod(new ArrowKeyMovementMethod());

        buttonZero = findViewById(R.id.btZero);
        buttonOne = findViewById(R.id.btOne);
        buttonTwo = findViewById(R.id.btTwo);
        buttonThree = findViewById(R.id.btThree);
        buttonFour = findViewById(R.id.btFour);
        buttonFive = findViewById(R.id.btFive);
        buttonSix = findViewById(R.id.btSix);
        buttonSeven = findViewById(R.id.btSeven);
        buttonEight = findViewById(R.id.btEight);
        buttonNine = findViewById(R.id.btNine);
        buttonPositiveNegative = findViewById(R.id.btPositiveNegative);
        buttonDeciminalSeparator = findViewById(R.id.btDeciminalSeparator);
        buttonCleanEnd = findViewById(R.id.btCleanEnd);
        buttonCleanAll = findViewById(R.id.btCleanAll);
        buttonBackSpace = findViewById(R.id.btBackSpace);
        buttonPlus = findViewById(R.id.btPlus);
        buttonMinus = findViewById(R.id.btMinus);
        buttonMultiply = findViewById(R.id.btMultiply);
        buttonDivision = findViewById(R.id.btDivision);
        buttonFraction = findViewById(R.id.btFraction);
        buttonSquare = findViewById(R.id.btSquare);
        buttonPower = findViewById(R.id.btPower);
        buttonPercent = findViewById(R.id.btPercent);
        buttonEquals = findViewById(R.id.btEquals);

        View.OnClickListener inputListener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")  // Разобраться с этой аннотацией
            @Override
            public void onClick(View view) {
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
        };
        buttonZero.setOnClickListener(inputListener);
        buttonOne.setOnClickListener(inputListener);
        buttonTwo.setOnClickListener(inputListener);
        buttonThree.setOnClickListener(inputListener);
        buttonFour.setOnClickListener(inputListener);
        buttonFive.setOnClickListener(inputListener);
        buttonSix.setOnClickListener(inputListener);
        buttonSeven.setOnClickListener(inputListener);
        buttonEight.setOnClickListener(inputListener);
        buttonNine.setOnClickListener(inputListener);
        buttonPositiveNegative.setOnClickListener(inputListener);
        buttonDeciminalSeparator.setOnClickListener(inputListener);
        buttonCleanEnd.setOnClickListener(inputListener);
        buttonBackSpace.setOnClickListener(inputListener);


        buttonCleanAll.setOnClickListener(view -> reset());

        View.OnClickListener actionListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        };
        buttonPlus.setOnClickListener(actionListener);
        buttonMinus.setOnClickListener(actionListener);
        buttonMultiply.setOnClickListener(actionListener);
        buttonDivision.setOnClickListener(actionListener);

        View.OnClickListener functionListener = new View.OnClickListener() {
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
        };

        buttonFraction.setOnClickListener(functionListener);
        buttonSquare.setOnClickListener(functionListener);
        buttonPower.setOnClickListener(functionListener);
        buttonPercent.setOnClickListener(functionListener);

        buttonEquals.setOnClickListener(new View.OnClickListener() {
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
        });
        buttonEquals.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (textView.getText().toString().equals("88")) {
                    textView.setTextSize(25);
                    textView.setGravity(Gravity.LEFT);
                    textView.setText(getString(R.string.eighty_eight));
                    setUnClickableButtons();
                }
                return false;
            }
        });

    }

    private Double getNumber() {
        if (textView.getText().charAt(0) == '~') {
            return Double.parseDouble(textView.getText().subSequence(1, textView.length()).toString().replace(',', '.'));
        } else return Double.parseDouble(textView.getText().toString().replace(',', '.'));
    }

    private Double calculateEquals() {
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

    private void showResult(Double equals) {
        StringBuffer result = new StringBuffer(decimalFormat.format(equals));
        if (equals > 9999999999L ||
                equals < -999999999L ||
                equals > 0 && equals < 0.00000001 ||
                equals < 0 && equals > -0.0000001
        ) {
            textView.setTextSize(25);
            textView.setText("Выход за пределы диапазона");
            setUnClickableButtons();
        } else if (result.length() > 10) {
            result.insert(0, '~');
            textView.setText(result.subSequence(0, 11));
        } else {
            textView.setText(result);
        }
    }

    private void reset() {
        action = null;
        firstArgument = null;
        secondArgument = null;
        equals = null;
        textView.setTextSize(60);
        textView.setGravity(Gravity.CENTER);
        textView.setText("0");
        setClickableButtons();
    }

    private void setUnClickableButtons() {
        buttonZero.setClickable(false);
        buttonOne.setClickable(false);
        buttonTwo.setClickable(false);
        buttonThree.setClickable(false);
        buttonFour.setClickable(false);
        buttonFive.setClickable(false);
        buttonSix.setClickable(false);
        buttonSeven.setClickable(false);
        buttonEight.setClickable(false);
        buttonNine.setClickable(false);
        buttonPositiveNegative.setClickable(false);
        buttonDeciminalSeparator.setClickable(false);
        buttonCleanEnd.setClickable(false);
        buttonBackSpace.setClickable(false);
        buttonPlus.setClickable(false);
        buttonMinus.setClickable(false);
        buttonMultiply.setClickable(false);
        buttonDivision.setClickable(false);
        buttonFraction.setClickable(false);
        buttonSquare.setClickable(false);
        buttonPower.setClickable(false);
        buttonPercent.setClickable(false);
        buttonEquals.setClickable(false);
    }
    private void setClickableButtons() {
        buttonZero.setClickable(true);
        buttonOne.setClickable(true);
        buttonTwo.setClickable(true);
        buttonThree.setClickable(true);
        buttonFour.setClickable(true);
        buttonFive.setClickable(true);
        buttonSix.setClickable(true);
        buttonSeven.setClickable(true);
        buttonEight.setClickable(true);
        buttonNine.setClickable(true);
        buttonPositiveNegative.setClickable(true);
        buttonDeciminalSeparator.setClickable(true);
        buttonCleanEnd.setClickable(true);
        buttonBackSpace.setClickable(true);
        buttonPlus.setClickable(true);
        buttonMinus.setClickable(true);
        buttonMultiply.setClickable(true);
        buttonDivision.setClickable(true);
        buttonFraction.setClickable(true);
        buttonSquare.setClickable(true);
        buttonPower.setClickable(true);
        buttonPercent.setClickable(true);
        buttonEquals.setClickable(true);
    }

}