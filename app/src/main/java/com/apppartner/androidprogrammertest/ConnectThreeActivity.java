package com.apppartner.androidprogrammertest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ConnectThreeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_three);
    }

    int activePlayer = 0;

    boolean gameIsActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPosiions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {0, 4, 8}};


    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);


            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.ic_apppartner);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.ic_apppartner_inverted);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(600);

            for (int[] winningPosition : winningPosiions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    final LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    playAgainLayout.setVisibility(View.VISIBLE);

                    gameIsActive = false;

                    TextView winner = (TextView) findViewById(R.id.winnerMessage);
                    if (activePlayer == 1) {
                        winner.setText(R.string.winner_original);
                    } else {
                        winner.setText(R.string.winner_invert);
                    }
                } else {
                    boolean gamIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gamIsOver = false;
                    }
                    if (gamIsOver) {
                        final LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        playAgainLayout.setVisibility(View.VISIBLE);
                        TextView winner = (TextView) findViewById(R.id.winnerMessage);

                        winner.setText(R.string.draw);

                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
