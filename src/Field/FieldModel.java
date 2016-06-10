package Field;

import sample.Settings;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class FieldModel {
    private int xCells;
    private int yCells;
    private int minesCount;
    private CellStatus[][] field;
    private byte[][] surroundingMinesCount;

    FieldModel() { }

    public void setNewField(Settings settings) {
        xCells = settings.getXCells();
        yCells = settings.getYCells();
        this.minesCount = settings.getMinesCount();
        generateMines();
    }

    public CellStatus getCellStatus(int x, int y) {
        return field[y][x];
    }

    public CellStatus getCellStatus(FieldCell cell) {
        return field[cell.getY()][cell.getX()];
    }

    public void setCellStatus(FieldCell cell, CellStatus status) {
        field[cell.getY()][cell.getX()] = status;
    }

    public void setCellStatus(int x, int y, CellStatus status) {
        field[y][x] = status;
    }

    private void IncrementCellMinesCount(int x, int y) {
        surroundingMinesCount[y][x]++;
    }

    private void IncrementSurroundingMinesCount(int x, int y) {
        if (checkCell(x - 1, y - 1)) IncrementCellMinesCount(x - 1, y - 1);
        if (checkCell(x, y - 1)) IncrementCellMinesCount(x, y - 1);
        if (checkCell(x + 1, y - 1)) IncrementCellMinesCount(x + 1, y - 1);
        if (checkCell(x + 1, y)) IncrementCellMinesCount(x + 1, y);
        if (checkCell(x + 1, y + 1)) IncrementCellMinesCount(x + 1, y + 1);
        if (checkCell(x, y + 1)) IncrementCellMinesCount(x, y + 1);
        if (checkCell(x - 1, y + 1)) IncrementCellMinesCount(x - 1, y + 1);
        if (checkCell(x - 1, y)) IncrementCellMinesCount(x - 1, y);
    }

    private void generateMines() {
        Random randomizer = new Random();
        List<FieldCell> valuesList = new LinkedList<>();
        field = new CellStatus[yCells][xCells];
        surroundingMinesCount = new byte[yCells][xCells];

        for (int i = 0, j = 0; i < xCells;) {
            valuesList.add(new FieldCell(i, j));
            setCellStatus(i, j++, CellStatus.EMPTY);
            if (j == yCells) {
                j = 0;
                ++i;
            }
        }

        FieldCell tempCell;
        for (int i = 0, x1 = 0, y1 = 0, x2, y2, x, y; i < minesCount; ++i) {
            tempCell = valuesList.remove(randomizer.nextInt(xCells * yCells - i));
            setCellStatus(tempCell.getX(), tempCell.getY(), CellStatus.MINED);
            IncrementSurroundingMinesCount(tempCell.getX(), tempCell.getY());
        }
    }

    public boolean toggleCellFlag(int x, int y) {
        switch (getCellStatus(x, y)) {
            case EMPTY:
                setCellStatus(x, y, CellStatus.EMPTY_FLAGGED);
                break;

            case EMPTY_FLAGGED:
                setCellStatus(x, y, CellStatus.EMPTY_INQUIRED);
                break;

            case EMPTY_INQUIRED:
                setCellStatus(x, y, CellStatus.EMPTY);
                break;

            case MINED:
                setCellStatus(x, y, CellStatus.MINED_FLAGGED);
                break;

            case MINED_FLAGGED:
                setCellStatus(x, y, CellStatus.MINED_INQUIRED);
                break;

            case MINED_INQUIRED:
                setCellStatus(x, y, CellStatus.MINED);
                break;
        }
        return checkForWin();
    }

    public boolean checkForWin() {
        for (int i = 0, j = 0; i < yCells;) {
            switch (getCellStatus(j, i)) {
                case EMPTY:
                case EMPTY_FLAGGED:
                case EMPTY_INQUIRED:
                case MINED:
                case MINED_INQUIRED:
                    return false;
            }

            ++j;
            if (j == xCells) {
                j = 0;
                ++i;
            }
        }

        return true;
    }

    public int getCellMinesCount(int x, int y) {
        return surroundingMinesCount[y][x];
    }

    public int getXCells() {
        return xCells;
    }

    public int getYCells() {
        return yCells;
    }

    public boolean checkCell(int x, int y) {
        return (x >= 0 && y >= 0 && x < xCells && y < yCells);
    }
}
