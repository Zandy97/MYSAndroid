package com.example.ymsandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.ymsandroid.fragment.CalendarFragment;
import com.example.ymsandroid.fragment.ScheduleFragment;
import com.example.ymsandroid.fragment.ToDoListFragment;
import com.example.ymsandroid.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Fragment toDolistFragment, scheduleFragment, calendarFragment, userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPwd = intent.getStringExtra("userPWD");

        Bundle bundle = new Bundle();

        FrameLayout frameLayout = findViewById(R.id.container);
        BottomNavigationView bottomView = findViewById(R.id.bottom_menu);

        bottomView.setOnNavigationItemSelectedListener(listener);

        toDolistFragment = new ToDoListFragment();
        scheduleFragment = new ScheduleFragment();
        calendarFragment = new CalendarFragment();
        userFragment = new UserFragment();

        bundle.putString("userID",userID);
        Log.d("setAgument", "userID값은 " + userID);
        toDolistFragment.setArguments(bundle);
//        scheduleFragment.setArguments(bundle);
//        calendarFragment.setArguments(bundle);
//        userFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.container, new ToDoListFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.itmTdl:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, toDolistFragment).commit();
                    return true;
                case R.id.itmCal:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, calendarFragment).commit();
                    return true;
                case R.id.itmSch:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, scheduleFragment).commit();
                    return true;
                case R.id.itmUser:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, userFragment).commit();
                    return true;
            }
            return false;
        }
    };
}
