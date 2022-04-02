package ru.junjavadev;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Objects;

import ru.junjavadev.minesweeper.Box;
import ru.junjavadev.minesweeper.Coord;
import ru.junjavadev.minesweeper.Game;
import ru.junjavadev.minesweeper.R;


public class MinesweeperAndroid extends AppCompatActivity {
    public static Game game;
    TextView massageState;
    final int ROWS = 5;
    final int COLS = 5;
    final int BOMBS = 50;
    TableLayout tableLayout;

    public void setMassageState(String textMessage) {
        massageState.setText(textMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field);
        tableLayout = findViewById(R.id.tableLayout);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        startNewGame();
    }

    private void startNewGame() {
        game = new Game(ROWS, COLS, BOMBS);
        game.start();
        setImages();
        initField();
        initLabel();
    }

    private void initLabel() {
        massageState = findViewById(R.id.label);
        massageState.setText(R.string.welcome);
    }

    private void initField() {
        tableLayout.removeAllViews();
        View view;
        for (int i = 0; i < ROWS; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < COLS; j++) {
                Coord coord = new Coord(i, j);
                view = getImageView(game.getBox(coord).name().toLowerCase());
                view.setClickable(true);
                view.setOnClickListener(click -> {
                    game.pressLeftButton(coord);
                    initField();
                    setMassageState(getMessage());
                });
                view.setOnLongClickListener(longClick -> {
                    game.pressRightButton(coord);
                    initField();
                    setMassageState(getMessage());
                    return true;
                });
                tableRow.addView(view, j);
            }
            tableLayout.addView(tableRow, i);
        }
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED:
                return "Think twice";
            case BOMBED:
                return "YOU LOSE! BIG BA-DA-BOOM!";
            case WINNER:
                return "CONGRATULATIONS";
        }
        return null;
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImageView(box.name().toLowerCase());
        }
    }


    public ImageView getImageView(String name) {
        int resId = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resId);
        return imageView;
    }


}
