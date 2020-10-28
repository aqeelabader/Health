package com.aqeel.abaderhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLogin extends AppCompatActivity {


    EditText uEmail, uPassword;
    Button uLogin;
    TextView uCreatenew;
    FirebaseAuth Fauth;
    ProgressBar progressBar;

    private  FirebaseAuth.AuthStateListener uAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        uEmail = findViewById(R.id.email);
        uPassword = findViewById(R.id.userPassword);
        uCreatenew = findViewById(R.id.registernew);
        uLogin = findViewById(R.id.UserLoginbutton);
        progressBar = findViewById(R.id.progressBar);


        Fauth = FirebaseAuth.getInstance();


        uLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();

                //checking user has entered info

                if(TextUtils.isEmpty(email)){
                    uEmail.setError("Email is Required. *");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    uPassword.setError("Password is Required. *");
                    return;
                }

                //checking password length

                if (password.length()<6){
                    uPassword.setError("Password must be 6 or more characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //authenticating the user

                Fauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(UserLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(UserLogin.this, "Error, Try Again", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intToMain = new Intent(UserLogin.this,MainActivity.class);
                            startActivity(intToMain);
                        }
                    }
                });

            }
        });
    }
}