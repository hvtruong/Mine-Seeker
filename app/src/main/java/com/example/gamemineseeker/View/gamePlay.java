package com.example.gamemineseeker.View;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.gamemineseeker.R;

public class gamePlay extends AppCompatActivity {

    private static final int NUM_ROW = 2;
    private static final int NUM_COL = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        populateGamePlay();
    }

    private void populateGamePlay(){
        TableLayout table = (TableLayout) findViewById(R.id.tableForGamePlay);

        for(int row = 0; row < NUM_ROW; row++) {
            TableRow tableRow = new TableRow(this);
            table.addView(tableRow);

            for (int col = 0; col < NUM_COL; col++) {
                Button button = new Button(this);
                tableRow.addView(button);
            }
        }
    }
}
