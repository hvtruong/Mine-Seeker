package com.example.gamemineseeker.Model;

public class MineCoordinate {
    private int MineRow;
    private int MineCol;

    public MineCoordinate() {
        this.MineRow = -1;
        this.MineCol = -1;
    }

    public MineCoordinate(int hiddenMineRow, int hiddenMineCol) {
        this.MineRow = hiddenMineRow;
        this.MineCol = hiddenMineCol;
    }

    public int getMineCol() {
        return MineCol;
    }

    public int getMineRow() {
        return MineRow;
    }

    public void setHiddenMineRow(int hiddenMineRow) {
        this.MineRow = hiddenMineRow;
    }

    public void setHiddenMineCol(int hiddenMineCol) {
        this.MineCol = hiddenMineCol;
    }
}
