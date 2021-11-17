package com.example.ymsandroid.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ymsandroid.R;

public class ScheduleFragment extends Fragment {
    Button btnSch, btnAdd, btnList ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        btnAdd = v.findViewById(R.id.btnAdd);
        btnSch = v.findViewById(R.id.btnSch);
        btnList = v.findViewById(R.id.btnList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "온 클릭",Toast.LENGTH_SHORT).show();
            }
        });
        btnAdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(focus){
                    Toast.makeText(getContext(), "온 터치",Toast.LENGTH_SHORT).show();
                    btnSch.setVisibility(View.VISIBLE);
                    btnList.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "벗어남",Toast.LENGTH_SHORT).show();
                    btnSch.setVisibility(View.INVISIBLE);
                    btnList.setVisibility(View.INVISIBLE);
                }
            }
        });

        return v;
    }
}