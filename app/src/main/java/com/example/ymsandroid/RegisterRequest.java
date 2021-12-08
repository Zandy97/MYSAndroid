package com.example.ymsandroid;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    //서버 URL 설정
    final static private String URL = "http://132.226.235.252/user/join";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPWD, String userName, int userAge, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("id", userID);
        map.put("pwd", userPWD);
        map.put("name", userName);
        map.put("age", userAge + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

