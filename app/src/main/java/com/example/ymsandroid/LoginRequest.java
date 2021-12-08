package com.example.ymsandroid;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //서버 URL 설정
    final static private String URL = "http://132.226.235.252/user/login_check";
    private Map<String, String> map;

    public LoginRequest(String userID, String userPWD, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("id", userID);
        map.put("pwd", userPWD);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}