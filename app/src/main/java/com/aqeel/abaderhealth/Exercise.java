package com.aqeel.abaderhealth;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Exercise extends AppCompatActivity {
    FirebaseAuth Fauth=FirebaseAuth.getInstance();
    String userID = Fauth.getCurrentUser().getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference exRef = database.getReference("exercise").child(userID);
    List<String> exloglist;
    ArrayAdapter exadapter;
    Exercisedone exercisedone;
    ListView exlogListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        exlogListView = findViewById(R.id.exerciselistview);
        exRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                exercisedone = new Exercisedone();
                exloglist = new ArrayList<String>();
                for(DataSnapshot exercisefromF : snapshot.getChildren()){
                    exercisedone = exercisefromF.getValue(Exercisedone.class);
                    exloglist.add(exercisedone.ToAString());
                }
                exadapter = new ArrayAdapter(Exercise.this,android.R.layout.simple_list_item_1,exloglist);
                exlogListView.setAdapter(exadapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Exercise.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}