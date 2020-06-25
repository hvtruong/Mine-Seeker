package com.example.gamemineseeker.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.gamemineseeker.Model.GamePlayOptions;
import com.example.gamemineseeker.Model.gamePlay;
import com.example.gamemineseeker.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    private GamePlayOptions newGame = GamePlayOptions.getInstance();
    public static final String SHARED_PREF = "sharedPrefs";
    public static final String numRow = "numRow";
    public static final String numCol = "numCol";
    public static final String numMine = "numMine";

    private int numberOfRowSaved, numberOfColSaved, numberOfMineSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        loadData();

        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(view ->{
            Intent gamePlayIntent = new Intent(MainMenu.this, gamePlay.class);
            startActivity(gamePlayIntent);
        });

        Button btn2 = findViewById(R.id.button3);
        btn2.setOnClickListener(view ->{
            Intent optionsIntent = new Intent(MainMenu.this, OptionsScreen.class);
            startActivity(optionsIntent);

        });

        Button btn3 = findViewById(R.id.button4);
        btn3.setOnClickListener(view ->{
            Intent helpIntent = new Intent(MainMenu.this, HelpScreen.class);
            startActivity(helpIntent);
        });
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        numberOfRowSaved = sharedPreferences.getInt(numRow, 4);
        numberOfColSaved = sharedPreferences.getInt(numCol, 7);
        numberOfMineSaved = sharedPreferences.getInt(numMine, 8);

        newGame.setNumRow(numberOfRowSaved);
        newGame.setNumCol(numberOfColSaved);
        newGame.setNumMine(numberOfMineSaved);
    }
}
