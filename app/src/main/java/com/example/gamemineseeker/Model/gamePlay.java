package com.example.gamemineseeker.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.gamemineseeker.Model.GamePlayOptions;
import com.example.gamemineseeker.Model.MineCoordinate;
import com.example.gamemineseeker.View.CongratulationDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamemineseeker.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class gamePlay extends AppCompatActivity {

    GamePlayOptions newGame = GamePlayOptions.getInstance();

    private final int NUM_ROW = newGame.getNumRow();
    private final int NUM_COL = newGame.getNumCol();

    Button[][] buttons = new Button[NUM_ROW][NUM_COL];

    int numFoundMine = 0;
    int numOfScanUsed = 0;
    TextView textView6;
    TextView textView7;
    Animation upToDownScan, leftToRightScan;

    ArrayList<MineCoordinate> revealedMineCoordinate = new ArrayList<>();
    ArrayList<MineCoordinate> alreadyScannedCell = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);

        textView6.setText(getString(R.string.found) + numFoundMine + getString(R.string.of) + newGame.getNumMine() + getString(R.string.mines));
        textView7.setText(R.string.scan_used);

        newGame.populateHiddenMines();
        populateGamePlay();
    }

    @SuppressLint("SetTextI18n")
    private void populateGamePlay() {
        TableLayout table = findViewById(R.id.tableForGamePlay);

        for (int row = 0; row < NUM_ROW; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COL; col++) {
                Button button = new Button(this);

                final int finalRow = row;
                final int finalCol = col;

                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

                tableRow.addView(button);
                button.setPadding(0,0,0,0);

                button.setOnClickListener(view -> {
                    if (newGame.isMineHere(finalRow, finalCol)) {
                        final MediaPlayer foundSound = MediaPlayer.create(this, R.raw.correct_answer);
                        foundSound.start();

                        displayMine(finalRow,finalCol);

                        revealedMineCoordinate.add(new MineCoordinate(finalRow, finalCol));
                        updateScanDisplay(finalRow, finalCol);

                        Vibrator mineFoundVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        mineFoundVibrator.vibrate(150);

                        numFoundMine++;
                        textView6.setText(getString(R.string.found) + numFoundMine + getString(R.string.of) + newGame.getNumMine() + getString(R.string.mines));

                        if(newGame.getNumMine() == revealedMineCoordinate.size()){
                            congratulationDialog();
                        }
                    } else {
                        final MediaPlayer scanSound = MediaPlayer.create(this, R.raw.scan_sound);
                        scanSound.start();

                        boolean isThisCellTapped = false;
                        for(int k = 0; k < alreadyScannedCell.size(); k++) {
                            if(alreadyScannedCell.get(k).getMineRow() == finalRow && alreadyScannedCell.get(k).getMineCol() == finalCol){
                                isThisCellTapped = true;
                                break;
                            }
                        }

                        alreadyScannedCell.add(new MineCoordinate(finalRow, finalCol));
                        scanDisplay(finalRow, finalCol);

                        Vibrator scanVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        scanVibrator.vibrate(250);

                        if(!isThisCellTapped) {
                            numOfScanUsed++;
                            textView7.setText("# Scans used: " + numOfScanUsed);
                        }
                    }
                });

                buttons[row][col] = button;
            }
        }
    }

    void displayMine(int row, int col){
        Button subButton = buttons[row][col];

        for(int i = 0; i < NUM_ROW;i++){
            for(int j = 0; j <NUM_COL;j++){
                Button tempButton = buttons[i][j];

                int buttonW = tempButton.getWidth();
                tempButton.setMinWidth(buttonW);
                tempButton.setMaxWidth(buttonW);

                int buttonH = tempButton.getHeight();
                tempButton.setMinHeight(buttonH);
                tempButton.setMaxHeight(buttonH);
            }
        }

        int width = subButton.getWidth();
        int height = subButton.getHeight();
        Bitmap originalB = BitmapFactory.decodeResource(getResources(),R.drawable.image1);
        Bitmap scaleB = Bitmap.createScaledBitmap(originalB, width, height, true);
        Resources resources = getResources();
        subButton.setBackground(new BitmapDrawable(resources, scaleB));

        newGame.eraseRevealedMine(row, col);
    }

    void animatedScanDisplay(int row, int col){
        Button subButton = buttons[row][col];
        int mineCounter = 0;

        for(int j = 0; j < NUM_COL;j++){
            if(newGame.isMineHere(row, j))
                mineCounter++;

            leftToRightScan = AnimationUtils.loadAnimation(this,R.anim.fadein);
            Button tempButton = buttons[row][j];
            tempButton.setAnimation(leftToRightScan);
        }

        for(int i = 0; i < NUM_ROW; i++){
            if(newGame.isMineHere(i, col))
                mineCounter++;

            upToDownScan = AnimationUtils.loadAnimation(this,R.anim.fadein);
            Button tempButton = buttons[i][col];
            tempButton.setAnimation(upToDownScan);
        }

        subButton.setTextColor(Color.WHITE);
        subButton.setText(getString(R.string.empty) + mineCounter);
    }

    void scanDisplay(int row, int col){

        Button subButton = buttons[row][col];
        int mineCounter = 0;

        for(int j = 0; j < NUM_COL;j++){
            if(newGame.isMineHere(row, j))
                mineCounter++;
        }
        for(int i = 0; i < NUM_ROW; i++){
            if(newGame.isMineHere(i, col))
                mineCounter++;
        }

        subButton.setTextColor(Color.WHITE);
        subButton.setText("" + mineCounter);
    }

    void updateScanDisplay(int row, int col){
        for(int i = 0; i < alreadyScannedCell.size(); i++) {
            int subRow = alreadyScannedCell.get(i).getMineRow();
            int subCol = alreadyScannedCell.get(i).getMineCol();

            if (subRow == row || subCol == col) {
                scanDisplay(subRow,subCol);
            }
        }
    }

    void congratulationDialog(){
        FragmentManager manager = getSupportFragmentManager();
        CongratulationDialogFragment dialog = new CongratulationDialogFragment();
        dialog.show(manager, "Congratulation message!");
    }
}
