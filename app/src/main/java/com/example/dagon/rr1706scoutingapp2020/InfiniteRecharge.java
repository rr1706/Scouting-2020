package com.example.dagon.rr1706scoutingapp2020;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class InfiniteRecharge extends AppCompatActivity {

    /*int[] color1 = { 127, 127, 255 }; //Made this an array because I couldn't figure out how to make a color object
    int[] color2 = { 159, 159, 255 };
    int[] color3 = { 159, 159, 223 };*/ //not needed

    int autoUpperScore = 0;
    int autoLowerScore = 0;
    int teleopUpperScore = 0;
    int teleopLowerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_recharge);

        //Constraints
        final ConstraintLayout AUTO = findViewById(R.id.AUTO);
        final ConstraintLayout TELEOP = findViewById(R.id.TELEOP);
        final ConstraintLayout ENDGAME = findViewById(R.id.ENDGAME);

        //Buttons
        final android.widget.Button blue_team_button = findViewById(R.id.blue_team_button);
        final android.widget.Button red_team_button = findViewById(R.id.red_team_button);

        //ImageViews
        final android.widget.ImageView auto_power_port = findViewById(R.id.auto_power_port);
        final android.widget.ImageView teleop_power_port = findViewById(R.id.teleop_power_port);
        final android.widget.ImageView auto_upper_plus = findViewById(R.id.auto_upper_plus);
        final android.widget.ImageView auto_upper_minus = findViewById(R.id.auto_upper_minus);
        final android.widget.ImageView auto_lower_plus = findViewById(R.id.auto_lower_plus);
        final android.widget.ImageView auto_lower_minus = findViewById(R.id.auto_lower_minus);
        final android.widget.ImageView teleop_upper_plus = findViewById(R.id.teleop_upper_plus);
        final android.widget.ImageView teleop_upper_minus = findViewById(R.id.teleop_upper_minus);
        final android.widget.ImageView teleop_lower_plus = findViewById(R.id.teleop_lower_plus);
        final android.widget.ImageView teleop_lower_minus = findViewById(R.id.teleop_lower_minus);
        final android.widget.ImageView logo = findViewById(R.id.logo);
        final android.widget.ImageView endgame_switch = findViewById(R.id.endgame_switch);

        //TextViews
        final android.widget.TextView auto_upper_text = findViewById(R.id.auto_upper_text);
        final android.widget.TextView auto_lower_text = findViewById(R.id.auto_lower_text);
        final android.widget.TextView teleop_upper_text = findViewById(R.id.teleop_upper_text);
        final android.widget.TextView teleop_lower_text = findViewById(R.id.teleop_lower_text);

        //EditTexts
        final android.widget.EditText team_input = findViewById(R.id.team_input);


        blue_team_button.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                AUTO.setBackgroundColor(android.graphics.Color.argb(255, 143, 143, 255));
                TELEOP.setBackgroundColor(android.graphics.Color.argb(255, 159, 159, 255));
                ENDGAME.setBackgroundColor(android.graphics.Color.argb(255, 127, 127, 247));
                auto_power_port.setImageResource(R.drawable.power_port_blue);
                teleop_power_port.setImageResource(R.drawable.power_port_blue);
                endgame_switch.setImageResource(R.drawable.switch_blue);
            }
        });

        red_team_button.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                AUTO.setBackgroundColor(android.graphics.Color.argb(255, 255, 143, 143));
                TELEOP.setBackgroundColor(android.graphics.Color.argb(255, 255, 159, 159));
                ENDGAME.setBackgroundColor(android.graphics.Color.argb(255, 247, 127, 127));
                auto_power_port.setImageResource(R.drawable.power_port_red);
                teleop_power_port.setImageResource(R.drawable.power_port_red);
                endgame_switch.setImageResource(R.drawable.switch_red);
            }
        });


        auto_upper_plus.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                autoUpperScore++;
                auto_upper_text.setText(Integer.toString(autoUpperScore));
            }
        });
        auto_upper_minus.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                if (autoUpperScore > 0) { autoUpperScore--; }
                auto_upper_text.setText(Integer.toString(autoUpperScore));
            }
        });

        auto_lower_plus.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                autoLowerScore++;
                auto_lower_text.setText(Integer.toString(autoLowerScore));
            }
        });
        auto_lower_minus.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                if (autoLowerScore > 0) { autoLowerScore--; }
                auto_lower_text.setText(Integer.toString(autoLowerScore));
            }
        });


        teleop_upper_plus.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                teleopUpperScore++;
                teleop_upper_text.setText(Integer.toString(teleopUpperScore));
            }
        });
        teleop_upper_minus.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                if (teleopUpperScore > 0) { teleopUpperScore--; }
                teleop_upper_text.setText(Integer.toString(teleopUpperScore));
            }
        });

        teleop_lower_plus.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                teleopLowerScore++;
                teleop_lower_text.setText(Integer.toString(teleopLowerScore));
            }
        });
        teleop_lower_minus.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                if (teleopLowerScore > 0) { teleopLowerScore--; }
                teleop_lower_text.setText(Integer.toString(teleopLowerScore));
            }
        });


        team_input.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            public void onFocusChange(android.view.View view, boolean hasFocus) {
                if (!hasFocus && team_input.getText().toString().equals("1706")) {
                    android.view.animation.RotateAnimation rotateAnimation = new android.view.animation.RotateAnimation(0, 720f,
                            android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f,
                            android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);

                    rotateAnimation.setInterpolator(new android.view.animation.LinearInterpolator());
                    rotateAnimation.setDuration(1000);

                    logo.startAnimation(rotateAnimation);
                }
            }
        });
    }
}
