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

public class MyProfile extends AppCompatActivity {

    FirebaseAuth Fauth=FirebaseAuth.getInstance();
    String userID = Fauth.getCurrentUser().getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("userdetails").child(userID);
    ListView userdetailview;
    List<String> detailList;
    ArrayAdapter adapter;
    Udetail udetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        userdetailview = findViewById(R.id.userdetailview);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                udetail = new Udetail();

                detailList = new ArrayList<String>();

                for(DataSnapshot userdetailsfromF : snapshot.getChildren()){
                    udetail = userdetailsfromF.getValue(Udetail.class);
                    detailList.add(udetail.ToString());
                }

                adapter = new ArrayAdapter(MyProfile.this,android.R.layout.simple_list_item_1,detailList);

                userdetailview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(MyProfile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}