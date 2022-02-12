package ru.junJavaDev.calculator.listeners;

import static androidx.core.content.res.TypedArrayUtils.getString;
import static ru.junJavaDev.calculator.MainActivity.textView;

import android.view.Gravity;
import android.view.View;

import com.example.calculator.R;

import ru.junJavaDev.calculator.Buttons;

public class EqualsLongClickListener implements View.OnLongClickListener {

    @Override
    public boolean onLongClick(View view) {
        if (textView.getText().toString().equals("88")) {
            textView.setTextSize(25);
            textView.setGravity(Gravity.LEFT);
            textView.setText(R.string.eighty_eight);
            Buttons.setUnClickable();
        }
        return false;
    }
}
