package com.example.sakina.snake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sakina.snake.engine.GameEngine;

public class Score extends AppCompatActivity {

    TextView t1,t2;
    int highScore;
    private GameEngine gameEngine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
         gameEngine = new GameEngine();

         t1 = (TextView)findViewById(R.id.text1);
        t2 = (TextView)findViewById(R.id.text2);
        int score = gameEngine.score;
        t1.setText("Score :"+score);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScore = settings.getInt("High_Score",0);

        Log.e("SNAKE","SCR :"+score);
        Log.e("SNAKE","HIGH :"+highScore);

        if(score>highScore)
        {
            t2.setText("High Score :"+score);
            //Save
            SharedPreferences.Editor editor = settings.edit();
            //SharedPreferences.Editor editor = getSharedPreferences("GAME_DATA", MODE_PRIVATE).edit();
            editor.putInt("High_Score",score);
            editor.commit();
            //editor.apply();
        }

        else {
            t2.setText("High Score :" + highScore);
        }
    }

    public void onclick(View v)
    {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        gameEngine.score = 0;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        gameEngine.score = 0;
       
    }




}
