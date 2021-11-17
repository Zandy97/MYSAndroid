package com.example.ymsandroid.fragment;

import android.app.AlertDialog;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ymsandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ToDoListFragment extends Fragment {

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

//   private String mParam2;
    private String userID;

    private OnFragmentInteractionListener mListener;

    public ToDoListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Log.v("TodoList ID : ", userID);
    }

    private ListView ingListView;
    private ToDoListAdapter adapter;
    private List<ToDoList> toDoListList;

    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        ingListView = (ListView)getView().findViewById(R.id.lvIng);
        toDoListList = new ArrayList<ToDoList>();
        adapter = new ToDoListAdapter(getContext().getApplicationContext(), toDoListList);
        ingListView.setAdapter(adapter);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("할일 페이지");
        alert.setMessage("아이디는 " + userID);
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Bundle extra = this.getArguments();
//
//        if(extra != null) {
//            extra = getArguments();
//            userID = extra.getString("userID");
//        }
//
//        Log.v("ToDoList 부분 아이디 받아온것은", userID);
        return inflater.inflate(R.layout.fragment_todolist, container, false);
    }

    public void onButtonPressed(Uri uri){
        if(mListener != null){
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = R.string.serverIP+"/todolist.php?" +
                        "userID=" + URLEncoder.encode(userID, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                toDoListList.clear();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String listTitle, listCategory, listDate;

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    listTitle = object.getString("listTitle");
                    listCategory = object.getString("listCategory");
                    listDate = object.getString("listDate");

                    ToDoList toDoList = new ToDoList(listTitle, listCategory, listDate);
                    toDoListList.add(toDoList);
                    count++;
                }
                if (count == 0){
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(ToDoListFragment.this.getContext());
                    dialog = builder.setMessage("조회된 강의가 없습니다.")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // 백그라운드 실행중에 실행
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        // 백그라운드 스레드로 동작하는 작업
        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                // 웹 서버 프로그램(URL) 연결 객체 작성
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                //URL로 부터 받게되는 InputStream 설정
                InputStream inputStream = httpURLConnection.getInputStream();
                //연결된 URL에서 데이터를 얻어와서 버퍼 스트림에 저장
                BufferedReader bufferedReader
                        = new BufferedReader(new InputStreamReader(inputStream));
                //읽어온 데이터를 문자열 변수 temp로 합치기
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}