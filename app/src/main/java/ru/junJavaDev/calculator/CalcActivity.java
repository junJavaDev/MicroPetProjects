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
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Objects;

import ru.junJavaDev.calculator.listeners.ActionListener;
import ru.junJavaDev.calculator.listeners.CleanAllListener;
import ru.junJavaDev.calculator.listeners.EqualsListener;
import ru.junJavaDev.calculator.listeners.EqualsLongClickListener;
import ru.junJavaDev.calculator.listeners.FunctionListener;
import ru.junJavaDev.calculator.listeners.InputListener;

public class CalcActivity extends AppCompatActivity {

    public static Action action = null;
    public static Double firstArgument = null;
    public static Double secondArgument = null;
    public static Double equals = null;
    @SuppressLint("StaticFieldLeak")
    public static TextView calcView;
    public static boolean isCalculation;
    public static DecimalFormat decimalFormat;
    Buttons buttons;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        buttons = new Buttons(this);

        decimalFormat = new DecimalFormat("###########.#########");

        calcView = findViewById(R.id.screenDisplay);
        calcView.setMovementMethod(new ArrowKeyMovementMethod());

        Vibrator vibrator;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            VibratorManager vm = (VibratorManager) getSystemService(VIBRATOR_MANAGER_SERVICE);
            vibrator = vm.getDefaultVibrator();
        } else {
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        }

        Buttons.setListener(buttons.getInputButtons(), new InputListener(vibrator));
        Buttons.setListener(buttons.getActionButtons(), new ActionListener(vibrator));
        Buttons.setListener(buttons.getFunctionButtons(), new FunctionListener(vibrator));
        buttons.getCleanAllButton().setOnClickListener(new CleanAllListener(vibrator, this));
        buttons.getEqualsButton().setOnClickListener(new EqualsListener(vibrator));
        buttons.getEqualsButton().setOnLongClickListener(new EqualsLongClickListener());

    }
    public static Double getNumber() {
        if (calcView.getText().charAt(0) == '~') {
            return Double.parseDouble(calcView.getText().subSequence(1, calcView.length()).toString().replace(',', '.'));
        } else return Double.parseDouble(calcView.getText().toString().replace(',', '.'));
    }

    public static void showResult(Double equals) {
        StringBuffer result = new StringBuffer(decimalFormat.format(equals));
        if (equals > 9999999999L ||
                equals < -999999999L ||
                equals > 0 && equals < 0.00000001 ||
                equals < 0 && equals > -0.0000001
        ) {
            calcView.setTextSize(25);
            calcView.setText("Выход за пределы диапазона");
//            Buttons.setClickable();
        } else if (result.length() > 10) {
            result.insert(0, '~');
            calcView.setText(result.subSequence(0, 11));
        } else {
            calcView.setText(result);
        }
    }

    public void reset() {
        action = null;
        firstArgument = null;
        secondArgument = null;
        equals = null;
        calcView.setTextSize(60);
        calcView.setGravity(Gravity.CENTER);
        calcView.setText("0");
    }
}