package com.example.sagar.iot;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView tvWater;
    CardView waterLevel,securityStatus,waterQuality,waterPump,waterAlarm,waterTutorial,waterReports;
    Typeface myFont;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    TextView tvUserName;
    Button btnLogout;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWater = (TextView) findViewById(R.id.tvWater);
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/KaushanScript-Regular.otf");
        tvWater.setTypeface(myFont);
        
        waterLevel = (CardView) findViewById(R.id.waterLevel);
        waterQuality = (CardView) findViewById(R.id.waterQuality);
        waterAlarm = (CardView) findViewById(R.id.waterAlarm);
        waterPump = (CardView) findViewById(R.id.waterPump);
        waterTutorial = (CardView) findViewById(R.id.waterTutorial);
        waterReports = (CardView) findViewById(R.id.waterReports);
        securityStatus = (CardView) findViewById(R.id.securityStatus);

        waterLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, WaterLevelActivity.class);
                startActivity(i);
            }
        });

        waterQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, WaterQualityActivity.class);
                startActivity(i);
            }
        });

        waterAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(i);
            }
        });

        waterQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, WaterQualityActivity.class);
                startActivity(i);
            }
        });

        waterPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PumpActivity.class);
                startActivity(i);
            }
        });

        waterTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TutorialActivity.class);
                startActivity(i);
            }
        });

        waterReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(i);
            }
        });

        securityStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SecurityActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder bulider = new AlertDialog.Builder(this);
        bulider.setMessage("Are You sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alert = bulider.create();
        alert.setTitle("Exit");
        alert.show();

    }
}

