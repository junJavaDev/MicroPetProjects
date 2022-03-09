package ru.junJavaDev.calculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Buttons extends androidx.appcompat.widget.AppCompatButton{

    public  Button buttonZero;
    public  Button buttonOne;
    public  Button buttonTwo;
    public Button buttonThree;
    public Button buttonFour;
    public Button buttonFive;
    public Button buttonSix;
    public Button buttonSeven;
    public Button buttonEight;
    public Button buttonNine;
    public Button buttonPositiveNegative;
    public Button buttonDeciminalSeparator;
    public Button buttonCleanEnd;
    public Button buttonBackSpace;


    private Set<Button> inputButtons = new HashSet<>();
    private static Set<Button> actionButtons = new HashSet<>();
    private static Set<Button> functionButtons = new HashSet<>();
    private static Button equalsButton;
    private static Button cleanAllButton;

    public Buttons(Context context, Button btZero, Button btOne, Button btTwo, Button btThree,
                   Button btFour, Button btFive, Button btSix, Button btSeven, Button btEight,
                   Button btNine, Button btPositiveNegative, Button btDeciminalSeparator,
                   Button btCleanEnd, Button btBackSpace) {
        super(context);
        buttonZero = btZero;
        buttonOne = btOne;
        buttonTwo = btTwo;
        buttonThree = btThree;
        buttonFour = btFour;
        buttonFive = btFive;
        buttonSix = btSix;
        buttonSeven = btSeven;
        buttonEight = btEight;
        buttonNine = btNine;
        buttonPositiveNegative = btPositiveNegative;
        buttonDeciminalSeparator = btDeciminalSeparator;
        buttonCleanEnd = btCleanEnd;
        buttonBackSpace = btBackSpace;}


    public void addInputButtons() {
        inputButtons.add(buttonZero);
        inputButtons.add(buttonOne);
        inputButtons.add(buttonTwo);
        inputButtons.add(buttonThree);
        inputButtons.add(buttonFour);
        inputButtons.add(buttonFive);
        inputButtons.add(buttonSix);
        inputButtons.add(buttonSeven);
        inputButtons.add(buttonEight);
        inputButtons.add(buttonNine);
        inputButtons.add(buttonPositiveNegative);
        inputButtons.add(buttonDeciminalSeparator);
        inputButtons.add(buttonCleanEnd);
        inputButtons.add(buttonBackSpace);
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

    public Set<Button> getInputButtons() {
        return inputButtons;
    }

    public static Set<Button> getActionButtons() {
        return actionButtons;
    }

    public static Set<Button> getFunctionButtons() {
        return functionButtons;
    }

    public void setUnClickable() {
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

    public  void setClickable() {
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
