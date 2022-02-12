package ru.junJavaDev.calculator;

import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Buttons {
    private static Set<Button> inputButtons = new HashSet<>();
    private static Set<Button> actionButtons = new HashSet<>();
    private static Set<Button> functionButtons = new HashSet<>();
    private static Button equalsButton;
    private static Button cleanAllButton;
    public static void addInputButtons(Button ... buttons) {
        inputButtons.addAll(Arrays.asList(buttons));
    }

    public static void addActionButtons(Button ... buttons) {
        actionButtons.addAll(Arrays.asList(buttons));
    }

    public static void addFunctionButtons(Button ... buttons) {
        functionButtons.addAll(Arrays.asList(buttons));
    }

    public static void setListener (Set<Button> buttons, View.OnClickListener listener) {
        for (Button button : buttons) {
            button.setOnClickListener(listener);
        }
    }

    public static void setCleanAllButton(Button cleanAllButton) {
        Buttons.cleanAllButton = cleanAllButton;
    }

    public static void setEqualsButton(Button equalsButton) {
        Buttons.equalsButton = equalsButton;
    }

    public static Set<Button> getInputButtons() {
        return inputButtons;
    }

    public static Set<Button> getActionButtons() {
        return actionButtons;
    }

    public static Set<Button> getFunctionButtons() {
        return functionButtons;
    }

    public static void setUnClickable() {
        for (Button inputButton : inputButtons) {
            inputButton.setClickable(false);
        }
        for (Button actionButton : actionButtons) {
            actionButton.setClickable(false);
        }
        for (Button functionButton : functionButtons) {
            functionButton.setClickable(false);
        }
        equalsButton.setClickable(false);
    }

    public static void setClickable() {
        for (Button inputButton : inputButtons) {
            inputButton.setClickable(true);
        }
        for (Button actionButton : actionButtons) {
            actionButton.setClickable(true);
        }
        for (Button functionButton : functionButtons) {
            functionButton.setClickable(true);
        }
        equalsButton.setClickable(true);
    }
}
