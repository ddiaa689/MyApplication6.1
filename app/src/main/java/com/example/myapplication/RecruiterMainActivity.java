package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecruiterMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_main);

        TextView tvInfo = findViewById(R.id.tvInfo);
        tvInfo.setText("欢迎，招聘者用户！这里可以发布职位、管理职位、查看简历、与求职者聊天等功能。");
    }
}