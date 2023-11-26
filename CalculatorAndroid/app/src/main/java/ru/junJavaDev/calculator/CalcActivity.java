package ru.junJavaDev.calculator;

import static ru.junJavaDev.calculator.Setting.CALC_TEXT_SIZE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.os.VibratorManager;
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
    public static boolean isCalculation;
    public static DecimalFormat decimalFormat;
    private TextView inputView;
    private TextView actionView;
    private Vibrator vibrator;
    Buttons buttons;

    @SuppressLint({"SourceLockedOrientationActivity", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        buttons = new Buttons(this);

        decimalFormat = new DecimalFormat("###########.#########");

        inputView = findViewById(R.id.inputDisplay);
        actionView = findViewById(R.id.actionDisplay);

        Buttons.setListener(buttons.getInputButtons(), new InputListener(this));
        Buttons.setListener(buttons.getActionButtons(), new ActionListener(this));
        Buttons.setListener(buttons.getFunctionButtons(), new FunctionListener(this));
        buttons.getCleanAllButton().setOnClickListener(new CleanAllListener(this));
        buttons.getEqualsButton().setOnClickListener(new EqualsListener(this));
        buttons.getEqualsButton().setOnLongClickListener(new EqualsLongClickListener(this));

    }
    public Double getNumber() {
        if (inputView.getText().charAt(0) == '~') {
            return Double.parseDouble(inputView.getText().subSequence(1, inputView.length()).toString().replace(',', '.'));
        } else return Double.parseDouble(inputView.getText().toString().replace(',', '.'));
    }

    public void showResult(Double equals) {
        StringBuffer result = new StringBuffer(decimalFormat.format(equals));
        if (equals > 9999999999L ||
                equals < -999999999L ||
                equals > 0 && equals < 0.00000001 ||
                equals < 0 && equals > -0.0000001
        ) {
            this.reset();
            buttons.setClickable(false);
            inputView.setText(R.string.out_of_range);

        } else if (result.length() > 10) {
            result.insert(0, '~');
            inputView.setText(result.subSequence(0, 11));
        } else {
            inputView.setText(result);
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
        isCalculation = false;
        inputView.setTextSize(CALC_TEXT_SIZE);
        inputView.setGravity(Gravity.CENTER);
        inputView.setText(R.string.zero);
        actionView.setText("");
        buttons.setClickable(true);
    }

    public Double calculateEquals() {
        return switch (action) {
            case plus -> firstArgument + secondArgument;
            case minus -> firstArgument - secondArgument;
            case multiply -> firstArgument * secondArgument;
            case division -> firstArgument / secondArgument;
        };
    }

    @SuppressLint("SetTextI18n")
    public void showAction() {
        actionView.setText(getFirstArgAndActionText());
    }
    @SuppressLint("SetTextI18n")
    public void showEquals() {
        if (secondArgument != null) {
            actionView.setText(getFirstArgAndActionText() + "\n" + decimalFormat.format(secondArgument));
        } else actionView.setText("");
    }

    private String getFirstArgAndActionText() {
        if (firstArgument != null && action != null) {
            String firstArg = decimalFormat.format(firstArgument);
            if (firstArg.length() > 11) {
                actionView.setText(firstArg.substring(0, 11));
            }
            return firstArg + " " + getString(action.symbol);
        } else return "";
    }
    public TextView getInputView() {
        return inputView;
    }
}