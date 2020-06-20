package com.example.gamemineseeker.View;

import android.content.Intent;
import android.os.Bundle;

import com.example.gamemineseeker.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int welcomeScreenTime = 12000;
    private Handler handler = new Handler();
    Animation rotate, fadeIn, fadeOut;
    ImageView imageView;
    TextView textView1, textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView2);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(rotate);
        Intent welcomeIntent = new Intent(MainActivity.this, MainMenu.class);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        fadeIn = AnimationUtils.loadAnimation(this,R.anim.fadein);
        textView1.startAnimation(fadeIn);

        fadeOut = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        textView2.startAnimation(fadeOut);

        handler.postDelayed(() -> {
            startActivity(welcomeIntent);
            finish();
        }, welcomeScreenTime);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            if(handler !=null){
                handler.removeCallbacksAndMessages(null);
            }
            startActivity(welcomeIntent);
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
