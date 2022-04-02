package ru.junjavadev;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;
import ru.junjavadev.minesweeper.Box;
import ru.junjavadev.minesweeper.Coord;
import ru.junjavadev.minesweeper.Game;
import ru.junjavadev.minesweeper.R;

public class MinesweeperAndroid extends AppCompatActivity {
    public static final int DEFAULT_VALUE = 10;
    public static Game game;
    TextView massageState;
    TextView bombsCount;
    int ROWS;
    int COLUMNS;
    int BOMBS;
    TableLayout tableLayout;

    public void setMassageState(String textMessage) {
        massageState.setText(textMessage);
    }

    public void setBombsLeftMessage(String textMessage) {
        bombsCount.setText(textMessage);
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field);
        tableLayout = findViewById(R.id.tableLayout);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ROWS = getIntent().getIntExtra("ROWS", DEFAULT_VALUE);
        COLUMNS = getIntent().getIntExtra("COLUMNS", DEFAULT_VALUE);
        BOMBS = getIntent().getIntExtra("BOMBS", DEFAULT_VALUE);
        startNewGame();
    }

    private void startNewGame() {
        game = new Game(ROWS, COLUMNS, BOMBS);
        game.start();
        setImages();
        initField();
        initLabel();
    }

    private void initLabel() {
        massageState = findViewById(R.id.label);
        massageState.setText(R.string.welcome);
        bombsCount = findViewById(R.id.bombsCount);
        bombsCount.setText(getCountOfBombsMessage());
    }

    private void initField() {
        tableLayout.removeAllViews();
        View view;
        for (int i = 0; i < ROWS; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < COLUMNS; j++) {
                Coord coord = new Coord(i, j);
                view = getImageView(game.getBox(coord).name().toLowerCase());
                view.setClickable(true);
                view.setOnClickListener(click -> {
                    game.pressLeftButton(coord);
                    initField();
                    setMassageState(getMessage());
                    setBombsLeftMessage(getCountOfBombsMessage());

                });
                view.setOnLongClickListener(longClick -> {
                    game.pressRightButton(coord);
                    initField();
                    setMassageState(getMessage());
                    setBombsLeftMessage(getCountOfBombsMessage());
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
                return getString(R.string.be_careful);
            case BOMBED:
                Toast.makeText(this, getString(R.string.lose), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, getString(R.string.victory_lose), Toast.LENGTH_SHORT).show();
                return getString(R.string.you_lose);
            case WINNER:
                Toast.makeText(this, getString(R.string.win), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, getString(R.string.victory_win), Toast.LENGTH_SHORT).show();
                return getString(R.string.you_win);
        }
        return null;
    }

    private String getCountOfBombsMessage() {
        return getString(R.string.bombs_count) + " " + game.getBombsLeft();
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
