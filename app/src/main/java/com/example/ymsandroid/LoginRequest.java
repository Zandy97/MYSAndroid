package com.example.ymsandroid;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //서버 URL 설정
    final static private String URL = "132.226.235.252:8080/user/login";
    private Map<String, String> map;

    public LoginRequest(String userID, String userPWD, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPWD", userPWD);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}