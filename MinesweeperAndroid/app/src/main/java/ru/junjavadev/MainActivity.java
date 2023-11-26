package ru.junjavadev;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import ru.junjavadev.minesweeper.R;

public class MainActivity extends AppCompatActivity {

    private static int rows;
    private static int columns;
    private static int bombs;
    public static final int MAX_ROWS = 100;
    public static final int MAX_COLUMNS = 40;
    public static final int MIN_VALUE = 2;
    public static final int MIN_BOMB_VALUE = 1;
    public static final int DEFAULT_VALUE = 10;
    public static final String EASY_ROWS_COLUMNS = "9";
    public static final String EASY_BOMBS = "10";
    public static final String MEDIUM_ROWS_COLUMNS = "16";
    public static final String MEDIUM_BOMBS = "40";
    public static final String HARD_ROWS = "30";
    public static final String HARD_COLUMNS = "16";
    public static final String HARD_BOMBS = "99";

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button personalGame = findViewById(R.id.personalGame);
        Button easyGame = findViewById(R.id.easyGame);
        Button normalGame = findViewById(R.id.mediumGame);
        Button hardGame = findViewById(R.id.hardGame);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        EditText rowsIn = findViewById(R.id.rowsIn);
        EditText columnIn = findViewById(R.id.columnsIn);
        EditText bombsIn = findViewById(R.id.bombsIn);

        personalGame.setOnClickListener(view -> {
            rows = getRows(String.valueOf(rowsIn.getText()));
            columns = getColumns(String.valueOf(columnIn.getText()));
            bombs = getBombs(String.valueOf(bombsIn.getText()));
            Intent intent = new Intent(MainActivity.this, MinesweeperAndroid.class);
            intent.putExtra("ROWS", rows);
            intent.putExtra("COLUMNS", columns);
            intent.putExtra("BOMBS", bombs);
            startActivity(intent);
        });

        easyGame.setOnClickListener(view -> {
            rowsIn.setText(EASY_ROWS_COLUMNS);
            columnIn.setText(EASY_ROWS_COLUMNS);
            bombsIn.setText(EASY_BOMBS);
        });

        normalGame.setOnClickListener(view -> {
            rowsIn.setText(MEDIUM_ROWS_COLUMNS);
            columnIn.setText(MEDIUM_ROWS_COLUMNS);
            bombsIn.setText(MEDIUM_BOMBS);
        });

        hardGame.setOnClickListener(view -> {
            rowsIn.setText(HARD_ROWS);
            columnIn.setText(HARD_COLUMNS);
            bombsIn.setText(HARD_BOMBS);
        });
    }

    private int getColumns (String in) {
        int columns = getNumber(in);
        if (columns > MAX_COLUMNS) {
            columns = MAX_COLUMNS;
        }
        return Math.max(columns, MIN_VALUE);
    }

    private int getRows (String in) {
        int rows = getNumber(in);
        if (rows > MAX_ROWS) {
            rows = MAX_ROWS;
        }
        return Math.max(rows, MIN_VALUE);
    }

    private int getBombs (String in) {
        int bombs = getNumber(in);
        return Math.max(bombs, MIN_BOMB_VALUE);
    }

    private int getNumber (String in) {
        int number;
        if (in.equals("")) {
            return DEFAULT_VALUE;
        } else {
            try {
                number = Integer.parseInt(in);
            } catch (Exception e) {
                return DEFAULT_VALUE;
            }
            return number;
        }
    }
}