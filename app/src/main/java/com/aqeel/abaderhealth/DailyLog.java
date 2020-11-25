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

public class DailyLog extends AppCompatActivity {

    FirebaseAuth Fauth=FirebaseAuth.getInstance();
    String userID = Fauth.getCurrentUser().getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mealRef = database.getReference("meals").child(userID);
    List<String> mealloglist;
    ArrayAdapter mealadapter;
    Mealseaten mealseaten;
    ListView mealslogListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_log);

        mealslogListView = findViewById(R.id.mealslistview);
        mealRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mealseaten = new Mealseaten();
                mealloglist = new ArrayList<String>();
                for(DataSnapshot mealsfromF : snapshot.getChildren()){
                    mealseaten = mealsfromF.getValue(Mealseaten.class);
                    mealloglist.add(mealseaten.mealString());
                }
                mealadapter = new ArrayAdapter(DailyLog.this,android.R.layout.simple_list_item_1,mealloglist);
                mealslogListView.setAdapter(mealadapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DailyLog.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}