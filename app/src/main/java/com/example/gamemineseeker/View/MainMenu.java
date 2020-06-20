package com.example.gamemineseeker.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.gamemineseeker.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(view ->{
            Intent gamePlayIntent = new Intent(MainMenu.this, gamePlay.class);
            startActivity(gamePlayIntent);
            finish();
        });

        /*Button btn2 = findViewById(R.id.button3);
        btn2.setOnClickListener(view ->{
            Intent optionsIntent = new Intent(MainMenu.this, optionsScreen.class);
            startActivity(optionsIntent);
            finish();
        });

        Button btn3 = findViewById(R.id.button4);
        btn3.setOnClickListener(view ->{
            Intent helpIntent = new Intent(MainMenu.this, helpScreen.class);
            startActivity(helpIntent);
            finish();
        });*/
    }

}
