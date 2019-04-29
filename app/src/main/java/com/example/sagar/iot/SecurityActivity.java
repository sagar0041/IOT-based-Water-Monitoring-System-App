package com.example.sagar.iot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public  class SecurityActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    TextView textViewUserEmail;
    CardView buttonLogout,waterLevel,buttonAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome "+user.getEmail());
        buttonLogout=(CardView) findViewById(R.id.buttonLogout);
        waterLevel = (CardView) findViewById(R.id.waterLevel);
        buttonAboutUs=(CardView) findViewById(R.id.buttonAboutUs);


        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent i = new Intent(SecurityActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        waterLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecurityActivity.this,CardViewSecActivity.class);
                startActivity(i);
                finish();
            }
        });

        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecurityActivity.this,AboutusActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
//
//    @Override
//    public void onClick(View v) {
//
//        if(v == buttonLogout){
//            firebaseAuth.signOut();
//            Intent i = new Intent(SecurityActivity.this,LoginActivity.class);
//            startActivity(i);
//            finish();
//        }
//
//    }
}
