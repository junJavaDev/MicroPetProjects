package ru.junJavaDev.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.os.VibratorManager;
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
    Buttons buttons;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();   // Разобраться с этой ошибкой
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        buttons = new Buttons(
                findViewById(R.id.btZero),
                findViewById(R.id.btOne),
                findViewById(R.id.btTwo),
                findViewById(R.id.btThree),
                findViewById(R.id.btFour),
                findViewById(R.id.btFive),
                findViewById(R.id.btSix),
                findViewById(R.id.btSeven),
                findViewById(R.id.btEight),
                findViewById(R.id.btNine),
                findViewById(R.id.btPositiveNegative),
                findViewById(R.id.btDeciminalSeparator),
                findViewById(R.id.btCleanEnd),
                findViewById(R.id.btBackSpace));

        decimalFormat = new DecimalFormat("###########.#########");


        textView = findViewById(R.id.screenDisplay);
        textView.setMovementMethod(new ArrowKeyMovementMethod());



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

        buttons.addInputButtons();
        Buttons.addActionButtons(buttonPlus, buttonMinus, buttonMultiply, buttonDivision);
        Buttons.addFunctionButtons(buttonFraction, buttonSquare, buttonPower, buttonPercent);
        Buttons.setEqualsButton(buttonEquals);
        Buttons.setCleanAllButton(buttonCleanAll);

        Vibrator vibrator;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            VibratorManager vm = (VibratorManager) getSystemService(VIBRATOR_MANAGER_SERVICE);
            vibrator = vm.getDefaultVibrator();
        } else {
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        }

        Buttons.setListener(buttons.getInputButtons(), new InputListener(vibrator));
        Buttons.setListener(Buttons.getActionButtons(), new ActionListener(vibrator));
        Buttons.setListener(Buttons.getFunctionButtons(), new FunctionListener(vibrator));
        buttonCleanAll.setOnClickListener(new CleanAllListener(vibrator, this));
        buttonEquals.setOnClickListener(new EqualsListener(vibrator));
        buttonEquals.setOnLongClickListener(new EqualsLongClickListener(buttons));

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
//            Buttons.setClickable();
        } else if (result.length() > 10) {
            result.insert(0, '~');
            textView.setText(result.subSequence(0, 11));
        } else {
            textView.setText(result);
        }
    }

    public void reset() {
        action = null;
        firstArgument = null;
        secondArgument = null;
        equals = null;
        textView.setTextSize(60);
        textView.setGravity(Gravity.CENTER);
        textView.setText("0");
        buttons.setClickable();
    }
}