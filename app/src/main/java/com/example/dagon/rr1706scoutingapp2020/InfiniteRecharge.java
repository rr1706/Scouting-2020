package com.example.dagon.rr1706scoutingapp2020;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InfiniteRecharge extends AppCompatActivity {

    int[] color1 = { 127, 127, 255 }; //Made this an array because I couldn't figure out how to make a color object
    int[] color2 = { 159, 159, 255 };
    int[] color3 = { 159, 159, 223 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_recharge);

        //public void update_colors() {
        //    System.out.print("hi");
        //}

        ConstraintLayout AUTO = findViewById(R.id.AUTO);
        ConstraintLayout TELEOP = findViewById(R.id.TELEOP);
        ConstraintLayout ENDGAME = findViewById(R.id.ENDGAME);

        AUTO.setBackgroundColor(android.graphics.Color.argb(255, color1[0], color1[1], color1[2]));
        TELEOP.setBackgroundColor(android.graphics.Color.argb(255, color2[0], color2[1], color2[2]));
        ENDGAME.setBackgroundColor(android.graphics.Color.argb(255, color2[0], color2[1], color2[2]));

        final android.widget.Button button = findViewById(R.id.blue_team_button);
        button.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                System.out.println("h");
            }
        });
    }
}
