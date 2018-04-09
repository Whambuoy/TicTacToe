package com.example.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TwoPlayer3By3 extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];
    private boolean turnOfPlayer1 = true;
    private int numberOfTurns;
    private int player1Score;
    private int player2Score;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player3_by3);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonId = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetScores();
            }
        });
    }

    @Override
    public void onClick(View view) {
        //To check if there is an empty string
        if(!((Button) view).getText().toString().equals("")){
            return;
        }
        if (turnOfPlayer1){
            ((Button )view).setText("X");
        } else {
            ((Button )view).setText("O");
        }
        numberOfTurns++;
        if (CheckWin()){
            if(turnOfPlayer1) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (numberOfTurns == 9){
            draw();
        } else {
            turnOfPlayer1 = !turnOfPlayer1;
        }

    }
    //Check if someone has won
    private boolean CheckWin(){
        String[][] field = new String[3][3];
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for(int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }

        for(int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }
    private void player1Wins(){
     player1Score++;
     Toast.makeText(this, "Player 1 won!!", Toast.LENGTH_SHORT).show();
     pointsUpdate();
     resetGame();
    }

    private void player2Wins(){
        player2Score++;
        Toast.makeText(this, "Player 2 won!!", Toast.LENGTH_SHORT).show();
        pointsUpdate();
        resetGame();
    }

    private void draw(){
        Toast.makeText(this, "It is a draw!!", Toast.LENGTH_SHORT).show();
        resetGame();

    }

    private void pointsUpdate(){
    textViewPlayer1.setText("Player 1: " + player1Score);
    textViewPlayer2.setText("Player 2: " + player2Score);

    }

    private void resetGame(){
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
            buttons[i][j].setText("");
        }
    }

    numberOfTurns = 0;
    turnOfPlayer1 = true;
    }

    public void resetScores(){
        player1Score = 0;
        player2Score = 0;
        pointsUpdate();
        resetGame();
    }
    //To save our variables
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("numberOfTurns", numberOfTurns);
        outState.putInt("player1Score", player1Score);
        outState.putInt("player2Score", player2Score);
        outState.putBoolean("turnOfPlayer1", turnOfPlayer1);
    }
    //Restore game after a screen rotation
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numberOfTurns = savedInstanceState.getInt("numberOfTurns");
        player1Score = savedInstanceState.getInt("player1Score");
        player2Score = savedInstanceState.getInt("player2Score");
        turnOfPlayer1 = savedInstanceState.getBoolean("turnOfPlayer1");
    }
}
