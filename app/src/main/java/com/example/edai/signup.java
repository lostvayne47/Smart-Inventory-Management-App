package com.example.edai;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    EditText emailId, password;
    Button btnsignup;
    TextView accLogin;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth= FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnsignup = findViewById(R.id.btnsignup);
        accLogin = findViewById(R.id.accLogin);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pass = password.getText().toString();
                if (email.isEmpty())
                {
                    emailId.setError("Please enter your email id.");
                    emailId.requestFocus();
                }
                else if (pass.isEmpty())
                {
                    password.setError("Please enter your password.");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pass.isEmpty())
                {
                    Toast.makeText(signup.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pass.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(signup.this,"SignUp Unsuccessful!, Please Try Again.",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity(new Intent(signup.this, scanqr.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(signup.this,"Error occurred",Toast.LENGTH_SHORT).show();
                }
            }
        });

        accLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accLoginIntent = new Intent(signup.this, login.class);
                startActivity(accLoginIntent);
            }
        });
    }
}
