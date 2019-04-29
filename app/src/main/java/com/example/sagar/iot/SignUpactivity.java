package com.example.sagar.iot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpactivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail, editTextPassword,editTextName,editTextPhone;
    Button buttonRegister;
    TextView textViewSignin;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            Intent i = new Intent(SignUpactivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextPhone=(EditText)findViewById(R.id.editTextPhone);
        textViewSignin=(TextView)findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter Email ",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(!task.isSuccessful()){
                    Toast.makeText(SignUpactivity.this,"Registration Error" ,Toast.LENGTH_LONG).show();
                }else {
                        String user_id =firebaseAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                        String name = editTextName.getText().toString().trim();
                        String emailid = editTextEmail.getText().toString().trim();
                        String phone = editTextPhone.getText().toString().trim();
                        String password =editTextPassword.getText().toString().trim();

                        Map newPost = new HashMap();
                        newPost.put("name", name);
                        newPost.put("emailid", emailid);
                        newPost.put("phone", phone );
                        newPost.put("password", password );

                        current_user_db.setValue(newPost);
                        Toast.makeText(SignUpactivity.this,"Registration Successfull",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SignUpactivity.this,LoginActivity.class);
                        startActivity(i);
                        finish();
                }
            }
        });
    }

    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {

        if(v == buttonRegister){
            registerUser();
        }

        if(v == textViewSignin){
            Intent i = new Intent(SignUpactivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}

