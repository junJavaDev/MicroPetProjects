package ru.junJavaDev.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.R;

import java.text.DecimalFormat;
import java.util.Objects;

import ru.junJavaDev.calculator.listeners.ActionListener;
import ru.junJavaDev.calculator.listeners.CleanAllListener;
import ru.junJavaDev.calculator.listeners.EqualsListener;
import ru.junJavaDev.calculator.listeners.EqualsLongClickListener;
import ru.junJavaDev.calculator.listeners.FunctionListener;
import ru.junJavaDev.calculator.listeners.InputListener;

public class MainActivity extends AppCompatActivity {

    public static Action action = null;
    public static Double firstArgument = null;
    public static Double secondArgument = null;
    public static Double equals = null;
    @SuppressLint("StaticFieldLeak")
    public static TextView textView;
    public static boolean isCalculation;
    public static DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        decimalFormat = new DecimalFormat("###########.#########");
        Objects.requireNonNull(getSupportActionBar()).hide();   // Разобраться с этой ошибкой

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.screenDisplay);
        textView.setMovementMethod(new ArrowKeyMovementMethod());

        Button buttonZero = findViewById(R.id.btZero);
        Button buttonOne = findViewById(R.id.btOne);
        Button buttonTwo = findViewById(R.id.btTwo);
        Button buttonThree = findViewById(R.id.btThree);
        Button buttonFour = findViewById(R.id.btFour);
        Button buttonFive = findViewById(R.id.btFive);
        Button buttonSix = findViewById(R.id.btSix);
        Button buttonSeven = findViewById(R.id.btSeven);
        Button buttonEight = findViewById(R.id.btEight);
        Button buttonNine = findViewById(R.id.btNine);
        Button buttonPositiveNegative = findViewById(R.id.btPositiveNegative);
        Button buttonDeciminalSeparator = findViewById(R.id.btDeciminalSeparator);
        Button buttonCleanEnd = findViewById(R.id.btCleanEnd);
        Button buttonBackSpace = findViewById(R.id.btBackSpace);
        Button buttonPlus = findViewById(R.id.btPlus);
        Button buttonMinus = findViewById(R.id.btMinus);
        Button buttonMultiply = findViewById(R.id.btMultiply);
        Button buttonDivision = findViewById(R.id.btDivision);
        Button buttonFraction = findViewById(R.id.btFraction);
        Button buttonSquare = findViewById(R.id.btSquare);
        Button buttonPower = findViewById(R.id.btPower);
        Button buttonPercent = findViewById(R.id.btPercent);
        Button buttonCleanAll = findViewById(R.id.btCleanAll);
        Button buttonEquals = findViewById(R.id.btEquals);

        Buttons.addInputButtons(buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour,
                buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine,
                buttonPositiveNegative, buttonDeciminalSeparator, buttonCleanEnd, buttonBackSpace);
        Buttons.addActionButtons(buttonPlus, buttonMinus, buttonMultiply, buttonDivision);
        Buttons.addFunctionButtons(buttonFraction, buttonSquare, buttonPower, buttonPercent);
        Buttons.setEqualsButton(buttonEquals);
        Buttons.setCleanAllButton(buttonCleanAll);

        Buttons.setListener(Buttons.getInputButtons(), new InputListener());
        Buttons.setListener(Buttons.getActionButtons(), new ActionListener());
        Buttons.setListener(Buttons.getFunctionButtons(), new FunctionListener());
        buttonCleanAll.setOnClickListener(new CleanAllListener());
        buttonEquals.setOnClickListener(new EqualsListener());
        buttonEquals.setOnLongClickListener(new EqualsLongClickListener());

    }
    public static Double getNumber() {
        if (textView.getText().charAt(0) == '~') {
            return Double.parseDouble(textView.getText().subSequence(1, textView.length()).toString().replace(',', '.'));
        } else return Double.parseDouble(textView.getText().toString().replace(',', '.'));
    }

    public static void showResult(Double equals) {
        StringBuffer result = new StringBuffer(decimalFormat.format(equals));
        if (equals > 9999999999L ||
                equals < -999999999L ||
                equals > 0 && equals < 0.00000001 ||
                equals < 0 && equals > -0.0000001
        ) {
            textView.setTextSize(25);
            textView.setText("Выход за пределы диапазона");
            Buttons.setClickable();
        } else if (result.length() > 10) {
            result.insert(0, '~');
            textView.setText(result.subSequence(0, 11));
        } else {
            textView.setText(result);
        }
    }

    public static void reset() {
        action = null;
        firstArgument = null;
        secondArgument = null;
        equals = null;
        textView.setTextSize(60);
        textView.setGravity(Gravity.CENTER);
        textView.setText("0");
        Buttons.setClickable();
    }
}