package ru.junJavaDev.calculator;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class TextActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static TextView textView;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        textView = findViewById(R.id.screenTextDisplay);
        changeText();
    }

    public void changeText() {
        float textSize = getIntent().getFloatExtra("textSize", 25);
        int gravity = getIntent().getIntExtra("gravity", 0);
        int text = getIntent().getIntExtra("text", 0);
        textView.setTextSize(textSize);
        textView.setGravity(gravity);
        textView.setText(text);
    }

}
