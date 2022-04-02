package ru.junjavadev.minesweeper;

public class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;
    private int countOfFlags = 0;


    public int getCountOfFlags() {
        return countOfFlags;
    }

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfFlags = 0;
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;
    }

    public void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGGED:
                setClosedToBox(coord);
                break;
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    public void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
        countOfFlags--;
    }

    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGGED);
        countOfFlags++;
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    public void setOpenedToClosedBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED) {
            flagMap.set(coord, Box.OPENED);
        }
    }

    public void setNobombToFlaggedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGGED) {
            flagMap.set(coord, Box.NOBOMB);
        }
    }



    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (flagMap.get(around) == Box.FLAGGED) {
                count++;
            }
        }
        return count;
    }

}
