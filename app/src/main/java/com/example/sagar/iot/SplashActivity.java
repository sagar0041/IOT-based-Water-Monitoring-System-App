package com.example.sagar.iot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class SplashActivity extends AppCompatActivity {

    LinearLayout splash, splash2;
    Button btnSub;
    Animation uptodown, downtoup;
    SharedPreferences mPrefs;
    final String splashDisplayedSharePref = "splashScreenShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btnSub = (Button) findViewById(R.id.btnSub);
        splash = (LinearLayout) findViewById(R.id.splash);
        splash2 = (LinearLayout) findViewById(R.id.splash2);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        splash.setAnimation(uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        splash2.setAnimation(downtoup);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}