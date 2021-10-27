package com.example.ymsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  TextView tvId, tvPWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvId = findViewById(R.id.tvId);
        tvPWD = findViewById(R.id.tvPwd);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        String userPwd = intent.getStringExtra("userPWD");

        tvId.setText(userId);
        tvPWD.setText(userPwd);
    }
}