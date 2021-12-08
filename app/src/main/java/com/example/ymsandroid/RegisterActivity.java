package com.example.ymsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtId, edtPWD, edtName, edtAge;
    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtId = findViewById(R.id.edtId);
        edtPWD = findViewById(R.id.edtPwd);
        edtName = findViewById(R.id.edtName);
        edtPWD = findViewById(R.id.edtPwd);
        edtAge = findViewById(R.id.edtAge);
        btnReg = findViewById(R.id.btnReg);

        // 회원가입 버튼
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Edt의 값을 Get
                String userID = edtId.getText().toString();
                String userPWD = edtPWD.getText().toString();
                String userName = edtName.getText().toString();
                int userAge = Integer.parseInt(edtAge.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("리턴", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("status");
                            if(success) { // 회원 등록에 성공
                                Toast.makeText(getApplicationContext(), "회원 등록에 성공!" + "아이디는 " + userID + "비밀번호는 " + userPWD, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "회원 등록에 실패!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) { // 회원 등록에 실패
                            e.printStackTrace();
                        }
                    }
                };

                //서버로 Volley를 이용해 요청
                RegisterRequest registerRequest = new RegisterRequest(userID, userPWD, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}