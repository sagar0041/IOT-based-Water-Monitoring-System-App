package com.example.sagar.iot;

import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.widget.SeekBar;
import android.*;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;

import me.itangqi.waveloadingview.WaveLoadingView;

public class WaterLevelActivity extends AppCompatActivity {

    WaveLoadingView waveLoadingView;
    SeekBar seekBar;

    private DatabaseReference mDatabase;
    private FirebaseAuth mauth;
    FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
    DatabaseReference mRootReference = firebaseDatabase.getReference();
    DatabaseReference mSeekBar = mRootReference.child("mSeekBar").child("WaterLevel");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_level);


        seekBar = (SeekBar) findViewById(R.id.seekBar);
        waveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        waveLoadingView.setProgressValue(0);

  //        seekBar.setMax(100);
//        seekBar.setMin(0);
//        mWaterLevel.setValue("");
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                waveLoadingView.setProgressValue();
//                if (i < 50) {
//                    waveLoadingView.setBottomTitle(String.format("%d%%", i));
//                    waveLoadingView.setCenterTitle("");
//                    waveLoadingView.setTopTitle("");
//                } else if (i < 80) {
//                    waveLoadingView.setBottomTitle("");
//                    waveLoadingView.setCenterTitle(String.format("%d%%", i));
//                    waveLoadingView.setTopTitle("");
//                } else {
//                    waveLoadingView.setBottomTitle("");
//                    waveLoadingView.setCenterTitle("");
//                    waveLoadingView.setTopTitle(String.format("%d%%", i));
//                }
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

    }


    @Override
    protected void onStart() {
         super.onStart();

        mSeekBar.addValueEventListener(new ValueEventListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 final Integer water = dataSnapshot.getValue(Integer.class);

                 seekBar.setMax(100);
                 seekBar.setMin(0);
                 seekBar.setProgress(water);
                 waveLoadingView.setProgressValue(water);
                 waveLoadingView.setCenterTitle(String.format("%d%%", water));
                 seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                     @Override
                     public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                         waveLoadingView.setProgressValue(water);

                         if (water < 50) {
                             waveLoadingView.setCenterTitle("");
                             waveLoadingView.setTopTitle("");
                             Toast.makeText(WaterLevelActivity.this, "Water Level is low", Toast.LENGTH_SHORT).show();
                         } else if (water < 80) {
                             waveLoadingView.setBottomTitle("");
                             waveLoadingView.setTopTitle("");
                             Toast.makeText(WaterLevelActivity.this, "Water Level is Medium", Toast.LENGTH_SHORT).show();
                         } else {
                             waveLoadingView.setBottomTitle("");
                             waveLoadingView.setCenterTitle("");
                             Toast.makeText(WaterLevelActivity.this, "Water Level is High", Toast.LENGTH_SHORT).show();
                          }
                     }

                     @Override
                     public void onStartTrackingTouch(SeekBar seekBar) {

                     }

                     @Override
                     public void onStopTrackingTouch(SeekBar seekBar) {

                     }

                 });

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }

         });
    }
}
