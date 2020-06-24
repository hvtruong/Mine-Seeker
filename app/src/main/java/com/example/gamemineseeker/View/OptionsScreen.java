package com.example.gamemineseeker.View;

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

    GamePlayOptions newGame = GamePlayOptions.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);

        createRadioButtons();

        Button btn = findViewById(R.id.button5);
        btn.setOnClickListener(view ->{
            finish();
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
            radioButton.setText(getString(R.string.empty) + rowContent);

            radioButton.setOnClickListener(view ->{
                newGame.setNumRow(rowContent);
            });

            radioRowGroup.addView(radioButton);
        }

        for(int i = 0; i < num_col.length;i++){
            int colContent = num_col[i];

            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(getString(R.string.empty) + colContent);

            radioButton.setOnClickListener(view ->{
                newGame.setNumCol(colContent);
            });

            radioColGroup.addView(radioButton);
        }

        for(int i = 0; i < num_mine.length;i++){
            int mineContent = num_mine[i];

            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(getString(R.string.empty) + mineContent);

            radioButton.setOnClickListener(view ->{
                newGame.setNumMine(mineContent);
            });

            radioMineGroup.addView(radioButton);
        }
    }
}
