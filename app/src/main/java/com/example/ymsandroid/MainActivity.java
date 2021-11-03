package com.example.ymsandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    CalendarFragment calendarFragment;
    ScheduleFragment scheduleFragment;
    ToDoListFragment toDoListFragment;
    UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userID");
        String userPwd = intent.getStringExtra("userPWD");

        calendarFragment = new CalendarFragment();
        scheduleFragment = new ScheduleFragment();
        toDoListFragment = new ToDoListFragment();
        userFragment = new UserFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, toDoListFragment).commit();
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itmTdl:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, toDoListFragment);
                        return true;
                    case R.id.itmCal:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, calendarFragment);
                        return true;
                    case R.id.itmSch:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, scheduleFragment);
                        return true;
                    case R.id.itmUser:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, userFragment);
                        return true;
                }
                return false;
            }
        });
    }
}