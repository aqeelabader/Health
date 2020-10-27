package com.aqeel.abaderhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Register extends AppCompatActivity {

    EditText uFullName, uEmail, uPassword;
    Button uRegisterBtn;
    TextView uLoginBtn;
    FirebaseAuth Fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //assigning values

        uFullName = findViewById(R.id.fullName);
        uEmail = findViewById(R.id.email);
        uPassword = findViewById(R.id.userPassword);
        uRegisterBtn = findViewById(R.id.registerbutton);
        uLoginBtn = findViewById(R.id.loginold);

        //firebaseAuth instance

        Fauth = FirebaseAuth.getInstance();

        //checking to see if user is already logged in.

        if(Fauth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        //registration code

        uRegisterBtn.setOnClickListener(new View.OnClickListener() {
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

                //registering the user in firebase auth

                Fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //if successful the user will be shown a success message and taken to the main page.
                        //if not, they will be shown an error message with details on exactly what went wrong.

                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "error, "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}