package ru.junJavaDev.calculator;

import static ru.junJavaDev.calculator.Setting.CALC_TEXT_SIZE;

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
    private Vibrator vibrator;
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

        Buttons.setListener(buttons.getInputButtons(), new InputListener(this));
        Buttons.setListener(buttons.getActionButtons(), new ActionListener(this));
        Buttons.setListener(buttons.getFunctionButtons(), new FunctionListener(this));
        buttons.getCleanAllButton().setOnClickListener(new CleanAllListener(this));
        buttons.getEqualsButton().setOnClickListener(new EqualsListener(this));
        buttons.getEqualsButton().setOnLongClickListener(new EqualsLongClickListener(this));

    }
    public static Double getNumber() {
        if (calcView.getText().charAt(0) == '~') {
            return Double.parseDouble(calcView.getText().subSequence(1, calcView.length()).toString().replace(',', '.'));
        } else return Double.parseDouble(calcView.getText().toString().replace(',', '.'));
    }

    public void showResult(Double equals) {
        StringBuffer result = new StringBuffer(decimalFormat.format(equals));
        if (equals > 9999999999L ||
                equals < -999999999L ||
                equals > 0 && equals < 0.00000001 ||
                equals < 0 && equals > -0.0000001
        ) {
            calcView.setTextSize(25);
            this.reset();
            calcView.setText(R.string.out_of_range);
        } else if (result.length() > 10) {
            result.insert(0, '~');
            calcView.setText(result.subSequence(0, 11));
        } else {
            calcView.setText(result);
        }
    }

    public Vibrator getVibrator() {
        if (vibrator == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                VibratorManager vm = (VibratorManager) getSystemService(VIBRATOR_MANAGER_SERVICE);
                vibrator = vm.getDefaultVibrator();
            } else {
                vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            }
        }
        return vibrator;
    }

    public void reset() {
        action = null;
        firstArgument = null;
        secondArgument = null;
        equals = null;
        calcView.setTextSize(CALC_TEXT_SIZE);
        calcView.setGravity(Gravity.CENTER);
        calcView.setText(R.string.zero);
    }
}