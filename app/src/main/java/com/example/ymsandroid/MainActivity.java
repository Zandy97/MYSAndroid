package com.example.ymsandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ymsandroid.fragment.CalendarFragment;
import com.example.ymsandroid.fragment.ScheduleFragment;
import com.example.ymsandroid.fragment.ToDoListFragment;
import com.example.ymsandroid.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userID");
        String userPwd = intent.getStringExtra("userPWD");

        bottomNavigationView = findViewById(R.id.bottom_menu);

        getSupportFragmentManager().beginTransaction().add(R.id.container, new ToDoListFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itmTdl:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ToDoListFragment()).commit();
                        break;
                    case R.id.itmCal:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CalendarFragment()).commit();
                        break;
                    case R.id.itmSch:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ScheduleFragment()).commit();
                        break;
                    case R.id.itmUser:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new UserFragment()).commit();
                        break;
                }
                return true;
            }
        });
    }
}