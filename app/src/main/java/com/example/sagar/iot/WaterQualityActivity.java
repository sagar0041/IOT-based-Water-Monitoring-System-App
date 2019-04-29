package com.example.sagar.iot;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.anastr.speedviewlib.SpeedView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class WaterQualityActivity extends AppCompatActivity {

    SpeedView phValue;
    SpeedView waterQuality;

    FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
    DatabaseReference mRootReference = firebaseDatabase.getReference();
    DatabaseReference mphValue = mRootReference.child("mphValue").child("PhValue");

    GraphView graphView;
    LineGraphSeries series;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_quality);

        phValue=(SpeedView)findViewById(R.id.phValue);
        waterQuality=(SpeedView)findViewById(R.id.waterQuality);

        mphValue.addValueEventListener(new ValueEventListener()  {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Float phWater = dataSnapshot.getValue(Float.class);
                phValue.speedTo(phWater);

                if (mphValue == null) {
                    Toast.makeText(WaterQualityActivity.this, "Please Enter a Value", Toast.LENGTH_SHORT).show();
                }

                if (phWater >= 0.0 && phWater <= 1.1) {
                    Toast.makeText(WaterQualityActivity.this, "Battery Acid", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 1.1 && phWater <= 2.5) {
                    Toast.makeText(WaterQualityActivity.this, "Stomach Acid", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 2.5 && phWater <= 3) {
                    Toast.makeText(WaterQualityActivity.this, "Coca Cola", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 3 && phWater <= 5) {
                    Toast.makeText(WaterQualityActivity.this, "Gatorade(Lemon Lime)", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 5 && phWater <= 6.4) {
                    Toast.makeText(WaterQualityActivity.this, "Black Coffee", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 6.4 && phWater <= 7.34) {
                    Toast.makeText(WaterQualityActivity.this, "Pure Water", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 7.34 && phWater <= 8.1) {
                    Toast.makeText(WaterQualityActivity.this, "Human Blood", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 8.1 && phWater <= 9) {
                    Toast.makeText(WaterQualityActivity.this, "Seawater", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 9 && phWater <= 10) {
                    Toast.makeText(WaterQualityActivity.this, "Baking Soda", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 11 && phWater <= 13) {
                    Toast.makeText(WaterQualityActivity.this, "Ammonia(household cleaner)", Toast.LENGTH_SHORT).show();
                } else if (phWater >= 13 && phWater <= 14) {
                    Toast.makeText(WaterQualityActivity.this, "Sodium hydroxide", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WaterQualityActivity.this, "Invalid Value", Toast.LENGTH_SHORT).show();
                }

            }

//                phValue.speedPercentTo(phWater);

//
//                Log.e("speed know", phValue.getCurrentSpeed()+"");
//                Log.e("speed know", waterQuality.getCurrentSpeed()+"");

//                phValue.setOnSpeedChangeListener(new OnSpeedChangeListener() {
//                    @Override
//                    public void onSpeedChange(Gauge gauge, boolean isSpeedUp, boolean isByTremble) {
//                        int speed = phValue.getCurrentIntSpeed();
//                        Log.e("Speed is",speed + "");
//                    }
//                });
//
//                phValue.setOnSectionChangeListener(new OnSectionChangeListener() {
//                    @Override
//                    public void onSectionChangeListener(byte oldSection, byte newSection) {
//                        if (newSection == phValue.HIGH_SECTION)
//                            Toast.makeText(getApplicationContext(), "Slow Down !!", Toast.LENGTH_LONG).show();
//                    }
//                });

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WaterQualityActivity.this, "Please Enter Some value", Toast.LENGTH_SHORT).show();
            }
        });



//        phValue.speedTo(50, 4000);
//        waterQuality.speedTo(50, 4000);
//
//        phValue.speedPercentTo(30);
//
//        Log.e("speed know", phValue.getCurrentSpeed()+"");
//        Log.e("speed know", waterQuality.getCurrentSpeed()+"");
//
//        phValue.setOnSpeedChangeListener(new OnSpeedChangeListener() {
//            @Override
//            public void onSpeedChange(Gauge gauge, boolean isSpeedUp, boolean isByTremble) {
//                int speed = phValue.getCurrentIntSpeed();
//                Log.e("Speed is",speed + "");
//            }
//        });
//
//        waterQuality.setOnSpeedChangeListener(new OnSpeedChangeListener() {
//            @Override
//            public void onSpeedChange(Gauge gauge, boolean isSpeedUp, boolean isByTremble) {
//                int speed = waterQuality.getCurrentIntSpeed();
//                Log.e("Speed is",speed + "");
//            }
//        });
//
//        phValue.setOnSectionChangeListener(new OnSectionChangeListener() {
//            @Override
//            public void onSectionChangeListener(byte oldSection, byte newSection) {
//                if (newSection == phValue.HIGH_SECTION)
//                    Toast.makeText(getApplicationContext(), "Slow Down !!", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        waterQuality.setOnSectionChangeListener(new OnSectionChangeListener() {
//            @Override
//            public void onSectionChangeListener(byte oldSection, byte newSection) {
//                if (newSection == waterQuality.HIGH_SECTION)
//                    Toast.makeText(getApplicationContext(), "Slow Down !!", Toast.LENGTH_LONG).show();
//            }
//        });
    }

}
