package com.example.dagon.rr1706scoutingapp2020;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class InfiniteRecharge extends AppCompatActivity {

    /*int[] color1 = { 127, 127, 255 }; //Made this an array because I couldn't figure out how to make a color object
    int[] color2 = { 159, 159, 255 };
    int[] color3 = { 159, 159, 223 };*/ //not needed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_recharge);

        final ConstraintLayout AUTO = findViewById(R.id.AUTO);
        final ConstraintLayout TELEOP = findViewById(R.id.TELEOP);
        final ConstraintLayout ENDGAME = findViewById(R.id.ENDGAME);

        final android.widget.Button blueButton = findViewById(R.id.blue_team_button);
        blueButton.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                AUTO.setBackgroundColor(android.graphics.Color.argb(255, 143, 143, 223));
                TELEOP.setBackgroundColor(android.graphics.Color.argb(255, 159, 159, 255));
                ENDGAME.setBackgroundColor(android.graphics.Color.argb(255, 127, 127, 247));
            }
        });

        final android.widget.Button redButton = findViewById(R.id.red_team_button);
        redButton.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                AUTO.setBackgroundColor(android.graphics.Color.argb(255, 223, 143, 143));
                TELEOP.setBackgroundColor(android.graphics.Color.argb(255, 255, 159, 159));
                ENDGAME.setBackgroundColor(android.graphics.Color.argb(255, 247, 127, 127));
            }
        });
    }
}
