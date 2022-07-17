package figures;

import interfaces.Clockwise;
import lombok.Getter;

import java.util.Arrays;

@Getter
public abstract class Figure implements Clockwise {
    protected int[][] body;
    private final int rows;
    private final int cols;

    public Figure(int[][] body) {
        this.body = body;
        rows = body.length;
        cols = body[0].length;
    }

    @Override
    public void turnClockwise() {
        arrayClockwise();
        moveLeft();
     }

    private void arrayClockwise() {
        int[][] arrayClockwise = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                arrayClockwise[row][col] = body[cols - col - 1][row];
            }
        }
        body = arrayClockwise;
    }

    private void moveLeft() {
        if (isFirstColEmpty()) {
            int[][] movedLeft = new int[rows][cols];
            for (int row = 0; row < rows; row++) {
                int temp = this.body[row][0];
                for (int col = 0; col < cols - 1; col++) {
                    movedLeft[row][col] = body[row][col + 1];
                }
                movedLeft[row][cols - 1] = temp;
            }
            body = movedLeft;
        }
    }


    private boolean isFirstColEmpty() {
        boolean isFirstColEmpty = true;
        for (int[] row : body) {
            if (row[0] == 1) {
                isFirstColEmpty = false;
                break;
            }
        }
        return isFirstColEmpty;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(body)
                .replaceAll("\\[", "")
                .replaceAll("], ", "\n")
                .replaceAll("]", "")
                .replaceAll("0", " ")
                .replaceAll(",", "")
                + "\n--------------------";
    }
}
