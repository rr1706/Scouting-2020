package com.example.dagon.rr1706scoutingapp2020;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;


public class InfiniteRecharge extends AppCompatActivity {
    int autoUpperScore = 0;
    int autoLowerScore = 0;
    int teleopUpperScore = 0;
    int teleopLowerScore = 0;
    int spin = 1;
    int ds_cooldown = 0; //ds_cooldown is the cool down for the data_submitted animation
    int rot_ctrl = 0;
    char alliance = 'n'; //b - Blue, r - Red, n - None


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_recharge);

        //Constraints
        final ConstraintLayout GENERAL_TOP = findViewById(R.id.GENERAL_TOP);
        final ConstraintLayout AUTO = findViewById(R.id.AUTO);
        final ConstraintLayout TELEOP = findViewById(R.id.TELEOP);
        final ConstraintLayout ENDGAME = findViewById(R.id.ENDGAME);
        final ConstraintLayout GENERAL_BOTTOM = findViewById(R.id.GENERAL_BOTTOM);

        //Buttons
        final Button blue_team_button = findViewById(R.id.blue_team_button);
        final Button red_team_button = findViewById(R.id.red_team_button);
        final Button submit = findViewById(R.id.submit);

        //ImageViews
        final ImageView auto_power_port = findViewById(R.id.auto_power_port);
        final ImageView teleop_power_port = findViewById(R.id.teleop_power_port);
        final ImageView auto_upper_plus = findViewById(R.id.auto_upper_plus);
        final ImageView auto_upper_minus = findViewById(R.id.auto_upper_minus);
        final ImageView auto_lower_plus = findViewById(R.id.auto_lower_plus);
        final ImageView auto_lower_minus = findViewById(R.id.auto_lower_minus);
        final ImageView teleop_upper_plus = findViewById(R.id.teleop_upper_plus);
        final ImageView teleop_upper_minus = findViewById(R.id.teleop_upper_minus);
        final ImageView teleop_lower_plus = findViewById(R.id.teleop_lower_plus);
        final ImageView teleop_lower_minus = findViewById(R.id.teleop_lower_minus);
        final ImageView logo = findViewById(R.id.logo);
        final ImageView endgame_switch_graphic = findViewById((R.id.endgame_switch_graphic));
        final ImageView data_submitted = findViewById(R.id.data_submitted);

        //TextViews
        final TextView auto_upper_text = findViewById(R.id.auto_upper_text);
        final TextView auto_lower_text = findViewById(R.id.auto_lower_text);
        final TextView teleop_upper_text = findViewById(R.id.teleop_upper_text);
        final TextView teleop_lower_text = findViewById(R.id.teleop_lower_text);

        //EditTexts
        final EditText name_input = findViewById(R.id.name_input);
        final EditText team_input = findViewById(R.id.team_input);
        final EditText round_input = findViewById(R.id.round_input);

        //CheckBoxes
        final CheckBox endgame_in_boundary = findViewById(R.id.endgame_in_boundary);
        final CheckBox endgame_hanging = findViewById(R.id.endgame_hanging);
        final CheckBox endgame_balanced = findViewById(R.id.endgame_balanced);
        final CheckBox teleop_rot_ctrl_1 = findViewById(R.id.teleop_rot_ctrl_1);
        final CheckBox teleop_rot_ctrl_2 = findViewById(R.id.teleop_rot_ctrl_2);
        final CheckBox auto_no_auto = findViewById(R.id.auto_no_auto);
        final CheckBox auto_pass_init_line = findViewById(R.id.auto_pass_init_line);

        //Spinners
        final Spinner logo_spinner = findViewById(R.id.logo_spinner);
        final Spinner speed = findViewById(R.id.speed);
        final Spinner endgame_results = findViewById(R.id.endgame_results);
        final Spinner endgame_generator = findViewById(R.id.endgame_generator);

        //Set invisible/visible elements
        endgame_hanging.setAlpha(1); endgame_hanging.setVisibility(View.GONE);
        endgame_balanced.setAlpha(1); endgame_balanced.setVisibility(View.GONE);
        teleop_rot_ctrl_2.setAlpha(1); teleop_rot_ctrl_2.setVisibility(View.GONE);
        data_submitted.setVisibility(View.VISIBLE);

        //The great while loop (100/sec)
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                while (0 == 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    data_submitted.post(new Runnable() {
                        @Override
                        public void run() {
                            if (ds_cooldown > 0) { ds_cooldown--; }

                            if (ds_cooldown >= 100) { data_submitted.setAlpha(255); }
                            else { data_submitted.setAlpha(255*ds_cooldown/100); }
                        }
                    });
                }
            }
        };
        Thread myThread = new Thread(myRunnable);
        myThread.start();

        blue_team_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alliance = 'b';

                AUTO.setBackgroundColor(Color.argb(255, 143, 143, 255));
                TELEOP.setBackgroundColor(Color.argb(255, 159, 159, 255));
                ENDGAME.setBackgroundColor(Color.argb(255, 127, 127, 247));
                auto_power_port.setImageResource(R.drawable.power_port_blue);
                teleop_power_port.setImageResource(R.drawable.power_port_blue);
                GENERAL_TOP.setBackgroundColor(Color.argb(255, 223, 223, 255));
                GENERAL_BOTTOM.setBackgroundColor(Color.argb(255, 223, 223, 255));
            }
        });

        red_team_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alliance = 'r';

                AUTO.setBackgroundColor(Color.argb(255, 255, 143, 143));
                TELEOP.setBackgroundColor(Color.argb(255, 255, 159, 159));
                ENDGAME.setBackgroundColor(Color.argb(255, 247, 127, 127));
                auto_power_port.setImageResource(R.drawable.power_port_red);
                teleop_power_port.setImageResource(R.drawable.power_port_red);
                GENERAL_TOP.setBackgroundColor(Color.argb(255, 255, 223, 223));
                GENERAL_BOTTOM.setBackgroundColor(Color.argb(255, 255, 223, 223));
            }
        });


        auto_upper_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoUpperScore++;
                auto_upper_text.setText(Integer.toString(autoUpperScore));
            }
        });
        auto_upper_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoUpperScore > 0) { autoUpperScore--; }
                auto_upper_text.setText(Integer.toString(autoUpperScore));
            }
        });

        auto_lower_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoLowerScore++;
                auto_lower_text.setText(Integer.toString(autoLowerScore));
            }
        });
        auto_lower_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoLowerScore > 0) { autoLowerScore--; }
                auto_lower_text.setText(Integer.toString(autoLowerScore));
            }
        });


        teleop_upper_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teleopUpperScore < 99) { teleopUpperScore++; }
                teleop_upper_text.setText(Integer.toString(teleopUpperScore));
            }
        });
        teleop_upper_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teleopUpperScore > 0) { teleopUpperScore--; }
                teleop_upper_text.setText(Integer.toString(teleopUpperScore));
            }
        });

        teleop_lower_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teleopLowerScore < 99) { teleopLowerScore++; }
                teleop_lower_text.setText(Integer.toString(teleopLowerScore));
            }
        });
        teleop_lower_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teleopLowerScore > 0) { teleopLowerScore--; }
                teleop_lower_text.setText(Integer.toString(teleopLowerScore));
            }
        });


        auto_no_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Disable fields, reset fields & vars.
                if (isChecked) {
                    autoUpperScore = 0;
                    autoLowerScore = 0;

                    auto_upper_text.setText("0");
                    auto_lower_text.setText("0");
                    auto_pass_init_line.setChecked(false);

                    auto_upper_text.setAlpha((float) 0.5);
                    auto_lower_text.setAlpha((float) 0.5);
                    auto_upper_plus.setAlpha((float) 0.5);
                    auto_upper_minus.setAlpha((float) 0.5);
                    auto_lower_plus.setAlpha((float) 0.5);
                    auto_lower_minus.setAlpha((float) 0.5);
                    auto_power_port.setAlpha((float) 0.5);
                    auto_pass_init_line.setAlpha((float) 0.5);

                    auto_upper_text.setEnabled(false);
                    auto_lower_text.setEnabled(false);
                    auto_upper_plus.setEnabled(false);
                    auto_upper_minus.setEnabled(false);
                    auto_lower_plus.setEnabled(false);
                    auto_lower_minus.setEnabled(false);
                    auto_power_port.setEnabled(false);
                    auto_pass_init_line.setEnabled(false);
                } else {
                    auto_upper_text.setAlpha((float) 1);
                    auto_lower_text.setAlpha((float) 1);
                    auto_upper_plus.setAlpha((float) 1);
                    auto_upper_minus.setAlpha((float) 1);
                    auto_lower_plus.setAlpha((float) 1);
                    auto_lower_minus.setAlpha((float) 1);
                    auto_power_port.setAlpha((float) 1);
                    auto_pass_init_line.setAlpha((float) 1);

                    auto_upper_text.setEnabled(true);
                    auto_lower_text.setEnabled(true);
                    auto_upper_plus.setEnabled(true);
                    auto_upper_minus.setEnabled(true);
                    auto_lower_plus.setEnabled(true);
                    auto_lower_minus.setEnabled(true);
                    auto_power_port.setEnabled(true);
                    auto_pass_init_line.setEnabled(true);
                }

            }
        });

        teleop_rot_ctrl_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    teleop_rot_ctrl_2.setVisibility(View.VISIBLE);
                } else {
                    teleop_rot_ctrl_2.setVisibility(View.GONE);;
                }
            }
        });

        team_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (team_input.getText().toString().equals("1706") && spin == 1) {
                    RotateAnimation rotateAnimation = new RotateAnimation(0, 720f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);

                    rotateAnimation.setInterpolator(new LinearInterpolator());
                    rotateAnimation.setDuration(1000*spin);

                    logo.startAnimation(rotateAnimation);
                } else if (!team_input.getText().toString().equals("1706")) { spin = 1; }

                return false; //Idk it wants a boolean
            }
        });


        endgame_in_boundary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    endgame_hanging.setVisibility(View.VISIBLE);
                    endgame_hanging.setVisibility(View.VISIBLE);
                    endgame_switch_graphic.setImageResource(R.drawable.switch_1_blue);
                } else {
                    endgame_hanging.setChecked(false);
                    endgame_hanging.setVisibility(View.GONE);
                    endgame_balanced.setChecked(false);
                    endgame_balanced.setVisibility(View.GONE);
                    endgame_switch_graphic.setImageResource(R.drawable.switch_0_blue);
                }
            }
        });

        endgame_hanging.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    endgame_balanced.setVisibility(View.VISIBLE);
                    endgame_switch_graphic.setImageResource(R.drawable.switch_2_blue);
                } else {
                    endgame_balanced.setChecked(false);
                    endgame_balanced.setVisibility(View.GONE);
                    endgame_switch_graphic.setImageResource(R.drawable.switch_1_blue);
                }
            }
        });

        endgame_balanced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    endgame_switch_graphic.setImageResource(R.drawable.switch_3_blue);
                } else {
                    endgame_switch_graphic.setImageResource(R.drawable.switch_2_blue);
                }
            }
        });


        logo_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (logo_spinner.getSelectedItem().toString().equals("Team 1706")) {
                    logo.setImageResource(R.drawable.ratchet_rockers_logo);
                } else if (logo_spinner.getSelectedItem().toString().equals("Team 8069")) {
                    logo.setImageResource(R.drawable.super_hornets_logo);
                } else if (logo_spinner.getSelectedItem().toString().equals("Team 4329")) {
                    logo.setImageResource(R.drawable.lutheran_roboteers_logo);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String submitError = "";
                String robotErrors = "";
                String genPos;
                SimpleDateFormat time = new SimpleDateFormat("dd-HHmmss", Locale.getDefault());
                int team;
                int round;
                String newTeam;

                //Special handling
                if (team_input.getText().toString().equals("")) { team = -1; }
                else { team = Integer.parseInt(team_input.getText().toString()); }

                if (round_input.getText().toString().equals("")) { round = -1; }
                else { round = Integer.parseInt(round_input.getText().toString()); }

                if (alliance == 'n') { submitError += " No Alliance,"; }
                if (name_input.getText().toString().equals("")) { submitError += " No Name,"; }
                if (speed.getSelectedItem().toString().equals("Speed")) { submitError += " No Speed,"; }
                if (endgame_results.getSelectedItem().toString().equals("Results")) { submitError += " No Results,"; }
                if (endgame_generator.getSelectedItem().toString().equals("Generator Level")) { submitError += " No Generator Lvl,"; }
                if (team == -1) { submitError += " No Team#,"; }
                if (round == -1) { submitError += " No Round#,"; }
                if (!submitError.equals("")) { submitError = submitError.substring(0,submitError.length()-1)+"."; }

                if (!(submitError.equals(""))) {
                    Toast.makeText(getApplicationContext(), "Submit Error:"+submitError, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Scouting Data Submitted!", Toast.LENGTH_SHORT);
                    ds_cooldown = 150; //Makes the check mark appear

                    //Save data for transfer
                    File dir = getDataDirectory();

                    try {
                        File myFile = new File(dir, team + "_" + round + "_" + time.format(new Date()) + ".txt");
                        FileOutputStream fOut = new FileOutputStream(myFile, true);
                        PrintWriter myOutWriter = new PrintWriter(new OutputStreamWriter(fOut));

                        myOutWriter.println("Scouter: "+name_input.getText());
                        myOutWriter.println("Team: "+team);
                        myOutWriter.println("Timestamp: "+time.format(new Date()));
                        myOutWriter.println("Match: "+round);
                        myOutWriter.println("Alliance: "+alliance);
                        myOutWriter.println("Speed: "+speed.getSelectedItem().toString());
                        //Robot errors handling
                        if (((CheckBox) findViewById(R.id.lost_parts)).isChecked()) { robotErrors += "lost parts, "; }
                        if (((CheckBox) findViewById(R.id.fell_over)).isChecked()) { robotErrors += "fell over, "; }
                        if (((CheckBox) findViewById(R.id.communication_issues)).isChecked()) { robotErrors += "comm issues, "; }
                        if (((CheckBox) findViewById(R.id.broke_down)).isChecked()) { robotErrors += "broke down, "; }
                        if (robotErrors.equals("")) { robotErrors = "None"; }
                        myOutWriter.println("Robot Errors: "+robotErrors);
                        myOutWriter.println("Auto Top Score: "+autoUpperScore);
                        myOutWriter.println("Auto Bottom Score: "+autoLowerScore);
                        myOutWriter.println("No Auto: "+((CheckBox) findViewById(R.id.auto_no_auto)).isChecked());
                        myOutWriter.println("Passed Init Line: "+((CheckBox) findViewById(R.id.auto_pass_init_line)).isChecked());
                        myOutWriter.println("Teleop Top Score: "+teleopUpperScore);
                        myOutWriter.println("Teleop Bottom Score: "+teleopLowerScore);
                        //Rotation control handling
                        if (((CheckBox) findViewById(R.id.teleop_rot_ctrl_2)).isChecked()) { rot_ctrl = 2; }
                        else if (((CheckBox) findViewById(R.id.teleop_rot_ctrl_1)).isChecked()) { rot_ctrl = 1; }
                        else { rot_ctrl = 0; }
                        myOutWriter.println("Rotation Control: "+rot_ctrl);
                        //Generator position handling
                        if (((CheckBox) findViewById(R.id.endgame_balanced)).isChecked()) { genPos = "Balanced"; }
                        else if (((CheckBox) findViewById(R.id.endgame_hanging)).isChecked()) { genPos = "Hanging"; }
                        else if (((CheckBox) findViewById(R.id.endgame_in_boundary)).isChecked()) { genPos = "In Boundary"; }
                        else { genPos = "No Points"; }
                        myOutWriter.println("Endgame: "+genPos);
                        myOutWriter.println("Results: "+((Spinner) findViewById(R.id.endgame_results)).getSelectedItem());
                        myOutWriter.println("Generator Level: "+((Spinner) findViewById(R.id.endgame_generator)).getSelectedItem());
                        myOutWriter.println("Notes: "+((EditText) findViewById(R.id.notes)).getText());

                        myOutWriter.flush();
                        myOutWriter.close();
                        fOut.close();

                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        Log.e("Exception", "File write failed: " + e.toString());
                    }

                    //Reset vars
                    round++;
                    teleopLowerScore = 0;
                    teleopUpperScore = 0;
                    autoLowerScore = 0;
                    autoUpperScore = 0;

                    try {
                        newTeam = getTeams().substring(
                                getTeams().indexOf(round + ":") + 2, //Start
                                getTeams().substring(getTeams().indexOf(round + ":")).indexOf(";") + getTeams().indexOf(round + ":") //End
                        );
                    } catch(Exception e) {
                        newTeam = "";
                    }

                    //Reset fields
                    team_input.setText(newTeam);
                    round_input.setText(""+round);
                    teleop_lower_text.setText("0");
                    teleop_upper_text.setText("0");
                    auto_lower_text.setText("0");
                    auto_upper_text.setText("0");
                    ((EditText) findViewById(R.id.notes)).setText("");
                    ((Spinner) findViewById(R.id.speed)).setSelection(0);
                    ((Spinner) findViewById(R.id.endgame_results)).setSelection(0);
                    ((Spinner) findViewById(R.id.endgame_generator)).setSelection(0);
                    ((CheckBox) findViewById(R.id.lost_parts)).setChecked(false);
                    ((CheckBox) findViewById(R.id.communication_issues)).setChecked(false);
                    ((CheckBox) findViewById(R.id.broke_down)).setChecked(false);
                    ((CheckBox) findViewById(R.id.fell_over)).setChecked(false);
                    ((CheckBox) findViewById(R.id.auto_no_auto)).setChecked(false);
                    ((CheckBox) findViewById(R.id.auto_pass_init_line)).setChecked(false);
                    ((CheckBox) findViewById(R.id.teleop_rot_ctrl_1)).setChecked(false);
                    ((CheckBox) findViewById(R.id.teleop_rot_ctrl_2)).setChecked(false);
                    ((CheckBox) findViewById(R.id.endgame_in_boundary)).setChecked(false);
                    ((CheckBox) findViewById(R.id.endgame_hanging)).setChecked(false);
                    ((CheckBox) findViewById(R.id.endgame_balanced)).setChecked(false);
                }
            }
        });
    }

    private File getDataDirectory() {
        File directory = Environment.getExternalStorageDirectory();
        File myDir = new File(directory + "/ScoutingData");
        myDir.mkdirs();
        return myDir;
    }

    private String getTeams() {
        String text = "";
        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard + "/Documents/ScoutingTeams.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text += line + ";";
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("log", text);
        return text;
    }
}
