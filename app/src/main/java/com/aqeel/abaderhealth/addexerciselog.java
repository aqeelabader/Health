package com.aqeel.abaderhealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addexerciselog extends AppCompatActivity {

    FirebaseAuth Fauth=FirebaseAuth.getInstance();
    String userID = Fauth.getCurrentUser().getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference exRef = database.getReference("exercise").child(userID);
    EditText  exercisetype, calburned, wchange;
    String userid, enteredexercisetype, enteredcalburned,enteredwchange;
    Button Log;
    Exercisedone exercisedone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexerciselog);
        exercisetype = findViewById(R.id.exercisetype);
        calburned = findViewById(R.id.calburned);
        wchange = findViewById(R.id.weightchange);
        Log = findViewById(R.id.exlog);

        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid = userID.toString().trim();
                enteredexercisetype = exercisetype.getText().toString().trim();
                enteredcalburned = calburned.getText().toString().trim();
                enteredwchange = wchange.getText().toString().trim();

                exercisedone = new Exercisedone(userid,enteredexercisetype,enteredexercisetype,enteredwchange);

                exRef.push().setValue(exercisedone).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(addexerciselog.this, "Added!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Exercise.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addexerciselog.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}