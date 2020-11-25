package com.aqeel.abaderhealth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class capturingInitialData extends AppCompatActivity {

    EditText uFullName, uheight, uweight, ucalorie, uTheight, uTweight, uTcalorie;
    Button Continue;
    FirebaseAuth Fauth=FirebaseAuth.getInstance();
    Udetail udetail;


    String userID = Fauth.getCurrentUser().getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("userdetails").child(userID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturing_initial_data);






        uFullName = findViewById(R.id.fullName);
        uheight = findViewById(R.id.heightInput);
        uweight = findViewById(R.id.weightInput);
        ucalorie = findViewById(R.id.currentCalInput);
        uTheight = findViewById(R.id.heightGoalInput);
        uTweight = findViewById(R.id.weightGoalInput);
        uTcalorie = findViewById(R.id.calorieGoalInput);
        Continue = findViewById(R.id.continueBTN);



        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Uid = userID.toString().trim();
                String Fullname = uFullName.getText().toString().trim();
                String height = uheight.getText().toString().trim();
                String weight = uweight.getText().toString().trim();
                String calorie = ucalorie.getText().toString().trim();
                String Theight = uTheight.getText().toString().trim();
                String Tweight = uTweight.getText().toString().trim();
                String Tcalorie = uTcalorie.getText().toString().trim();

                if (TextUtils.isEmpty(Fullname)) {
                    uFullName.setError("Required.");
                    return;
                }
                if (TextUtils.isEmpty(height)) {
                    uheight.setError("Required. *");
                    return;
                }
                if (TextUtils.isEmpty(weight)) {
                    uweight.setError("Required. *");
                    return;
                }
                if (TextUtils.isEmpty(calorie)) {
                    ucalorie.setError("Required. *");
                    return;
                }
                if (TextUtils.isEmpty(Theight)) {
                    uTheight.setError("Required. *");
                    return;
                }
                if (TextUtils.isEmpty(Tweight)) {
                    uTweight.setError("Required. *");
                    return;
                }
                if (TextUtils.isEmpty(Tcalorie)) {
                    uTcalorie.setError("Required. *");
                    return;
                }


                udetail = new Udetail(Uid, Fullname,height,weight,calorie,Theight,Tweight,Tcalorie);
                myRef.push().setValue(udetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(capturingInitialData.this, "success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });

            }
        });
    }
}







