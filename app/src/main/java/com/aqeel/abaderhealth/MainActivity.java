package com.aqeel.abaderhealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    Button logout;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggleOnAndOff;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = findViewById(R.id.navdrawer);

        toggleOnAndOff = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.draweropen,R.string.drawerclosed);
        drawerLayout.addDrawerListener(toggleOnAndOff);
        toggleOnAndOff.syncState();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, MyProfile.class);
                MainActivity.this.startActivity(i);

                break;

            case R.id.nav_log:

                Intent l = new Intent(MainActivity.this, DailyLog.class);
                MainActivity.this.startActivity(l);

                break;

            case R.id.nav_exercise:

                Intent e = new Intent(MainActivity.this, Exercise.class);
                MainActivity.this.startActivity(e);

                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),UserLogin.class));
        finish();
    }
}