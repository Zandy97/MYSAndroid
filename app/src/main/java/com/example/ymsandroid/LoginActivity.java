package com.example.ymsandroid;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText edtId, edtPWD;
    private Button btnLog, btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtId = findViewById(R.id.edtId);
        edtPWD = findViewById(R.id.edtPwd);

        btnLog = findViewById(R.id.btnLog);
        btnReg = findViewById(R.id.btnReg);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = edtId.getText().toString();
                String userPWD = edtPWD.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success) { // 회원 등록에 성공
                                String userID = jsonObject.getString("userID");
                                String userPWD = jsonObject.getString("userPWD");

                                Toast.makeText(getApplicationContext(), "로그인에 성공!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPWD", userPWD);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(), "로그인에 실패!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) { // 회원 등록에 실패
                            e.printStackTrace();
                        }
                        //testing
                    }
                };

                LoginRequest loginRequest = new LoginRequest(userID, userPWD, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}