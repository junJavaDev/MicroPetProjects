package ru.junJavaDev.calculator;

import android.os.Build;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;
import java.util.Set;

public class Buttons {
    private boolean clickable = true;
    private final CalcActivity activity;

    public Buttons(CalcActivity activity) {
        this.activity = activity;
        addInputButtons();
        addActionButtons();
        addFunctionButtons();
        addCleanAllButton();
        addEqualsButton();
    }

    private final Set<Button> inputButtons = new HashSet<>();
    private final Set<Button> actionButtons = new HashSet<>();
    private final Set<Button> functionButtons = new HashSet<>();
    private Button cleanAllButton;
    private Button equalsButton;

    public void setClickable(boolean clickable) {
        if (this.clickable != clickable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                inputButtons.forEach(btn -> btn.setClickable(clickable));
                actionButtons.forEach(btn -> btn.setClickable(clickable));
                functionButtons.forEach(btn -> btn.setClickable(clickable));
                equalsButton.setClickable(clickable);
                equalsButton.setLongClickable(clickable);
                this.clickable = clickable;
            }
        }
    }

    public void addInputButtons() {
        inputButtons.addAll(Set.of (
                activity.findViewById(R.id.btZero),
                activity.findViewById(R.id.btOne),
                activity.findViewById(R.id.btTwo),
                activity.findViewById(R.id.btThree),
                activity.findViewById(R.id.btFour),
                activity.findViewById(R.id.btFive),
                activity.findViewById(R.id.btSix),
                activity.findViewById(R.id.btSeven),
                activity.findViewById(R.id.btEight),
                activity.findViewById(R.id.btNine),
                activity.findViewById(R.id.btPositiveNegative),
                activity.findViewById(R.id.btDeciminalSeparator),
                activity.findViewById(R.id.btCleanEnd),
                activity.findViewById(R.id.btBackSpace))
        );
    }

    private void addActionButtons() {
        actionButtons.addAll(Set.of(
                activity.findViewById(R.id.btPlus),
                activity.findViewById(R.id.btMinus),
                activity.findViewById(R.id.btMultiply),
                activity.findViewById(R.id.btDivision)
        ));
    }

    private void addFunctionButtons() {
        functionButtons.addAll(Set.of(
                activity.findViewById(R.id.btFraction),
                activity.findViewById(R.id.btSquare),
                activity.findViewById(R.id.btPower),
                activity.findViewById(R.id.btPercent)
        ));
    }

    private void addCleanAllButton() {
        this.cleanAllButton = activity.findViewById(R.id.btCleanAllText);
    }

    private void addEqualsButton() {
        this.equalsButton = activity.findViewById(R.id.btEquals);
    }

    public static void setListener (Set<Button> buttons, View.OnClickListener listener) {
        for (Button button : buttons) {
            button.setOnClickListener(listener);
        }
    }

    public Set<Button> getInputButtons() {
        return inputButtons;
    }

    public Set<Button> getActionButtons() {
        return actionButtons;
    }

    public Set<Button> getFunctionButtons() {
        return functionButtons;
    }

    public Button getCleanAllButton() {
        return cleanAllButton;
    }

    public Button getEqualsButton() {
        return equalsButton;
    }
}
