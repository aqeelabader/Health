package com.aqeel.abaderhealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addmeallog extends AppCompatActivity {

    FirebaseAuth Fauth=FirebaseAuth.getInstance();
    String userID = Fauth.getCurrentUser().getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mealRef = database.getReference("meals").child(userID);
    EditText mealname, mealcal;
    String userid, enteredmealname, enteredmealcals;
    Button mealLog;
    Mealseaten mealseaten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeallog);

        mealname = findViewById(R.id.mealName);
        mealcal = findViewById(R.id.calnum);
        mealLog = findViewById(R.id.meallogbtn);

        mealLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid = userID.toString().trim();
                enteredmealname = mealname.getText().toString().trim();
                enteredmealcals = mealcal.getText().toString().trim();

                mealseaten = new Mealseaten(userid,enteredmealname,enteredmealcals);

                mealRef.push().setValue(mealseaten).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(addmeallog.this, "Added!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), DailyLog.class));
                    }
                });
            }
        });

    }
}