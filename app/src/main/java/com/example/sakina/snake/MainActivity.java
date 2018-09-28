package com.example.sakina.snake;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.sakina.snake.engine.GameEngine;
import com.example.sakina.snake.enums.Direction;
import com.example.sakina.snake.enums.GameState;
import com.example.sakina.snake.views.SnakeView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private GameEngine gameEngine;
    private SnakeView snakeView;
    private final Handler handler = new Handler();
    private final long updateDelay = 150;

    public int fs=0;

    private float prevX,prevY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEngine = new GameEngine();
        gameEngine.initGame();
        snakeView = (SnakeView)findViewById(R.id.snakeView);
        snakeView.setOnTouchListener(this);
        startUpdateHandler();

    }

    private void startUpdateHandler()
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameEngine.Update();




                if(gameEngine.getCurrentGameState()== GameState.Running){
                    handler.postDelayed(this,updateDelay);

                }

               /* if(gameEngine.getCurrentGameState()==GameState.Lost)
                {
                    OnGameLost();
                }
               */

               if(gameEngine.getCurrentGameState()==GameState.Lost)
               {
                   fs = gameEngine.score;
                   Log.d("SCORE:", String.valueOf(fs));
                   OnGameLost();

               }
               snakeView.setSnakeViewMap(gameEngine.getMap());
                snakeView.invalidate();
            }
        },updateDelay);
    }

    private void OnGameLost()
    {

        Toast.makeText(this,"You have Lost the Game",Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(),Score.class);
        startActivity(i);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                prevX = event.getX();
                prevY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                float newX = event.getX();
                float newY = event.getY();

                // calculate where we swiped

                if(Math.abs(newX-prevX)>Math.abs(newY-prevY))
                {
                    // Left - Right Direction
                    if(newX > prevX)
                    {
                        // Right
                        gameEngine.UpdateDirection(Direction.East);
                    } else
                    {
                        // Left
                        gameEngine.UpdateDirection(Direction.West);
                    }
                }

                else
                {
                    // Up - Down Direction
                    if(newY>prevY)
                    {
                        // Up
                        gameEngine.UpdateDirection(Direction.South);
                    } else
                    {
                        // Down
                        gameEngine.UpdateDirection(Direction.North);
                    }


                }

                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gameEngine.score = 0;
        finish();
    }


}

