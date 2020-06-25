package com.example.gamemineseeker.Model;

import java.util.ArrayList;
import java.util.Random;

public class GamePlayOptions {
    private int numRow, numCol, numMine;
    private static GamePlayOptions instance;
    private ArrayList<MineCoordinate> hiddenMinesManager = new ArrayList<>();
    private int totalGame;

    public void setTotalGame(int totalGame) {
        this.totalGame = totalGame;
    }

    public int getTotalGame() {
        return totalGame;
    }

    public GamePlayOptions(){
        numRow = 4;
        numCol = 7;
        numMine = 8;
        totalGame = 0;
        populateHiddenMines();
    }

    public GamePlayOptions(int row, int col, int mine){
        numRow = row;
        numCol = col;
        numMine = mine;
        populateHiddenMines();
    }

    public static GamePlayOptions getInstance(){
        if(instance == null)
            instance = new GamePlayOptions();
        return instance;
    }

    public int getNumMine() {
        return numMine;
    }

    public void setNumMine(int numMine) {
        this.numMine = numMine;
    }

    public int getNumCol() {
        return numCol;
    }

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }

    public int getNumRow() {
        return numRow;
    }

    public void setNumRow(int numRow) {
        this.numRow = numRow;
    }

    public static int generateRandomInt(int upper){
        Random random = new Random();
        return random.nextInt(upper);
    }

    public MineCoordinate getMine(int index){
        return hiddenMinesManager.get(index);
    }

    public boolean isMineHere(int checkedRow, int checkedCol){
        for(int i = 0; i <hiddenMinesManager.size(); i++){
            if(hiddenMinesManager.get(i).getMineRow() == checkedRow && hiddenMinesManager.get(i).getMineCol() == checkedCol){
                return true;
            }
        }
        return false;
    }

    public void populateHiddenMines(){
        hiddenMinesManager.clear();
        for(int i = 1; i <= numMine; i++ ){
            int randomRow = generateRandomInt(numRow);
            int randomCol = generateRandomInt(numCol);

            //Check if there is a mine already here
            if(isMineHere(randomRow, randomCol) == true){
                i--;
                continue;
            }

            hiddenMinesManager.add(new MineCoordinate(randomRow,randomCol));
        }
    }

    public void eraseRevealedMine(int row, int col){
        for(int i = 0; i <hiddenMinesManager.size(); i++){
            if(hiddenMinesManager.get(i).getMineRow() == row && hiddenMinesManager.get(i).getMineCol() == col){
                hiddenMinesManager.remove(i);
                break;
            }
        }
    }
}
