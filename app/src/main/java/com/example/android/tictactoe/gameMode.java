package com.example.android.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gameMode extends AppCompatActivity implements View.OnClickListener{

    private Button changeToTwoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        changeToTwoPlayer = (Button) findViewById(R.id.player_2);

        changeToTwoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open2Player();
            }
        });


    }
    public void open2Player() {
        Intent intent = new Intent(this, TwoPlayer3By3.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {


    }
}
