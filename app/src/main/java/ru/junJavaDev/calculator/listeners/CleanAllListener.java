package ru.junJavaDev.calculator.listeners;

import android.view.View;

import ru.junJavaDev.calculator.MainActivity;

public class CleanAllListener implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        MainActivity.reset();
    }
}
