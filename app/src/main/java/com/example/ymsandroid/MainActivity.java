package com.example.ymsandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ymsandroid.fragment.CalendarFragment;
import com.example.ymsandroid.fragment.ScheduleFragment;
import com.example.ymsandroid.fragment.ToDoListFragment;
import com.example.ymsandroid.fragment.UserFragment;
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

        BottomNavigationView bottomNav = findViewById(R.id.bottom_menu);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    return false;
                }
            };
}

/*

        return false;
        }
        });
*/