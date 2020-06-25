package com.example.gamemineseeker.View;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.gamemineseeker.Model.GamePlayOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.gamemineseeker.R;

public class OptionsScreen extends AppCompatActivity {
    public static final String SHARED_PREF = "sharedPrefs";
    public static final String numRow = "numRow";
    public static final String numCol = "numCol";
    public static final String numMine = "numMine";
    public static final String numGamePlayed = "numGamePlayed";

    GamePlayOptions newGame = GamePlayOptions.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);

        createRadioButtons();

        Button saveData = findViewById(R.id.button5);
        saveData.setOnClickListener(view ->{
            saveData();
            finish();
        });

        Button eraseTimesPlayed = findViewById(R.id.button7);
        eraseTimesPlayed.setOnClickListener(view ->{
            newGame.setTotalGame(0);
            saveData();
        });
    }

    public void createRadioButtons(){
        RadioGroup radioRowGroup = (RadioGroup) findViewById(R.id.radio_group_row);
        RadioGroup radioColGroup = (RadioGroup) findViewById(R.id.radio_group_col);
        RadioGroup radioMineGroup = (RadioGroup) findViewById(R.id.radio_group_mines);
        int[] num_row = getResources().getIntArray(R.array.num_row);
        int[] num_col = getResources().getIntArray(R.array.num_col);
        int[] num_mine = getResources().getIntArray(R.array.num_mine);

        for(int i = 0; i < num_row.length;i++){
            int rowContent = num_row[i];

            RadioButton radioButton = new RadioButton(this);
            radioButton.setTextColor(Color.WHITE);
            radioButton.setText(getString(R.string.empty) + rowContent);

            radioButton.setOnClickListener(view ->{
                newGame.setNumRow(rowContent);
            });

            radioRowGroup.addView(radioButton);
        }

        for(int i = 0; i < num_col.length;i++){
            int colContent = num_col[i];

            RadioButton radioButton = new RadioButton(this);
            radioButton.setTextColor(Color.WHITE);
            radioButton.setText(getString(R.string.empty) + colContent);

            radioButton.setOnClickListener(view ->{
                newGame.setNumCol(colContent);
            });

            radioColGroup.addView(radioButton);
        }

        for(int i = 0; i < num_mine.length;i++){
            int mineContent = num_mine[i];

            RadioButton radioButton = new RadioButton(this);
            radioButton.setTextColor(Color.WHITE);
            radioButton.setText(getString(R.string.empty) + mineContent);

            radioButton.setOnClickListener(view ->{
                newGame.setNumMine(mineContent);
            });

            radioMineGroup.addView(radioButton);
        }
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(numRow, newGame.getNumRow());
        editor.putInt(numCol, newGame.getNumCol());
        editor.putInt(numMine, newGame.getNumMine());
        editor.putInt(numGamePlayed, newGame.getTotalGame());

        editor.apply();
    }
}
