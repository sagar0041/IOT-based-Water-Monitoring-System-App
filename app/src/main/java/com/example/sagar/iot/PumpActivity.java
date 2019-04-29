package com.example.sagar.iot;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.TransitionDrawable;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Locale;
import com.pusher.pushnotifications.PushNotifications;

public class PumpActivity extends AppCompatActivity implements ValueEventListener {

    ImageView imageView;
    ToggleButton btnToggle;
    boolean turnedOn = false;
    TextToSpeech tts;
    FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
    DatabaseReference mRootReference = firebaseDatabase.getReference();
    DatabaseReference mChildReference = mRootReference.child("Pump");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump);

        int o = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(o);

        imageView=(ImageView)findViewById(R.id.imageView);
        btnToggle=(ToggleButton)findViewById(R.id.btnToogle);

        tts =new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                /*validation */
                if(i != TextToSpeech.ERROR)
                    tts.setLanguage(Locale.ENGLISH);
            }
        });
        mChildReference.setValue(0);

        btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!turnedOn){
                    imageView.setImageResource(R.drawable.trans_on);
                    ((TransitionDrawable)imageView.getDrawable()).startTransition(2000);
                    turnedOn = true;
                    mChildReference.setValue(1);
                    tts.speak("Water Tap Turn ON",TextToSpeech.QUEUE_FLUSH,null);
                }else {
                    imageView.setImageResource(R.drawable.trans_off);
                    ((TransitionDrawable)imageView.getDrawable()).startTransition(2000);
                    turnedOn = false;
                    mChildReference.setValue(0);
                    tts.speak("Water Tap Turn OFF",TextToSpeech.QUEUE_FLUSH,null);
                }

            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getValue(Integer.class)!=null){
//            Integer key = Integer.valueOf(dataSnapshot.getKey());
            Integer Pump = Integer.valueOf(dataSnapshot.getValue(Integer.class));
            if(Pump.equals(1)){
                btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(!turnedOn){
                            imageView.setImageResource(R.drawable.trans_on);
                            ((TransitionDrawable)imageView.getDrawable()).startTransition(2000);
                            turnedOn = true;
                            btnToggle.setChecked(true);
                            mChildReference.setValue(1);
                            tts.speak("Water Tap Turn ON",TextToSpeech.QUEUE_FLUSH,null);
                        }else {
                            imageView.setImageResource(R.drawable.trans_off);
                            ((TransitionDrawable)imageView.getDrawable()).startTransition(2000);
                            turnedOn = false;
                            btnToggle.setChecked(false);
                            mChildReference.setValue(0);
                            tts.speak("Water Tap Turn OFF",TextToSpeech.QUEUE_FLUSH,null);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mChildReference.addValueEventListener(this);

    }
}
